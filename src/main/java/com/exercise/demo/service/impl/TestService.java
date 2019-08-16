package com.exercise.demo.service.impl;

import com.exercise.demo.dao.order.generated.OrderExtendMapper;
import com.exercise.demo.dao.page.AbstractSearchCondition;
import com.exercise.demo.dao.page.OrderDirection;
import com.exercise.demo.dao.page.Pagination;
import com.exercise.demo.dao.page.PaginationUtils;
import com.exercise.demo.model.po.order.OrderExtend;
import com.exercise.demo.model.po.order.OrderExtendExample;

import com.exercise.demo.model.request.PageTestModel;
import com.exercise.demo.service.inter.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 16:58
 */
@Service
public class TestService implements ITestService {

    @Autowired
    OrderExtendMapper orderExtendMapper;

    @Override
    public List<OrderExtend> getOrderExtendList(long id) {

        OrderExtendExample extendExample = new OrderExtendExample();
        extendExample.createCriteria().andIdEqualTo(id);
        List<OrderExtend> sdf = orderExtendMapper.selectByExample(extendExample);
        return sdf;

    }

    @Override
    public List<OrderExtend> getPageOrderExtendList(PageTestModel model) {

        AbstractSearchCondition params=new AbstractSearchCondition();
        params.setPageIndex(model.getPageIndex());
        params.setPageSize(model.getPageSize());
        params.setSortBy("id");
        params.setOrderBy(OrderDirection.DESC);


        OrderExtendExample extendExample = new OrderExtendExample();
        extendExample.createCriteria().andIdGreaterThan(1L);


        Pagination<OrderExtend> pageList = PaginationUtils.
                pagingBy(params, () -> orderExtendMapper.selectByExample(extendExample));
        return pageList.getElements();
    }
}
