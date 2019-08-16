package com.exercise.demo.service.inter;

import com.exercise.demo.model.po.order.OrderExtend;
import com.exercise.demo.model.request.PageTestModel;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 16:56
 */
public interface ITestService {

    List<OrderExtend> getOrderExtendList(long id);

    List<OrderExtend> getPageOrderExtendList(PageTestModel model);
}
