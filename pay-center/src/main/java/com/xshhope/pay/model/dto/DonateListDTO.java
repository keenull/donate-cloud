package com.xshhope.pay.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class DonateListDTO {
    private Long id;
    private String userName;
    private String receiver;
    private String title;
    private Double money;
    private Date gmtCreated;

    public DonateListDTO(Long id, String userName, String receiver, String title, Double money, Date gmtCreated) {
        this.id = id;
        this.userName = userName;
        this.receiver = receiver;
        this.title = title;
        this.money = money;
        this.gmtCreated = gmtCreated;
    }
}
