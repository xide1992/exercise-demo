package com.exercise.demo.service.inter;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 17:21
 */
public interface IWeChatService {

    String wxLogin(String code);

    void refreshWeChatImage();
}
