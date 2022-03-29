package com.yangliwei.test1.config.error;

/**
 * @author ylw12
 */

public enum CustomExceptionType {
    /**
     * 自定义异常
     */
    PARAM_ERROR(400, "参数错误!"),
    SYSTEM_ERROR(500, "系统出现异常,请稍候再试!"),
    USER_EXIST(404, "用户已存在!"),
    USER_NOT_EXIST(404, "用户不存在!"),
    USER_NOT_LOGIN(401, "用户未登录!"),
    USER_NOT_LOGIN_OR_PERMISSION(401, "用户未登录或没有权限!"),
    USER_NOT_PERMISSION(403, "用户没有权限!"),
    USER_LOGIN_EXCEPTION(500, "用户登录失败!"),
    USER_UNAUTHORIZED(401, "用户未授权!"),
    ;
    /**
     *  异常类型
     */
    private final int code;
    /**
     *  异常信息
     */
    private final String message;

    CustomExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}