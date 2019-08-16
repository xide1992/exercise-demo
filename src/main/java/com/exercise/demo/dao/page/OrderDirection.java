package com.exercise.demo.dao.page;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 21:44
 */
public enum OrderDirection {
    /**
     * ASC
     */
    ASC(0, "ASC"),

    /**
     * DESC
     */
    DESC(1, "DESC");


    private String message;
    private int code;
    OrderDirection(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public static OrderDirection getEnumByVal(int code) {
        for (OrderDirection c : OrderDirection.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return  null;
    }
}
