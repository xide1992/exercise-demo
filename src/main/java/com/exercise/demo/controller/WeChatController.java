package com.exercise.demo.controller;

import com.exercise.demo.service.inter.IWeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 微信登录
     *
     * @return
     */
    @ApiOperation(value = "登录", notes = "微信登录", response = String.class)
    @RequestMapping(value = "wxLogin", method = RequestMethod.GET)
    @ResponseBody
    public String wxLogin(@RequestParam String code) {
        return weChatService.wxLogin(code);
    }

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
