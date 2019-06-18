package com.xshhope.pay.service;


import com.xshhope.model.pay.PayPO;

import java.util.List;

/**
 * @author xshhope
 */
public interface PayService {
    PayPO getPayById(String id);

    void savePay(PayPO payPO);

    int changePayState(PayPO payById, int i);

    List<PayPO> getByState(Integer state);

    List<PayPO> getLastWeek();
}
