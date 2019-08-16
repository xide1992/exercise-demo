package com.exercise.demo.controller;

import com.exercise.demo.model.Response;
import com.exercise.demo.model.request.AddOrderModel;
import com.exercise.demo.service.inter.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: xdz
 * @Descrption:  订单相关服务
 * @Date: 2019/5/15 22:33
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单相关服务", description = "订单相关服务")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "新增订单", notes = "新增", response = Response.class)
    @PostMapping("/add")
    public Response<String> addOrder(@RequestBody @Valid AddOrderModel request) {
        Response result = orderService.addOrder(request);
        return result;
    }

    @RequestMapping(value = "getOrders", method = RequestMethod.GET)
    @ApiImplicitParam(name = "count", value = "数量", required = true, dataType = "int", paramType = "query")
    @ResponseBody
    public Response<String> getOrders(@RequestParam  int count) {
        return orderService.getOrders(count);
    }
}
