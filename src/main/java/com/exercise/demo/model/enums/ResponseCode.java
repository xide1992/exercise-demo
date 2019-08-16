package com.exercise.demo.model.enums;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:37
 */
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(0, "Success."),

    /**
     * 参数验证失败
     */
    VALIDATION_FAILURE(10002, "Validation failure."),

    /**
     * 内部系统异常
     */
    INTERNAL_EXCEPTION(2, "Internal Exception."),

    /**
     * 业务处理失败
     */
    PROCESS_FAILURE(3, "process failure."),

    /**
     * 处理失败不要重试
     */
    FAILURE_NOT_AGAIN(4, "fialure need again");

    private String message;
    private int code;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
