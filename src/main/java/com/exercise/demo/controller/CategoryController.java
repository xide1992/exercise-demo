package com.exercise.demo.controller;

import com.exercise.demo.model.Response;
import com.exercise.demo.service.inter.ICategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2020/2/2 10:46
 */
@RestController
@RequestMapping("/category")
@Api(value = "分类", description = "分类")
public class CategoryController {


    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public Response<List<com.exercise.demo.model.po.order.Category>> categories() {
        return categoryService.getCategoryList();
    }

}
