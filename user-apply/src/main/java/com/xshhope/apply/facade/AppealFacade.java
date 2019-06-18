package com.xshhope.apply.facade;

import com.google.common.collect.Lists;
import com.xshhope.apply.model.dto.*;
import com.xshhope.apply.service.AppealService;
import com.xshhope.common.support.ActionResult;
import com.xshhope.common.support.ActionTemplate;
import com.xshhope.common.utils.AppUserUtil;
import com.xshhope.common.utils.Langs;
import com.xshhope.common.utils.Pager;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author xshhope
 */
@Service
public class AppealFacade {

    @Autowired
    private AppealService appealService;

    public Pager<AppealDTO> listPerson(int page, int size, String search, String orderField, String orderDirection) {
        List<AppealDTO> appealDTOS = appealService.listPersonVerified(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPersonCompleted(int page, int size, String search, String orderField, String orderDirection) {
        List<AppealDTO> appealDTOS = appealService.listPersonVerifiedCompleted(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPersonVerified(int page, int size, String search, String begin, String end, String desc) {
        List<AppealDTO> appealDTOS = appealService.listPersonVerified(page, size, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPersonUnVerified(int page, int size, String search, String begin, String end, String desc) {
        List<AppealDTO> appealDTOS = appealService.listPersonUnVerified(page, size, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPublicVerified(int page, int size, String search, String orderField, String orderDirection) {

        List<AppealDTO> appealDTOS = appealService.listPublicVerified(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPublicVerifiedCompleted(int page, int size, String search, String orderField, String orderDirection) {

        List<AppealDTO> appealDTOS = appealService.listPublicVerifiedCompleted(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPublicVerified(int page, int size, String search, String begin, String end, String desc) {

        List<AppealDTO> data = Lists.newArrayList();
        List<AppealDTO> appealDTOS = appealService.listPublicVerified(page, size, search, begin, end, desc);
        Long total = appealService.countPublicVerified(search, begin, end);

        appealDTOS.forEach(x -> {

            data.add(x);
        });
        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(data);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listPublicUnVerified(int page, int size, String search, String begin, String end, String desc) {

        List<AppealDTO> appealDTOS = appealService.listPublicUnVerified(page, size, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public AppealPO getPublicAppealById(Long id) {
        return appealService.getPublicAppealById(id);
    }

    public AppealDetailDTO getPersonAppealById(Long id) {

        AppealDetailDTO appealPO = appealService.getPersonAppealById(id);
        return appealPO;
    }

    public Pager<RecordCountDTO> donateCount(int page, int size, String search, String orderField, String orderDirection) {

        List<RecordCountDTO> appealDTOS = appealService.donateCount(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<RecordCountDTO> pager = new Pager<RecordCountDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<RecordCountDTO> donateAdminCount(int page, int size, Integer type, String search, String begin, String end, String desc) {

        if (Langs.isNull(type)) {
            type = 0;
        }

        List<RecordCountDTO> appealDTOS = appealService.donateAdminCount(page, size, type, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<RecordCountDTO> pager = new Pager<RecordCountDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    @Transactional
    public ActionResult approve(List<Long> applyIds) {
        AppUser user = AppUserUtil.getLoginAppUser();
        if (!Langs.isEmpty(applyIds)) {
            appealService.approve(user.getUsername(), user.getId(), applyIds);
            return ActionTemplate.toAck("审批成功");
        }
        return ActionTemplate.toNack("审批未成功");
    }

    @Transactional
    public ActionResult deny(List<Long> applyIds) {
        AppUser user = AppUserUtil.getLoginAppUser();
        if (!Langs.isEmpty(applyIds)) {
            appealService.deny(user.getUsername(), user.getId(), applyIds);
            return ActionTemplate.toAck("审批成功");
        }
        return ActionTemplate.toNack("审批未成功");
    }

    @Transactional
    public ActionResult<Boolean> approve(Long id) {
        AppUser user = AppUserUtil.getLoginAppUser();
        if (!Langs.isEmpty(id)) {
            appealService.approve(user.getUsername(), user.getId(), id);
            return ActionTemplate.toAck("审批成功");
        }
        return ActionTemplate.toNack("审批未成功");
    }

    @Transactional
    public ActionResult<Boolean> deny(Long id) {
        AppUser user = AppUserUtil.getLoginAppUser();
        if (!Langs.isEmpty(id)) {
            appealService.deny(user.getUsername(), user.getId(), id);
            return ActionTemplate.toAck("审批成功");
        }
        return ActionTemplate.toNack("审批未成功");
    }

    public Pager<PersonApplyDTO> getAppealByUser(int page, int size, String search, String begin, String end, String desc) {
        AppUser user = AppUserUtil.getLoginAppUser();

        List<PersonApplyDTO> appealDTOS = appealService.getAppealByUser(user.getId(), page, size, search, begin, end, desc);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<PersonApplyDTO> pager = new Pager<PersonApplyDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public Pager<AppealDTO> listCompleted(int page, int size, String search, String orderField, String orderDirection) {
        List<AppealDTO> appealDTOS = appealService.listCompleted(search, orderField, orderDirection, page, size);
        Long total = Long.valueOf(appealDTOS.size());

        Pager<AppealDTO> pager = new Pager<AppealDTO>();
        pager.setData(appealDTOS);
        pager.setTotalElements(total);
        return pager;
    }

    public AppealPO getAppealById(Long id) {
        return appealService.getAppealById(id);
    }

    public List<AppealDetailVO> pageDetail() {
        List<AppealPO> appealPOS = appealService.pageDetail();
        List<AppealDetailVO> appealDetailVOS = Lists.newArrayList();

        appealPOS.forEach(x ->{
            AppealDetailVO appealDetailVO = new AppealDetailVO();
            appealDetailVO.setId(x.getId());
            appealDetailVO.setFile(x.getFile());
            appealDetailVO.setTitle(x.getTitle());
            appealDetailVO.setType(x.getAppealType());
            appealDetailVOS.add(appealDetailVO);
        });

        return Optional.of(appealDetailVOS).orElse(Lists.newArrayList());
    }
}
