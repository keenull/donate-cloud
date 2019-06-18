package com.xshhope.apply.service.impl;

import com.google.common.collect.Lists;
import com.xshhope.apply.model.dto.AppealApplyDTO;
import com.xshhope.apply.model.dto.PersonApplyDTO;
import com.xshhope.apply.model.param.ApplyEditParam;
import com.xshhope.apply.repository.AppealRepository;
import com.xshhope.apply.repository.ApplyRepository;
import com.xshhope.apply.service.AppealService;
import com.xshhope.apply.service.ApplyService;
import com.xshhope.model.apply.AppealPO;
import com.xshhope.model.apply.ApplyPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xshhope
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private AppealRepository appealRepository;

    @Override
    public List<ApplyPO> listApplys(String search, String orderField, String orderDirection, int page, int size) {
        Sort.Order order = null;
        if ("desc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.DESC, orderField);
        } else if ("asc".equalsIgnoreCase(orderDirection)) {
            order = new Sort.Order(Sort.Direction.ASC, orderField);
        }

        PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by(order));

        List<ApplyPO> applyPOS = orderDirection.equalsIgnoreCase("desc") ? this.applyRepository.listApplyDESC(search, orderField, pageReq) : applyRepository.listApplyASC(search, orderField, pageReq);
        return Optional.of(applyPOS).orElse(Lists.newArrayList());
    }

    @Override
    public Long countApplys() {
        return applyRepository.countApplys();
    }

    @Override
    public ApplyPO getApplyById(Long id) {
        return applyRepository.getApplyById(id);
    }

    @Override
    public ApplyPO updateApply(ApplyPO applyPO) {
        return applyRepository.save(applyPO);
    }

    @Transactional
    @Override
    public void update(ApplyEditParam param) {
        applyRepository.update(param.getState(),param.getGoal(),param.getTitle(),new Date(), param.getId());
    }

    @Transactional
    @Override
    public void delete(Long id, Date date) {
        applyRepository.delete(id, date);
    }


    @Override
    public void approveApply(Long applyId) {
        applyRepository.approveApply(applyId, new Date());
    }

    @Override
    public void denyApply(Long applyId) {
        applyRepository.denyApply(applyId, new Date());
    }

    @Transactional
    @Override
    public void save(ApplyPO applyPO) {
        applyRepository.save(applyPO);
    }

    @Transactional
    @Override
    public void saveAll(List<ApplyPO> list){
        applyRepository.saveAll(list);
    }

    @Override
    public List<PersonApplyDTO> getApplyByUser(Long userId, int page, int size, String search, String begin, String end, String desc) {
        List<PersonApplyDTO> personApplyDTOS = Lists.newArrayList();

        List<ApplyPO> applyPOS = this.applyRepository.getApplyByUser(userId, search, PageRequest.of(page - 1, size));

        applyPOS.forEach( x -> {
            AppealPO appealPO = appealRepository.getAppealByApplyId(x.getId());
            PersonApplyDTO personApplyDTO = new PersonApplyDTO();
            personApplyDTO.setId(x.getId());
            personApplyDTO.setTitle(x.getTitle());
            personApplyDTO.setGoal(x.getGoal());
            personApplyDTO.setCompleted(appealPO.getCompleted());
            personApplyDTO.setGmtCreated(x.getGmtCreated());
            personApplyDTO.setState(x.getApplyState());

            personApplyDTOS.add(personApplyDTO);
        });

        return Optional.of(personApplyDTOS).orElse(Lists.newArrayList());
    }
}
