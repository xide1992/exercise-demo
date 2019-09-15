package com.exercise.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exercise.demo.common.utils.*;
import com.exercise.demo.model.po.order.OrderExtend;
import com.exercise.demo.model.request.PageTestModel;
import com.exercise.demo.model.request.TestSelectModel;
import com.exercise.demo.service.inter.ITestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 16:08
 */
@RestController
public class HelloWorldController {

    @Autowired
    private ITestService testService;

    @ApiOperation(value = "hello", notes = "hello")
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring boot!";
    }


    @ApiOperation(value = "get测试", notes = "get测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/getTest")
    public String getTest(@ModelAttribute @Valid TestSelectModel request) {
        LogHelper.info("infotest", "111", "222", "3333");
        LogHelper.warn("warntest", "111", "222", "3333");
        LogHelper.error("errortest", "111", "222", "3333", new Exception("sfs"));
        List<OrderExtend> result = testService.getOrderExtendList(request.getId());
        return JSON.toJSONString(result);
    }

    @ApiOperation(value = "分页测试", notes = "分页测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/getPageTest")
    public String getPageTest(@ModelAttribute @Valid PageTestModel request) {

        List<OrderExtend> result = testService.getPageOrderExtendList(request);
        return JSON.toJSONString(result);
    }


    @GetMapping("/encrypt")
    @ApiOperation(value = "加密")
    @ApiImplicitParam(name = "str", value = "加密字符", required = true, dataType = "string", paramType = "query")
    @ResponseBody
    public String encrypt(String str) {
        try {
            return AESUtil.encrypt(str, "");
        } catch (Throwable e) {
            return "";
        }
    }

    @GetMapping("/decrypt")
    @ApiOperation(value = "解密")
    @ApiImplicitParam(name = "str", value = "解密字符", required = true, dataType = "string", paramType = "query")
    @ResponseBody
    public String decrypt(String str) {
        try {
            return AESUtil.decrypt(str, "");
        } catch (Throwable e) {
            return "";
        }
    }

    @GetMapping("/MD5")
    @ApiOperation(value = "MD5加密")
    @ApiImplicitParam(name = "str", value = "字符", required = true, dataType = "string", paramType = "query")
    @ResponseBody
    public String md5(String str) {
        try {
            return MD5Util.getMD5(str);
        } catch (Throwable e) {
            return "";
        }
    }


    @GetMapping("/xmlToJson")
    @ApiOperation(value = "xml转换为json")
    @ApiImplicitParam(name = "str", value = "字符", required = true, dataType = "string", paramType = "query")
    @ResponseBody
    public String xmlToJson(String str) {
        try {
            JSONObject resultJson = XmlUtil.documentToJSONObject(str);
            return JSON.toJSONString(resultJson);
        } catch (Throwable e) {
            return "";
        }
    }


    @GetMapping("/getIp")
    @ApiOperation(value = "获取Ip")
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        try {
            String result = "本机:" + IpUtil.getLocalHostAddress() + ",远程:" + IpUtil.getIp(request)+",name:"+IpUtil.getLocalHostName();
            return result;
        } catch (Throwable e) {
            return "";
        }
    }

    @GetMapping("/orderIdGenerate")
    @ApiOperation(value = "生成订单号")
    @ResponseBody
    public String orderIdGenerate(HttpServletRequest request) {
        try {

            return OrderIdGenerateUtil.getOrderSerialId("xx");
        } catch (Throwable e) {
            return "";
        }
    }
}
