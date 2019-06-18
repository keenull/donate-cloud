package com.xshhope.apply.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class ApplyDTO {
    private Long id;

    private Long userId;

    private String userName;

    private String title;

    private String content;

    private Integer applyType;

    private Integer applyState;

    private Double goal;

    private Integer completed;

    private Date gmtCreated;

    private Date gmtModified;
}
