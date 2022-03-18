package com.yangliwei.test1.config.error;

/**
 * @author ylw12
 */
public class CustomException extends RuntimeException {
    private final int code;
    private final String message;


    public CustomException(CustomExceptionType type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }

    public CustomException(CustomExceptionType type, String msg) {
        this.code = type.getCode();
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
