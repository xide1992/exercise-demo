package com.exercise.demo.model;

import com.exercise.demo.common.utils.IpUtil;
import com.exercise.demo.model.enums.ResponseCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:37
 */
public class Response<T> {

    @ApiModelProperty(value = "是否成功")
    private boolean succeed;

    @ApiModelProperty("返回数据信息")
    private T body;

    @ApiModelProperty("返回数据信息,兼容不同接口的格式")
    private T data;

    @ApiModelProperty(value = "返回状态码")
    private ResponseCode statusCode;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "本机IP")
    private static String serverIp = "";

    static {
        serverIp = IpUtil.getLocalIP();
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

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

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        Response.serverIp = serverIp;
    }
}
