package com.dot.base.exception;

/**
 * 校验异常
 */
public class ValidateException extends RuntimeException {

    private Integer code;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
