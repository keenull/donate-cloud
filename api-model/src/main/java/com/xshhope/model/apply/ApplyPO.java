package com.xshhope.model.apply;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xshhope
 */
@Entity
@Table(name = "dt_apply")
@Data
public class ApplyPO implements Serializable{

    private static final long serialVersionUID = 8218914936358127464L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "verify_user_id")
    private Long verifyUserId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "apply_type")
    private Integer applyType;

    @Column(name = "apply_state")
    private Integer applyState;

    @Column(name = "goal")
    private Double goal;

    @Column(name = "completed")
    private Integer completed;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "file")
    private String file;

    @Column(name = "is_deleted")
    private Integer deleted;
}
