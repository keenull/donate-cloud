package com.xshhope.apply.model.dto;

import lombok.Data;

/**
 * @author xshhope
 */
@Data
public class AppealDetailDTO {
    private Long id;

    private String title;

    private String content;

    private Long appealCount;

    private Double completed;

    private Double goal;

    private Integer state;

    private String file;
}
