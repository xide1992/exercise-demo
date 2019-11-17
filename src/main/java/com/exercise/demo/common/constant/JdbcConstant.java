package com.exercise.demo.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/8/25 1:06
 */
@Component
public class JdbcConstant {

    public static String URL;

    public static String USERNAME;

    public static String PASSWORD;

    @Value("${spring.datasource.url}")
    public void setURL(String url) {
        JdbcConstant.URL = url;
    }

    @Value("${spring.datasource.username}")
    public void setUSERNAME(String username) {
        JdbcConstant.USERNAME = username;
    }

    @Value("${spring.datasource.password}")
    public void setPASSWORD(String password) {
        JdbcConstant.PASSWORD = password;
    }
}
