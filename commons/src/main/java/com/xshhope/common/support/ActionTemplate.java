package com.xshhope.common.support;

import org.springframework.validation.BindingResult;

/**
 * 返回模板
 *
 * @author xshhope
 */
public final class ActionTemplate {
    private ActionTemplate() {
    }

    /**
     * Description: 返回ack结果
     */
    public static <T> ActionResult<T> toAck(String msg) {
        return toAck(msg, null);
    }

    /**
     * Description: 返回ack结果
     */
    public static <T> ActionResult<T> toAck(String msg, T data) {
        ActionResult<T> dto = new ActionResult<T>();
        dto.setCode(Result.ACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    /**
     * Description: 返回REDIRECT结果
     */
    public static ActionResult<String> toRedirect(String url) {
        ActionResult<String> dto = new ActionResult<String>();
        dto.setCode(Result.REDIRECT);
        dto.setData(url);
        return dto;
    }

    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     */
    public static <T> ActionResult<T> toNack(String msg) {
        return toNack(msg, null);
    }


    /**
     * Description: 在controller层直接返回错误消息，避免在controller中用该方法catch异常做处理
     */
    public static <T> ActionResult<T> toNack(String msg, T data) {
        ActionResult<T> dto = new ActionResult<T>();
        dto.setCode(Result.NACK);
        dto.setMessage(msg);
        dto.setData(data);
        return dto;
    }

    /**
     * Description: 验证错误
     */
    public static ActionResult<BindingResult> toValidationError(String msg, BindingResult br) {
        ActionResult<BindingResult> dto = new ActionResult<BindingResult>();
        dto.setCode(Result.VALIDATION_ERROR);
        dto.setMessage(msg);
        dto.setData(br);
        return dto;
    }
}
