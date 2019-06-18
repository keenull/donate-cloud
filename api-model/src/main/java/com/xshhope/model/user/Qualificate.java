package com.xshhope.model.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xshhope
 */
@Data
public class Qualificate implements Serializable{

    private static final long serialVersionUID = 1838730810248662527L;

    private Long id;

    private Long userId;

    private String colleague;

    private String school;

    private String studentId;

    private String img;

    private String file;

    private Integer deleted;

    private Integer passed;
}
