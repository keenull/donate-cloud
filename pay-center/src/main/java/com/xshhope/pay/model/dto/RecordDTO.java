package com.xshhope.pay.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class RecordDTO {

    private String donater;

    private String receiver;

    private Double money;

    private String projectName;

    private Date gmtCreated;
}
