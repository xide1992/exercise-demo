package com.exercise.demo.service.inter;

import com.exercise.demo.model.Response;
import com.exercise.demo.model.request.AddOrderModel;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:35
 */
public interface IOrderService {

    Response<String> addOrder(AddOrderModel model);

    Response<String> getOrders(int count);

}
