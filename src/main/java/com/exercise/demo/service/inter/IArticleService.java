package com.exercise.demo.service.inter;

import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.ArticleInfo;
import com.exercise.demo.model.request.GetArticles;
import com.exercise.demo.model.response.GetArticleListResponse;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/25 22:58
 */
public interface IArticleService {

    /**
     * 新增文章
     *
     * @param request
     * @return
     */
    Response addArticle(ArticleInfo request);


    /**
     * 更新文章
     *
     * @param request
     * @return
     */
    Response updateArticle(ArticleInfo request);

    Response<GetArticleListResponse> getArticleList(GetArticles request);

    Response<ArticleInfo> getArticleInfoById(long id);
}
