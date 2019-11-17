package com.exercise.demo.model.enums;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 17:08
 */
public enum  HttpMethodType {
    GET(0, "GET"),
    POST(1, "POST"),
    PUT(2, "PUT"),
    DELETE(3, "DELETE");
    private int code;
    private String message;

    HttpMethodType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
