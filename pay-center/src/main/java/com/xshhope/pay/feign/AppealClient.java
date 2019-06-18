package com.xshhope.pay.feign;

import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xshhope
 */
@FeignClient("user-apply")
public interface AppealClient {

    @GetMapping("/apply/getApplyById/{id}")
    public ApplyPO getApplyByIdd(@PathVariable("id") Long id);

    @GetMapping("/appeal/getAppealById/{id}")
    public AppealPO getAppealByIdd (@PathVariable("id") Long id);

    @GetMapping("/appeal/getAppealById/{id}")
    public AppealPO getAppeals ();
}
