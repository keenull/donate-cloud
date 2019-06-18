package com.xshhope.apply.convert;


import com.xshhope.apply.model.dto.AppealDetailDTO;
import com.xshhope.model.apply.AppealPO;

/**
 * @author xshhope
 */
public class AppealDetailConvert implements Convert<AppealPO, AppealDetailDTO> {
    @Override
    public AppealDetailDTO apply(AppealPO source) {
        AppealDetailDTO appealDetailDTO = new AppealDetailDTO();
        appealDetailDTO.setId(source.getId());
        appealDetailDTO.setTitle(source.getTitle());
        appealDetailDTO.setContent(source.getContent());
        appealDetailDTO.setAppealCount(source.getAppealCount());
        appealDetailDTO.setCompleted(source.getCompleted());
        appealDetailDTO.setState(source.getAppealState());
        appealDetailDTO.setGoal(source.getGoal());
        appealDetailDTO.setFile(source.getFile());

        return appealDetailDTO;
    }
}
