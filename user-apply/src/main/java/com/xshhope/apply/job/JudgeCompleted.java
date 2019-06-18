package com.xshhope.apply.job;

import com.google.common.collect.Lists;
import com.xshhope.apply.repository.AppealRepository;
import com.xshhope.apply.service.AppealService;
import com.xshhope.apply.service.ApplyService;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xshhope
 */
@Slf4j
@Component
public class JudgeCompleted {

    @Autowired
    private AppealService appealService;

    @Autowired
    private ApplyService applyService;

    /**
     * 每分钟检查是否已完成
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void checkCompleted(){
        List<AppealPO> appealPOS = Lists.newArrayList();
        List<ApplyPO> applyPOS = Lists.newArrayList();
        List<Long> ids = Lists.newArrayList();
        appealService.listAppeals().forEach(x ->{
            if (x.getCompleted() > x.getGoal()){
                x.setAppealState(1);
                appealPOS.add(x);
                ids.add(x.getApplyId());
            }
        });
        ids.forEach(x -> {
            applyPOS.add(applyService.getApplyById(x));
        });
        appealService.saveAll(appealPOS);
        applyService.saveAll(applyPOS);
    }
}
