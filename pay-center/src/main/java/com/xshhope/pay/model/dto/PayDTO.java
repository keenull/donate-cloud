package com.xshhope.pay.model.dto;

import lombok.Data;

/**
 * @author xshhope
 */
@Data
public class PayDTO {
    private String id;

    private Long createdUserId;

    private Double money;

    private Integer payState;

    private String payNum;

    private String comment;

    private String passUrl;

    /**
     * 含小程序
     */
    private String passUrl2;

    /**
     * 含xboot
     */
    private String passUrl3;

    private String backUrl;

    private String passNotShowUrl;

    private String editUrl;

    private String delUrl;

    private String closeUrl;

    private String openUrl;
}
