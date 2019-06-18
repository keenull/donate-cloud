package com.xshhope.model.apply;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xshhope
 */

@Entity
@Table(name = "dt_appeal")
@Data
public class AppealPO implements Serializable{

    private static final long serialVersionUID = -2918010588460674077L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apply_id")
    private Long applyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "appeal_type")
    private Integer appealType;

    @Column(name = "appeal_state")
    private Integer appealState;

    @Column(name = "appeal_verify")
    private Integer appealVerify;

    @Column(name = "appeal_count")
    private Long appealCount;

    @Column(name = "file")
    private String file;

    @Column(name = "goal")
    private Double goal;

    @Column(name = "completed")
    private Double completed;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "is_deleted")
    private Integer deleted;
}
