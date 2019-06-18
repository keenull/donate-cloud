package com.xshhope.apply.convert;


import com.xshhope.apply.model.dto.ApplyDTO;
import com.xshhope.model.apply.ApplyPO;

/**
 * @author xshhope
 */
public class ApplyConvert implements Convert<ApplyPO, ApplyDTO> {
    @Override
    public ApplyDTO apply(ApplyPO source) {
        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setId(source.getId());
        applyDTO.setUserId(source.getUserId());
        applyDTO.setUserName(source.getUserName());
        applyDTO.setTitle(source.getTitle());
        applyDTO.setContent(source.getContent());
        applyDTO.setApplyType(source.getApplyType());
        applyDTO.setApplyState(source.getApplyState());
        applyDTO.setGoal(source.getGoal());
        applyDTO.setCompleted(source.getCompleted());
        applyDTO.setGmtCreated(source.getGmtCreated());
        applyDTO.setGmtModified(source.getGmtModified());

        return applyDTO;
    }
}
