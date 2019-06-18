package com.xshhope.pay.convert;


import com.xshhope.pay.model.dto.PayDTO;
import com.xshhope.model.pay.PayPO;

/**
 * @author xshhope
 */
public class PayConvert implements Convert<PayPO, PayDTO> {
    @Override
    public PayDTO apply(PayPO source) {
        PayDTO payDTO = new PayDTO();
        payDTO.setId(source.getId());
        payDTO.setCreatedUserId(source.getCreatedUserId());
        payDTO.setMoney(source.getMoney());
        payDTO.setPayState(source.getPayState());
        payDTO.setComment(source.getComment());
        payDTO.setPayNum(source.getPayNum());
        return payDTO;
    }
}
