package com.xshhope.apply.model.dto;

import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xshhope
 */
@Data
public class AppealApplyDTO implements Serializable {

    private static final long serialVersionUID = -506728236497380327L;

    private AppealPO appealPO;
    private ApplyPO applyPO;

    public AppealApplyDTO() {

    }

    public AppealApplyDTO(AppealPO appealPO) {
        ApplyPO applyPO = new ApplyPO();
        this.appealPO = appealPO;
        this.applyPO = applyPO;
    }

    public AppealApplyDTO(ApplyPO applyPO) {
        AppealPO appealPO = new AppealPO();
        this.appealPO = appealPO;
        this.applyPO = applyPO;
    }

    public AppealApplyDTO(AppealPO appealPO, ApplyPO applyPO) {
        this.appealPO = appealPO;
        this.applyPO = applyPO;
    }
}
