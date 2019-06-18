package com.xshhope.pay.facade;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import com.xshhope.model.apply.constants.UserApplyMq;
import com.xshhope.model.log.Log;
import com.xshhope.model.pay.PayPO;
import com.xshhope.model.user.AppUser;
import com.xshhope.pay.feign.AppealClient;
import com.xshhope.pay.feign.LogClient;
import com.xshhope.pay.model.dto.PayVO;
import com.xshhope.pay.model.param.PayParam;
import com.xshhope.pay.service.PayService;
import com.xshhope.pay.service.RecordService;
import com.xshhope.pay.service.impl.IpInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xshhope
 */
@Slf4j
@Service
public class PayFacade {

    @Value("${custom.ip.expire}")
    private Long IP_EXPIRE;

    @Value("${custom.my.token}")
    private String MY_TOKEN;

    @Value("${custom.email.sender}")
    private String EMAIL_SENDER;

    @Value("${custom.email.receiver}")
    private String EMAIL_RECEIVER;

    @Value("${custom.token.admin.expire}")
    private Long ADMIN_EXPIRE;

    @Value("${custom.server.url}")
    private String SERVER_URL;

    @Value("${custom.qrnum}")
    private Integer QRNUM;

    @Value("${custom.my.privateKey}")
    private String privateKey;

    @Value("${custom.my.publicKey}")
    private String publicKey;

    @Value("${custom.my.appId}")
    private String appId;

    @Autowired
    private PayService payService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private AppealClient appealClient;

    @Autowired
    private LogClient logClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private EmailUtils emailUtils;

    private static final String CLOSE_DMF_KEY="Donate_CLOSE_DMF_KEY";

    private static final String CLOSE_DMF_REASON="Donate_CLOSE_DMF_REASON";

    public ActionResult pay(HttpServletRequest request, PayParam param) throws AlipayApiException{
        AppUser user = AppUserUtil.getLoginAppUser();
        PayPO payPO = new PayPO();
        AppealPO appealPO = appealClient.getAppealByIdd(param.getAppealId());
        ApplyPO applyPO = appealClient.getApplyByIdd(appealPO.getApplyId());

        String isOpenDMF = redisTemplate.opsForValue().get(CLOSE_DMF_KEY);
        String dmfReason = redisTemplate.opsForValue().get(CLOSE_DMF_REASON);
        String msg = "";
        if(StringUtils.isNotBlank(isOpenDMF)){
            msg = dmfReason + "如有疑问请进行反馈";
            return ActionTemplate.toNack(msg);
        }

        String ip = IpInfoUtils.getIpAddr(request);
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }

        ip="DMF:"+ip;

        payPO.setPayState(0);
        payPO.setMoney(param.getMoney().doubleValue());
        payPO.setReceiver(applyPO.getUserId());
        payPO.setComment(param.getComment());
        payPO.setAppealId(param.getAppealId());
        payPO.setTitle(param.getTitle());
        payPO.setId(UUID.randomUUID().toString());
        payPO.setCreatedUserId(user.getId());
        payPO.setDeleted(0);
        payPO.setGmtCreated(new Date());
        payPO.setGmtModified(new Date());
        payPO.setTitle(applyPO.getTitle());
        payService.savePay(payPO);

        // 日志保存
        savePayLog(user.getUsername(), "用户支付");

