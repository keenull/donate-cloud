package com.xshhope.apply.consumer;

import com.xshhope.apply.config.RabbitmqConfig;
import com.xshhope.apply.feign.LogClient;
import com.xshhope.apply.service.AppealService;
import com.xshhope.apply.service.ApplyService;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import com.xshhope.model.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author xshhope
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitmqConfig.APPLY_UPDATE_QUEUE)
public class ApplyUpdateConsumer {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private AppealService appealService;

    @Autowired
    private LogClient logClient;

    /**
     * 收到支付更新的消
     * @param map
     */
    @RabbitHandler
    public void applyUpdateHandler(Map<String, Object> map) {
        log.info("接收到支付更新的消息,map.size:{}", map.size());
        try {
            appealService.saveAppeal((AppealPO)(map.get("appeal")));
            applyService.save((ApplyPO)(map.get("apply")));

            // 日志保存
            savePayUpdateLog("", "支付更新");
        } catch (Exception e) {
            log.error("支付更新处理异常", e);
        }
    }

    /**
     * 支付更新日志
     *
     * @param name
     */
    private void savePayUpdateLog(String name, String remark) {
        log.info("{}支付更新", name);
        // 异步
        CompletableFuture.runAsync(() -> {
            try {
                Log log = Log.builder().username(name).module("支付更新").remark(remark).createTime(new Date())
                        .build();
                logClient.save(log);
            } catch (Exception e) {
                // do nothing
            }

        });
    }

}
