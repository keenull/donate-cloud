package com.xshhope.apply.service.impl;

import com.google.common.collect.Lists;
import com.xshhope.apply.convert.AppealConvert;
import com.xshhope.apply.convert.AppealDetailConvert;
import com.xshhope.apply.model.dto.*;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.apply.repository.AppealRepository;
import com.xshhope.apply.repository.ApplyRepository;
import com.xshhope.apply.service.AppealService;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xshhope
 */
@Slf4j
@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealRepository appealRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Override
    public List<AppealDTO> listPersonVerified(String search, String orderField, String orderDirection, int page, int size) {
        List<AppealDTO> appealDTOList = Lists.newArrayList();
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<AppealPO> appealPOS = orderDirection.equalsIgnoreCase("desc") ? this.appealRepository.listPersonVerifiedDescT(search, orderField, pageReq) : appealRepository.listPersonVerifiedAscT(search, orderField, pageReq);

        appealPOS.forEach(x -> {
            String file = applyRepository.getApplyById(x.getApplyId()).getFile();
            AppealDTO apply = new AppealConvert().apply(x);
            apply.setFile(file);
            appealDTOList.add(apply);
        });

        return Optional.ofNullable(appealDTOList).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listPersonVerifiedCompleted(String search, String orderField, String orderDirection, int page, int size) {
        List<AppealDTO> appealDTOList = Lists.newArrayList();
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<AppealPO> appealPOS = orderDirection.equalsIgnoreCase("desc") ? this.appealRepository.listPersonVerifiedCompletedDesc(search, orderField, pageReq) : appealRepository.listPersonVerifiedCompletedAsc(search, orderField, pageReq);

        appealPOS.forEach(x -> {
            String file = applyRepository.getApplyById(x.getApplyId()).getFile();
            AppealDTO apply = new AppealConvert().apply(x);
            apply.setFile(file);
            appealDTOList.add(apply);
        });

        return Optional.ofNullable(appealDTOList).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listPersonVerified(int page, int size, String search, String begin, String end, String desc) {

        List<AppealDTO> appealDTOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            appealDTOS = this.appealRepository.listPersonVerifiedOnlyDesc(search, PageRequest.of(page - 1, size));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }

            appealDTOS = this.appealRepository.listPersonVerifiedDesc(search, beginTime, endTime, PageRequest.of(page - 1, size));
        }

        return Optional.ofNullable(appealDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countPersonAppeal() {
        return appealRepository.countPersonAppeal();
    }

    @Override
    public Long countPersonVerified() {
        return appealRepository.countPersonVerified();
    }

    @Override
    public Long countPersonUnVerified() {
        return appealRepository.countPersonUnVerified();
    }

    @Override
    public List<AppealDTO> listPublicVerified(String search, String orderField, String orderDirection, int page, int size) {
        List<AppealDTO> appealDTOList = Lists.newArrayList();
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<AppealPO> appealPOS = orderDirection.equalsIgnoreCase("desc") ? this.appealRepository.listPublicVerifiedDesc(search, orderField, pageReq) : appealRepository.listPublicVerifiedAsc(search, orderField, pageReq);

        appealPOS.forEach(x -> {
            String file = applyRepository.getApplyById(x.getApplyId()).getFile();
            AppealDTO apply = new AppealConvert().apply(x);
            apply.setFile(file);
            appealDTOList.add(apply);
        });

        return Optional.of(appealDTOList).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listPublicVerifiedCompleted(String search, String orderField, String orderDirection, int page, int size) {
        List<AppealDTO> appealDTOList = Lists.newArrayList();
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<AppealPO> appealPOS = orderDirection.equalsIgnoreCase("desc") ? this.appealRepository.listPublicVerifiedCompletedDesc(search, orderField, pageReq) : appealRepository.listPublicVerifiedCompletedAsc(search, orderField, pageReq);

        appealPOS.forEach(x -> {
            String file = applyRepository.getApplyById(x.getApplyId()).getFile();
            AppealDTO apply = new AppealConvert().apply(x);
            apply.setFile(file);
            appealDTOList.add(apply);
        });

        return Optional.of(appealDTOList).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listPublicVerified(int page, int size, String search, String begin, String end, String desc) {
        List<AppealDTO> appealDTOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            appealDTOS = this.appealRepository.listPublicVerifiedOnlyDesc(search, PageRequest.of(page - 1, size));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }

            appealDTOS = this.appealRepository.listPublicVerifiedDesc(search, beginTime, endTime, PageRequest.of(page - 1, size));
        }

        return Optional.of(appealDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countPublicAppeal() {
        return appealRepository.countPublicAppeal();
    }

    @Override
    public Long countPublicUnVerified() {
        return appealRepository.countPublicUnVerified();
    }

    @Override
    public Long countPublicVerified() {
        return appealRepository.countPublicVerified();
    }

    @Override
    public AppealPO getPublicAppealById(Long id) {
        return appealRepository.getPublicAppealById(id);
    }

    @Override
    public AppealDetailDTO getPersonAppealById(Long id) {
        AppealPO appealPO = appealRepository.getPersonAppealById(id);
        String file = applyRepository.getApplyById(appealPO.getApplyId()).getFile();
        AppealDetailDTO appealDetailDTO = new AppealDetailConvert().apply(appealPO);
        appealDetailDTO.setFile(file);
        return appealDetailDTO;
    }

    @Transactional
    @Override
    public AppealPO saveAppealPO(ApplyPO applyPO) {
        // appeal的 userid 和 username 是审批人的
        AppealPO appealPO = new AppealPO();
        appealPO.setApplyId(applyPO.getId());
        /**
         * 捐款次数
         */
        appealPO.setAppealCount(0L);
        appealPO.setGmtCreated(new Date());
        appealPO.setCompleted(0.0);
        appealPO.setAppealVerify(1);

        appealPO.setGmtModified(new Date());
        appealPO.setDeleted(applyPO.getDeleted());
        appealPO.setTitle(applyPO.getTitle());
        appealPO.setContent(applyPO.getContent());
        appealPO.setAppealType(applyPO.getApplyType());
        appealPO.setAppealState(applyPO.getApplyState());
        appealPO.setGoal(applyPO.getGoal());

        appealPO.setFile(applyPO.getFile());

        appealPO = appealRepository.save(appealPO);

        return appealPO;
    }

    @Override
    public AppealPO getAppealById(Long id) {
        return appealRepository.getAppealById(id);
    }

    @Override
    public List<AppealDTO> listPersonUnVerified(int page, int size, String search, String begin, String end, String desc) {
        List<AppealDTO> appealDTOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            appealDTOS = this.appealRepository.listPersonUnVerifiedOnlyDesc(search, PageRequest.of(page - 1, size));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }

            appealDTOS = this.appealRepository.listPersonUnVerifiedDesc(search, beginTime, endTime, PageRequest.of(page - 1, size));
        }

        return Optional.of(appealDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listPublicUnVerified(int page, int size, String search, String begin, String end, String desc) {

        List<AppealDTO> appealDTOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            appealDTOS = this.appealRepository.listPublicUnVerifiedOnlyDesc(search, PageRequest.of(page - 1, size));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }

            appealDTOS = this.appealRepository.listPublicUnVerifiedDesc(search, beginTime, endTime, PageRequest.of(page - 1, size));
        }

        return Optional.of(appealDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<RecordCountDTO> donateCount(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<RecordCountDTO> donateCountDTOList = orderDirection.equalsIgnoreCase("desc") ? this.appealRepository.donateCountDesc(orderField, pageReq) : appealRepository.donateCountAsc(orderField, pageReq);

        return Optional.of(donateCountDTOList).orElse(Lists.newArrayList());
    }

    @Override
    public Long countAppeal() {
        return appealRepository.countAppeal();
    }

    @Override
    public Long countPersonVerified(String search, String begin, String end) {
        Long total = 0L;
        if (begin.equals("") || end.equals("")) {
            total = this.appealRepository.countPersonVerifiedOnly(search);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }
            total = this.appealRepository.countPersonVerified(search, beginTime, endTime);
        }
        return total;
    }

    @Override
    public Long countPersonUnVerified(String search, String begin, String end) {
        Long total = 0L;
        if (begin.equals("") || end.equals("")) {
            total = this.appealRepository.countPersonUnVerifiedOnly(search);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }
            total = this.appealRepository.countPersonUnVerified(search, beginTime, endTime);
        }
        return total;
    }

    @Override
    public Long countPublicVerified(String search, String begin, String end) {
        Long total = 0L;
        if (begin.equals("") || end.equals("")) {
            total = this.appealRepository.countPublicVerifiedOnly(search);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }
            total = this.appealRepository.countPublicVerified(search, beginTime, endTime);
        }
        return total;
    }

    @Override
    public Long countPublicUnVerified(String search, String begin, String end) {
        Long total = 0L;
        if (begin.equals("") || end.equals("")) {
            total = this.appealRepository.countPublicUnVerifiedOnly(search);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }
            total = this.appealRepository.countPublicUnVerified(search, beginTime, endTime);
        }
        return total;
    }

    @Override
    public List<RecordCountDTO> donateAdminCount(int page, int size, Integer type, String search, String begin, String end, String desc) {
        List<RecordCountDTO> recordCountDTOS = Lists.newArrayList();

        List<AppealApplyDTO> appealApplyDTOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            if (type.equals(1)) {
                appealApplyDTOS = this.appealRepository.listDonateByReceiverOnly(search, PageRequest.of(page - 1, size));
            }
            if (type.equals(2)) {
                appealApplyDTOS = this.appealRepository.listDonateByProjectOnly(search, PageRequest.of(page - 1, size));
            }
            if (type.equals(0)) {
                appealApplyDTOS = this.appealRepository.listDonateOnly(search, PageRequest.of(page - 1, size));
            }
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = new Date();
            Date endTime = new Date();
            try {
                beginTime = simpleDateFormat.parse(begin);
                endTime = simpleDateFormat.parse(end);
            } catch (ParseException e) {
                log.info("时间转换失败");
            }

            if (type.equals(1)) {
                appealApplyDTOS = this.appealRepository.listDonateByReceiver(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
            if (type.equals(2)) {
                appealApplyDTOS = this.appealRepository.listDonateByProject(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
            if (type.equals(0)) {
                appealApplyDTOS = this.appealRepository.listDonate(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
        }

        appealApplyDTOS.forEach(x -> {
            RecordCountDTO recordCountDTO = new RecordCountDTO();
            recordCountDTO.setId(x.getAppealPO().getId());
            recordCountDTO.setReceiver(x.getApplyPO().getUserName());
            recordCountDTO.setTitle(x.getAppealPO().getTitle());
            recordCountDTO.setCompleted(x.getAppealPO().getCompleted());
            recordCountDTO.setGoal(x.getAppealPO().getGoal());
            recordCountDTO.setGmtCreated(x.getAppealPO().getGmtCreated());
            recordCountDTO.setGmtModified(x.getAppealPO().getGmtModified());
            recordCountDTO.setState(x.getAppealPO().getAppealState());


            recordCountDTOS.add(recordCountDTO);
        });

        return Optional.of(recordCountDTOS).orElse(Lists.newArrayList());
    }

    @Transactional
    @Override
    public void update(ApplyEditParam param) {
        appealRepository.update(param.getState(),param.getGoal(),param.getTitle(), new Date(), param.getId());
    }

    @Transactional
    @Override
    public void approve(String userName, Long id, List<Long> applyIds) {
        appealRepository.approve(userName, id, applyIds);
    }

    @Transactional
    @Override
    public void deny(String userName, Long id, List<Long> applyIds) {
        appealRepository.deny(userName, id, applyIds);
    }

    @Transactional
    @Override
    public void approve(String userName, Long id, Long applyId) {
        appealRepository.approve(userName, id, applyId);
    }

    @Transactional
    @Override
    public void deny(String userName, Long id, Long applyId) {
        appealRepository.deny(userName, id, applyId);
    }

    @Transactional
    @Override
    public void delete(Long id, Date date) {
        appealRepository.delete(id, date);
    }

    @Override
    public List<PersonApplyDTO> getAppealByUser(Long userId, int page, int size, String search, String begin, String end, String desc) {
        List<PersonApplyDTO> personApplyDTOS = Lists.newArrayList();

        List<AppealApplyDTO> appealApplyDTOS;

        appealApplyDTOS = this.appealRepository.getAppealByUser(userId, search, PageRequest.of(page - 1, size));

        appealApplyDTOS.forEach( x -> {
            PersonApplyDTO personApplyDTO = new PersonApplyDTO();
            personApplyDTO.setId(x.getApplyPO().getId());
            personApplyDTO.setTitle(x.getApplyPO().getTitle());
            personApplyDTO.setGoal(x.getApplyPO().getGoal());
            personApplyDTO.setCompleted(x.getAppealPO().getCompleted());
            personApplyDTO.setGmtCreated(x.getApplyPO().getGmtCreated());

            personApplyDTOS.add(personApplyDTO);
        });

        return Optional.of(personApplyDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<AppealDTO> listCompleted(String search, String orderField, String orderDirection, int page, int size) {
        List<AppealDTO> appealDTOList = Lists.newArrayList();
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<AppealPO> appealPOS = this.appealRepository.listCompleted(search, orderField, pageReq);

        appealPOS.forEach(x -> {
            appealDTOList.add(new AppealConvert().apply(x));
        });

        return Optional.ofNullable(appealDTOList).orElse(Lists.newArrayList());
    }

    @Transactional
    @Override
    public void saveAppeal(AppealPO appealPO) {
        appealRepository.save(appealPO);
    }

    @Override
    public List<AppealPO> listAppeals() {
        return appealRepository.listAppeals(0, 0);
    }

    @Transactional
    @Override
    public void saveAll(List<AppealPO> list) {
        appealRepository.saveAll(list);
    }

    @Override
    public List<AppealPO> getLastWeek() {
        return appealRepository.getLastWeek();
    }

    @Override
    public List<AppealPO> pageDetail() {
        return appealRepository.pageDetail();
    }
}
