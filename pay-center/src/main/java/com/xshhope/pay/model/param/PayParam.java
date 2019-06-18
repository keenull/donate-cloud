package com.xshhope.pay.model.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xshhope
 */
@Data
public class PayParam {
    private String id;
    private Long appealId;
    private Long payType;
    private String title;
    private String mail;
    private BigDecimal money;
    private Boolean custom;
    private String comment;
    private String orderId;
}
