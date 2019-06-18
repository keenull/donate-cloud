package com.xshhope.pay.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class RecordCountDTO {
    private Long id;
    private String receiver;
    private String title;
    private Double completed;
    private Double goal;
    private Date gmtCreated;
    private Date gmtModified;
    private Integer state;

    public RecordCountDTO() {
    }

    public RecordCountDTO(Long id, String receiver, String title, Double completed, Double goal, Date gmtCreated, Date gmtModified, Integer state) {
        this.id = id;
        this.receiver = receiver;
        this.title = title;
        this.completed = completed;
        this.goal = goal;
        this.gmtCreated = gmtCreated;
        this.gmtModified = gmtModified;
        this.state = state;
    }
}
