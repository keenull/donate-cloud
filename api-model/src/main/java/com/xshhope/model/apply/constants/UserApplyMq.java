package com.xshhope.model.apply.constants;

/**
 * @author xshhope
 */
public interface UserApplyMq {
    /**
     * 用户系统exchange名
     */
    String MQ_EXCHANGE_Apply = "apply.topic.apply";

    /**
     * 申请更新routing key
     */
    String ROUTING_KEY_APPLY_UPDATE = "apply.update";
}
