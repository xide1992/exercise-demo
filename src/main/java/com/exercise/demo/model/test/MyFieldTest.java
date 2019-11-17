package com.exercise.demo.model.test;

import com.exercise.demo.annotation.MyField;

import java.lang.reflect.Field;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/11/17 21:51
 */
public class MyFieldTest {
    //使用我们的自定义注解
    @MyField(description = "用户名", length = 12)
    private String username;
}
