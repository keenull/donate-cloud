package com.xshhope.pay.job;

import com.alipay.api.AlipayApiException;
import com.xshhope.model.pay.PayPO;
import com.xshhope.pay.facade.PayFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JudgePaiedJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgePaiedJob.class);

    @Autowired
    private PayFacade payFacade;

    /**
     * 每小时检查是否漏单
     */
    @Scheduled(cron="0 0 0/1 * * ?")
    public void checkJob() {

        // 检查是否漏单
        List<PayPO> dmf = payFacade.getByState(0);
        dmf.forEach(e -> {
            try {
                payFacade.queryPayState(e.getId());
            } catch (AlipayApiException e1) {
                LOGGER.info(e1.getErrMsg());
            }
        });
    }
}