package com.xshhope.apply.service;


import com.xshhope.apply.model.dto.AppealDTO;
import com.xshhope.apply.model.dto.AppealDetailDTO;
import com.xshhope.apply.model.dto.PersonApplyDTO;
import com.xshhope.apply.model.dto.RecordCountDTO;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;

import java.util.Date;
import java.util.List;

/**
 * @author xshhope
 */
public interface AppealService {
    List<AppealDTO> listPersonVerified(String search, String orderField, String orderDirection, int page, int size);

    List<AppealDTO> listPersonVerifiedCompleted(String search, String orderField, String orderDirection, int page, int size);

    List<AppealDTO> listPersonVerified(int page, int size, String search, String begin, String end, String desc);

    Long countPersonAppeal();

    List<AppealDTO> listPublicVerified(String search, String orderField, String orderDirection, int page, int size);

    List<AppealDTO> listPublicVerifiedCompleted(String search, String orderField, String orderDirection, int page, int size);

    List<AppealDTO> listPublicVerified(int page, int size, String search, String begin, String end, String desc);

    Long countPublicAppeal();

    AppealPO getPublicAppealById(Long id);

    AppealDetailDTO getPersonAppealById(Long id);

    AppealPO saveAppealPO(ApplyPO applyPO);

    AppealPO getAppealById(Long id);

    List<AppealDTO> listPersonUnVerified(int page, int size, String search, String begin, String end, String desc);

    List<AppealDTO> listPublicUnVerified(int page, int size, String search, String begin, String end, String desc);

    Long countPublicUnVerified();

    Long countPublicVerified();

    Long countPersonVerified();

    Long countPersonUnVerified();

    List<RecordCountDTO> donateCount(String search, String orderField, String orderDirection, int page, int size);

    Long countAppeal();

    Long countPersonVerified(String search, String begin, String end);

    Long countPersonUnVerified(String search, String begin, String end);

    Long countPublicVerified(String search, String begin, String end);

    Long countPublicUnVerified(String search, String begin, String end);

    List<RecordCountDTO> donateAdminCount(int page, int size, Integer type, String search, String begin, String end, String desc);

    void update(ApplyEditParam param);

    void approve(String userName, Long id, List<Long> applyIds);

    void deny(String userName, Long id, List<Long> applyIds);

    void approve(String userName, Long id, Long applyId);

    void deny(String userName, Long id, Long applyId);

    void delete(Long id, Date date);

    List<PersonApplyDTO> getAppealByUser(Long id, int page, int size, String search, String begin, String end, String desc);

    List<AppealDTO> listCompleted(String search, String orderField, String orderDirection, int page, int size);

    void saveAppeal(AppealPO appealPO);

    List<AppealPO> listAppeals();

    void saveAll(List<AppealPO> appealPOS);

    List<AppealPO> getLastWeek();

    List<AppealPO> pageDetail();
}
