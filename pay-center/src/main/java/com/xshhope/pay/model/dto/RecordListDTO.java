package com.xshhope.pay.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class RecordListDTO {

    private Long rank;

    private String donater;

    private Double totalMoney;

    private Date latestTime;

    public RecordListDTO(Long rank, String donater, Double totalMoney, Date latestTime) {
        this.rank = rank;
        this.donater = donater;
        this.totalMoney = totalMoney;
        this.latestTime = latestTime;
    }
}
