package com.xshhope.apply.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xshhope
 */
@Data
public class AppealDTO {

    private Long id;

    private Integer type;

    private String title;

    private Long appealCount;

    private Double goal;

    private Double completed;

    private Integer state;

    private Date gmtModified;

    private String file;

    public AppealDTO() {
    }

    public AppealDTO(Long id, String title, Long appealCount, Double goal, Double completed, Integer state, Date gmtModified, String file) {
        this.id = id;
        this.title = title;
        this.appealCount = appealCount;
        this.goal = goal;
        this.completed = completed;
        this.state = state;
        this.gmtModified = gmtModified;
        this.file = file;
    }

    public AppealDTO(Long id, String title, Long appealCount, Double goal, Double completed, Integer state, Date gmtModified) {
        this.id = id;
        this.title = title;
        this.appealCount = appealCount;
        this.goal = goal;
        this.completed = completed;
        this.state = state;
        this.gmtModified = gmtModified;
    }
}
