package com.exercise.demo.controller;

import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.ArticleInfo;
import com.exercise.demo.model.request.GetArticles;
import com.exercise.demo.model.response.GetArticleListResponse;
import com.exercise.demo.service.inter.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/25 22:57
 */
@RestController
@RequestMapping("/article")
@Api(value = "订单相关服务", description = "文章相关服务")
public class ArticleController {
    @Autowired
    private IArticleService articleService;


    @ApiOperation(value = "新增文章", notes = "新增", response = Response.class)
    @PostMapping("/add")
    public Response addArticle(@RequestBody @Valid ArticleInfo request) {
        Response result = articleService.addArticle(request);
        return result;
    }


    @ApiOperation(value = "更新文章", notes = "新增", response = Response.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateArticle(@RequestBody @Valid ArticleInfo request) {
        Response result = articleService.updateArticle(request);
        return result;
    }

    @ApiOperation(value = "获取文章列表")
    @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
    @ResponseBody
    public Response<GetArticleListResponse> getArticleList(@ModelAttribute @Valid GetArticles request) {
        return articleService.getArticleList(request);
    }

    @ApiOperation(value = "根据id获取文章")
    @RequestMapping(value = "getArticleInfoById", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "表Id", required = true, dataType = "long", paramType = "query")
    @ResponseBody
    public Response<ArticleInfo> getArticleInfoById(@RequestParam long id) {
        return articleService.getArticleInfoById(id);
    }
}
