package com.exercise.demo.controller;

import com.exercise.demo.service.inter.IWeChatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 17:16
 */
@RestController
@RequestMapping("/wechat")
@Api("微信相关服务")
public class WeChatController {

    @Autowired
    IWeChatService weChatService;

    /**
     * 刷新微信图片
     *
     * @return
     */
    @RequestMapping(value = "refreshWeChatImage", method = RequestMethod.GET)
    @ResponseBody
    public void refreshWeChatImage() {
        weChatService.refreshWeChatImage();
    }
}
