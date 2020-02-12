package com.exercise.demo.service.impl;

import com.exercise.demo.dao.order.generated.CategoryMapper;
import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.Category;
import com.exercise.demo.model.po.order.CategoryExample;
import com.exercise.demo.service.inter.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2020/2/2 10:48
 */
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Response<List<Category>> getCategoryList() {

        Response response = new Response();
        response.setSucceed(false);

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdGreaterThan(0L);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        if (categoryList == null) {
            return null;
        }

        response.setSucceed(true);
        response.setBody(categoryList);
        return response;
    }
}
