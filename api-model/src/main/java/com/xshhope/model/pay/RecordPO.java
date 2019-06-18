package com.xshhope.model.pay;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xshhope
 */
@Entity
@Table(name = "dt_record")
@Data
public class RecordPO implements Serializable{

    private static final long serialVersionUID = -3914696835384260161L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "created_user_name")
    private String createdUserName;

    @Column(name = "receiver_id")
    private Long receiver;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "verify_user_id")
    private Long verifyUserId;

    @Column(name = "title")
    private String title;

    @Column(name = "pay_order")
    private String payOrder;

    @Column(name = "goal")
    private Double goal;

    @Column(name = "money")
    private Double money;

    @Column(name = "record_state")
    private Integer recordState;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "is_deleted")
    private Integer deleted;
}
