package com.xshhope.model.pay;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xshhope
 */
@Entity
@Table(name = "dt_pay")
@Data
public class PayPO implements Serializable{

    private static final long serialVersionUID = -4730859488075007271L;

    @Id
    @Column
    private String id;

    @Column(name = "appeal_id")
    private Long appealId;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "modified_user_id")
    private Long modifiedUserId;

    @Column(name = "receiver_id")
    private Long receiver;

    @Column(name = "title")
    private String title;

    @Column(name = "money")
    private Double money;

    @Column(name = "pay_state")
    private Integer payState;

    @Column(name = "custom")
    private Integer custom;

    @Column(name = "comment")
    private String comment;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "pay_num")
    private String payNum;

    @Column(name = "is_deleted")
    private Integer deleted;
}
