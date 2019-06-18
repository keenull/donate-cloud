package com.xshhope.apply.service;


import com.xshhope.apply.model.dto.PersonApplyDTO;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.model.apply.ApplyPO;

import java.util.Date;
import java.util.List;

/**
 * @author xshhope
 */
public interface ApplyService {
    List<ApplyPO> listApplys(String search, String orderField, String orderDirection, int page, int size);

    Long countApplys();

    ApplyPO getApplyById(Long id);

    ApplyPO updateApply(ApplyPO applyPO);

    void update(ApplyEditParam param);

    void delete(Long userId, Date date);

    void approveApply(Long applyId);

    void denyApply(Long applyId);

    void save(ApplyPO applyPO);

    void saveAll(List<ApplyPO> applyPOS);

    List<PersonApplyDTO> getApplyByUser(Long id, int page, int size, String search, String begin, String end, String desc);
}
