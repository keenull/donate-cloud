package com.xshhope.pay.controller;

import com.alipay.api.AlipayApiException;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.Langs;
import com.xshhope.pay.facade.PayFacade;
import com.xshhope.pay.model.dto.PayVO;
import com.xshhope.pay.model.param.PayParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xshhope
 */
@Api(description = "捐款模块")
@RestController
@RequestMapping(value = "/donate")
public class PayController {

    @Autowired
    private PayFacade payFacade;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation(value = "捐款", notes = "需要权限", produces = "application/json")
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ActionResult<Object> searchUser(HttpServletRequest request, @RequestBody PayParam param)  throws AlipayApiException{
        if (Langs.isEmpty(param)) {
            return ActionTemplate.toNack("参数不能为空");
        }

        return payFacade.pay(request, param);
    }

    /**
     * 查询支付结果
     * @param id
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping(value = "/pay/state/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ActionResult<Object> queryPayState(@PathVariable String id) throws AlipayApiException {

        return payFacade.queryPayState(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/pay-anon/getLastWeek/{id}")
    public ActionResult getLastWeek(@PathVariable("id") Long id){
        List<PayVO> data = payFacade.getLastWeek();
        return ActionTemplate.toAck("查询成功", data);
    }
}
