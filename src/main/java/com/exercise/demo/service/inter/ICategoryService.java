package com.exercise.demo.service.inter;

import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.Category;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2020/2/2 10:48
 */
public interface ICategoryService {

    Response<List<Category>> getCategoryList();
}
