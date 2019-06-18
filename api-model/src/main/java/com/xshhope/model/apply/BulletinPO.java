package com.xshhope.model.apply;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xshhope
 */
@Entity
@Table(name = "dt_bulletin")
@Data
public class BulletinPO implements Serializable{

    private static final long serialVersionUID = 3272580369078878678L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "modified_user_id")
    private Long modifiedUserId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "is_show")
    private Integer show;

    @Column(name = "is_deleted")
    private Integer deleted;
}
