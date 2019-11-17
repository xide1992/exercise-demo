package com.exercise.demo.controller;

import com.exercise.demo.annotation.LoginRequired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
