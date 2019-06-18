package com.xshhope.apply.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class BulletinListDTO {
    private Long id;

    private String createdUser;

    private String modifiedUser;

    private String title;

    private String content;

    private Date gmtCreated;

    private Date gmtModified;

    private Integer state;
}
