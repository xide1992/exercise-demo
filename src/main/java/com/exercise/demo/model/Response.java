package com.exercise.demo.model;

import com.exercise.demo.model.enums.ResponseCode;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:37
 */
public class Response<T> {
    private T data;
    private ResponseCode statusCode;
    private boolean succeed;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(ResponseCode statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