        //记录缓存
        redisTemplate.opsForValue().set(ip,"added", 1L, TimeUnit.MINUTES);

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                appId,privateKey,"json","GBK",publicKey,"RSA2");
        AlipayTradePrecreateRequest r = new AlipayTradePrecreateRequest();
        r.setBizContent("{" +
                "\"out_trade_no\":\""+payPO.getId()+"\"," +
                "\"total_amount\":"+payPO.getMoney()+"," +
                "\"subject\":\"校园爱心捐赠\"" +
                "  }");
        AlipayTradePrecreateResponse response = alipayClient.execute(r);

        if(!response.isSuccess()){
            return ActionTemplate.toNack("调用支付宝接口生成二维码失败");
        }
        Map<String, Object> result = new HashMap<>(16);
        result.put("id", payPO.getId());
        result.put("qrCode", response.getQrCode());

        return ActionTemplate.toAck("添加捐赠支付订单成功", result);
    }


    public ActionResult queryPayState(String outTradeNo) throws AlipayApiException{
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                appId,privateKey,"json","GBK",publicKey,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+outTradeNo+"\"" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response!=null&&response.isSuccess()&&"TRADE_SUCCESS".equals(response.getTradeStatus())){
            sendActiveEmail(outTradeNo);
            Map<String, Object> data = Maps.newHashMap();
            data.put("code",1);
            return  ActionTemplate.toAck("发送邮件成功", data);
        }
        Map<String, Object> data = Maps.newHashMap();
        data.put("code",0);
        return ActionTemplate.toAck("支付中!!!", data);
    }



    @Async
    public void sendActiveEmail(String id){

        PayPO pay = payService.getPayById(id);

        AppUser user = AppUserUtil.getLoginAppUser();

        if(pay.getPayState()==1){
            return;
        }

        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("dmf:"+pay.getId(), token, ADMIN_EXPIRE, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(pay.getId(), token, ADMIN_EXPIRE, TimeUnit.DAYS);

        pay.setPayState(1);
        payService.savePay(pay);

        Map<String, Object> adminMap = Maps.newHashMap();
        adminMap.put("title", pay.getTitle());
        adminMap.put("nickName", AppUserUtil.getLoginAppUser().getNickname());
        adminMap.put("money", pay.getMoney());
        adminMap.put("comment", pay.getComment());
        adminMap.put("email", user.getMail());
        adminMap.put("gmtCreated", pay.getGmtCreated());

        Map<String, Object> userMap = Maps.newHashMap();
        userMap.put("nickName", AppUserUtil.getLoginAppUser().getNickname());
        userMap.put("title", pay.getTitle());
        userMap.put("money", pay.getMoney());
        userMap.put("comment", pay.getComment());
        userMap.put("payOrder", pay.getId());
        userMap.put("gmtCreated", pay.getGmtCreated());

        emailUtils.sendTemplateMail(EMAIL_SENDER, EMAIL_RECEIVER, "【爱心捐赠平台】当面付收款" + pay.getMoney() + "元", "email-admin", adminMap);
        emailUtils.sendTemplateMail(EMAIL_SENDER, user.getMail(), "【爱心捐赠平台】捐赠成功通知", "email-user", userMap);

        AppealPO appealPO = appealClient.getAppealByIdd(pay.getAppealId());
        appealPO.setAppealCount(appealPO.getAppealCount() + 1);
        appealPO.setCompleted(appealPO.getCompleted() + pay.getMoney());

        ApplyPO applyPO = appealClient.getApplyByIdd(appealPO.getApplyId());

        if (appealPO.getCompleted() > appealPO.getGoal()) {

            applyPO.setCompleted(0);
            appealPO.setAppealState(0);
        }

        Map<String, Object> map = Maps.newHashMap();

        map.put("apply", applyPO);
        map.put("appeal",appealPO);

        // 发布支付更新的消息
        amqpTemplate.convertAndSend(UserApplyMq.MQ_EXCHANGE_Apply, UserApplyMq.ROUTING_KEY_APPLY_UPDATE, map);

        recordService.saveRecordByPay(pay);

        redisTemplate.delete("xpay:"+pay.getId());
    }

    public List<PayPO> getByState(Integer state) {
        return payService.getByState(state);
    }

    public List<PayVO> getLastWeek() {
        Map<Long, Integer> data = Maps.newHashMap();
        TreeMap<Long, Integer> data2 = Maps.newTreeMap();
        // 最近7天记录
        List<PayPO> list = payService.getLastWeek();

        list.forEach(x -> {

            for (Integer i = 0; i < 7; i++) {
                Long aaa = getStartTimeOfDay(System.currentTimeMillis() - i * 86400000);
                Long bbb = getEndTimeOfDay(System.currentTimeMillis() - i * 86400000);
                Long ccc = x.getGmtCreated().getTime();
                Long target = getStartTimeOfDay(x.getGmtCreated().getTime());
                if (ccc > aaa && ccc < bbb) {
                    if (data.containsKey(target)) {
                        data.put(target, (data.get(target)) + 1);
                    } else {
                        data.put(target, 0);
                    }
                }
            }
        });


        for (int j = 0; j < 7; j++){
            data2.put(getStartTimeOfDay(System.currentTimeMillis() - j * 86400000), 0);
        }

        for (Map.Entry<Long, Integer> entry : data.entrySet()) {
            if (data2.containsKey(entry.getKey())){
                int result = entry.getValue() + 1;
                data2.put(entry.getKey(),result);
            }
        }

        List<PayVO> payVOS = Lists.newArrayList();

        for (Map.Entry<Long, Integer> entry : data2.entrySet()) {
            PayVO payVO = new PayVO();
            payVO.setTime(entry.getKey());
            payVO.setCount(entry.getValue());
            payVOS.add(payVO);
        }
        return payVOS;
    }

    private long getStartTimeOfDay(Long now) {
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private long getEndTimeOfDay(Long now) {
        String tz = "GMT+8";
        TimeZone curTimeZone = TimeZone.getTimeZone(tz);
        Calendar calendar = Calendar.getInstance(curTimeZone);
        calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 支付日志
     *
     * @param username
     */
    private void savePayLog(String username, String remark) {
        log.info("{}支付", username);
        // 异步
        CompletableFuture.runAsync(() -> {
            try {
                Log log = Log.builder().username(username).module("支付").remark(remark).createTime(new Date())
                        .build();
                logClient.save(log);
            } catch (Exception e) {
                // do nothing
            }

        });
    }
}
