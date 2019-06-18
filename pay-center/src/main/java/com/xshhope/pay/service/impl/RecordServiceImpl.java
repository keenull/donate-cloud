package com.xshhope.pay.service.impl;

import com.google.common.collect.Lists;
import com.xshhope.model.apply.ApplyPO;
import com.xshhope.model.user.AppUser;
import com.xshhope.pay.feign.AppealClient;
import com.xshhope.pay.feign.UserClient;
import com.xshhope.pay.model.dto.DonateListDTO;
import com.xshhope.pay.model.dto.RecordListDTO;
import com.xshhope.model.pay.PayPO;
import com.xshhope.model.pay.RecordPO;
import com.xshhope.pay.repository.RecordRepository;
import com.xshhope.pay.service.RecordService;
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
import java.util.UUID;

/**
 * @author xshhope
 */
@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppealClient appealClient;

    @Autowired
    private UserClient userClient;

    @Transactional
    @Override
    public void saveRecordByPay(PayPO payPO) {
        AppUser donater = userClient.findUserByIdd(payPO.getCreatedUserId());
        AppUser receiver = userClient.findUserByIdd(payPO.getReceiver());
        ApplyPO applyPO = appealClient.getApplyByIdd(payPO.getAppealId());
        RecordPO recordPO = new RecordPO();
        recordPO.setGoal(applyPO.getGoal());
        recordPO.setCreatedUserId(payPO.getCreatedUserId());
        recordPO.setCreatedUserName(donater.getUsername());
        recordPO.setReceiver(payPO.getReceiver());
        recordPO.setReceiverName(receiver.getUsername());
        recordPO.setPayOrder(payPO.getId());
        recordPO.setTitle(payPO.getTitle());
        recordPO.setMoney(payPO.getMoney());
        recordPO.setRecordState(0);
        recordPO.setGmtCreated(new Date());
        recordPO.setGmtModified(new Date());
        recordPO.setDeleted(0);
        recordRepository.saveAndFlush(recordPO);
    }

    @Override
    public List<RecordPO> listRecord(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<RecordPO> bulletinPOS = orderDirection.equalsIgnoreCase("desc") ? this.recordRepository.listRecordDESC(search, orderField, pageReq) : recordRepository.listRecordASC(search, orderField, pageReq);
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countRecords() {
        return recordRepository.countRecords();
    }

    @Override
    public List<RecordListDTO> recordListDTO(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<RecordListDTO> bulletinPOS = orderDirection.equalsIgnoreCase("desc") ? this.recordRepository.recordListDTOPageDesc(search, orderField, pageReq) : recordRepository.recordListDTOPageAsc(search, orderField, pageReq);
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<DonateListDTO> listDonate(int page, int size, Integer type, String search, String begin, String end, String desc) {

        List<DonateListDTO> bulletinPOS = Lists.newArrayList();

        if (begin.equals("") || end.equals("")) {
            if (type.equals(1)) {
                bulletinPOS = this.recordRepository.listDonateByReceiverOnly(search, PageRequest.of(page - 1, size));
            }
            if (type.equals(2)) {
                bulletinPOS = this.recordRepository.listDonateByProjectOnly(search, PageRequest.of(page - 1, size));
            }
            if (type.equals(0)) {
                bulletinPOS = this.recordRepository.listDonateOnly(search, PageRequest.of(page - 1, size));
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
                bulletinPOS = this.recordRepository.listDonateByReceiver(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
            if (type.equals(2)) {
                bulletinPOS = this.recordRepository.listDonateByProject(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
            if (type.equals(0)) {
                bulletinPOS = this.recordRepository.listDonate(search, beginTime, endTime, PageRequest.of(page - 1, size));
            }
        }
        return Optional.of(bulletinPOS).orElse(Lists.newArrayList());
    }

    @Override
    public List<DonateListDTO> getDontateByUser(Long userId, int page, int size, String search, String begin, String end, String desc) {
        List<DonateListDTO> donateListDTOS = Lists.newArrayList();


        donateListDTOS = this.recordRepository.getDontateByUser(userId, PageRequest.of(page - 1, size));


        return Optional.of(donateListDTOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countDontateByUser(Long userId) {
        return recordRepository.countDontateByUser(userId);
    }
}
