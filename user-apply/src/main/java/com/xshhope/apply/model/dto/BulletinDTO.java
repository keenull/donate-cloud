package com.xshhope.apply.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class BulletinDTO {
    private Long id;

    private Long createdUserId;

    private Long modifiedUserId;

    private String title;

    private String content;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer state;
}
