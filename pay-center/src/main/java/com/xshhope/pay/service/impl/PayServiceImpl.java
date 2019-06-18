package com.xshhope.pay.service.impl;

import com.xshhope.model.pay.PayPO;
import com.xshhope.pay.repository.PayRepository;
import com.xshhope.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xshhope
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository payRepository;

    @Override
    public PayPO getPayById(String id) {
        return payRepository.getPayById(id);
    }


    @Transactional
    @Override
    public void savePay(PayPO payPO) {
        payRepository.save(payPO);
    }

    @Transactional
    @Override
    public int changePayState(PayPO payById, int i) {
        payById.setPayState(i);
        payRepository.saveAndFlush(payById);
        return 0;
    }

    @Override
    public List<PayPO> getByState(Integer state) {
        return payRepository.getByState(state);
    }

    @Override
    public List<PayPO> getLastWeek() {
        return payRepository.getLastWeek();
    }
}
