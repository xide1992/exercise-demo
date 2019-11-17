package com.exercise.demo.controller;

import com.exercise.demo.annotation.LoginRequired;
import com.exercise.demo.annotation.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/11/17 22:02
 */
@RestController
public class LoginController {

    @GetMapping("/sourceA")
    public String sourceA(){
        return "你正在访问sourceA资源";
    }

    @LoginRequired
    @GetMapping("/sourceB")
    public String sourceB(){
        return "你正在访问sourceB资源";
    }

    @MyLog
    @GetMapping("/sourceC/{source_name}")
    public String sourceC(@PathVariable("source_name") String sourceName){
        return "你正在访问sourceC资源" +sourceName;
    }
}
