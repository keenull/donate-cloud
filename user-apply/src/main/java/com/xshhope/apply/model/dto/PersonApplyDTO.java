package com.xshhope.apply.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class PersonApplyDTO {

    private Long id;
    private String title;
    private Double goal;
    private Double completed;
    private Date gmtCreated;
    private Integer state;
}
