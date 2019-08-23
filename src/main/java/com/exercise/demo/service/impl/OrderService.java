package com.exercise.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.exercise.demo.dao.order.generated.MainOrderMapper;

import com.exercise.demo.model.Response;

import com.exercise.demo.model.po.order.MainOrder;
import com.exercise.demo.model.po.order.MainOrderExample;
import com.exercise.demo.model.request.AddOrderModel;
import com.exercise.demo.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:35
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    MainOrderMapper mainOrderMapper;

//    @Autowired
//    UserMapper userMapper;

    /**
     * 新增
     *
     * @param model
     * @return
     */
    @Override
    public Response<String> addOrder(AddOrderModel model) {

        Response<String> response = new Response<>();

        MainOrder orderAdd = new MainOrder();

        orderAdd.setUserId(model.getBuyUserId());
        orderAdd.setUserName(model.getBuyUserName());
        orderAdd.setPrice(new BigDecimal(100));

        int result = mainOrderMapper.insertSelective(orderAdd);
        if (result > 0) {
            response.setData("新增成功");
            return response;
        } else {
            response.setData("新增失败");
            return response;
        }
    }


    /**
     * 查询
     *
     * @param count
     * @return
     */
    @Override
    public Response<String> getOrders(int count) {

        Response<String> response = new Response<>();

        MainOrderExample orderInfoExample = new MainOrderExample();
        orderInfoExample.createCriteria().andIdGreaterThan(0L);

        List<MainOrder> orderInfoList = mainOrderMapper.selectByExample(orderInfoExample);
        response.setData(JSON.toJSONString(orderInfoList));
        return response;

    }
}
