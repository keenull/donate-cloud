package com.xshhope.common.support;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author xshhope
 */
public class ActionResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private T data;

    /**
     * ResultDto Constructor
     */
    public ActionResult() {

    }

    /**
     * ResultDto Constructor
     */
    public ActionResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Description: whether this is non business error
     *
     * @return
     */
    public boolean isNonBizError() {
        return Result.SESSION_TIME_OUT.equals(code) || Result.COMMON_ERROR.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
