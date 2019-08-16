package com.exercise.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.exercise.demo.dao.order.generated.OrderInfoMapper;
import com.exercise.demo.dao.order.generated.UserInfoMapper;
import com.exercise.demo.dao.order.generated.XideUserMapper;
import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.OrderInfo;
import com.exercise.demo.model.po.order.OrderInfoExample;
import com.exercise.demo.model.po.order.UserInfo;
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
    OrderInfoMapper orderInfoMapper;

    @Autowired
    XideUserMapper xideUserMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 新增
     *
     * @param model
     * @return
     */
    @Override
    public Response<String> addOrder(AddOrderModel model) {

        Response<String> response=new Response<>();


        UserInfo userInfo=new UserInfo();
      //  userInfo.setId(1L);
        userInfo.setUserCard("10001");
       // userInfo.setUserName("test");

        int result1=userInfoMapper.insertSelective(userInfo);

        OrderInfo orderAdd=new OrderInfo();
        orderAdd.setProductId(model.getProductId());
        orderAdd.setProductName(model.getProductName());
        orderAdd.setBuyUserId(model.getBuyUserId());
        orderAdd.setBuyUserName(model.getBuyUserName());
        orderAdd.setPrice(new BigDecimal(100));

        int result=orderInfoMapper.insertSelective(orderAdd);
        if(result>0)
        {
            response.setData("新增成功");
            return response;
        }
        else
        {
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

        OrderInfoExample orderInfoExample = new OrderInfoExample();
        orderInfoExample.createCriteria().andIdGreaterThan(0L);

        List<OrderInfo> orderInfoList = orderInfoMapper.selectByExample(orderInfoExample);
        response.setData(JSON.toJSONString(orderInfoList));
        return response;

    }
}
