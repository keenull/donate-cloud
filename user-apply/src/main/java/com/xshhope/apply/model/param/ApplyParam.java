package com.xshhope.apply.model.param;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class ApplyParam {
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Integer applyType;

    private Integer applyState;

    private Double goal;

    private Double completed;

    private Date gmtCreated;

    private Date gmtModified;

    private String file;
}
