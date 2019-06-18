package com.xshhope.apply.convert;


import com.xshhope.apply.model.dto.AppealDTO;
import com.xshhope.model.apply.AppealPO;

/**
 * @author xshhope
 */
public class AppealConvert implements Convert<AppealPO, AppealDTO> {
    @Override
    public AppealDTO apply(AppealPO source) {
        AppealDTO appealDTO = new AppealDTO();
        appealDTO.setId(source.getId());
        appealDTO.setTitle(source.getTitle());
        appealDTO.setAppealCount(source.getAppealCount());
        appealDTO.setState(source.getAppealState());
        appealDTO.setGoal(source.getGoal());
        appealDTO.setCompleted(source.getCompleted());
        appealDTO.setGmtModified(source.getGmtModified());
        appealDTO.setType(source.getAppealType());

        return appealDTO;
    }
}
