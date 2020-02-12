package com.exercise.demo.service.impl;

import com.exercise.demo.dao.order.customized.ArticleInfoCustomizedMapper;
import com.exercise.demo.dao.order.generated.ArticleInfoMapper;
import com.exercise.demo.dao.page.AbstractSearchCondition;
import com.exercise.demo.dao.page.OrderDirection;
import com.exercise.demo.dao.page.Pagination;
import com.exercise.demo.dao.page.PaginationUtils;
import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.ArticleInfo;
import com.exercise.demo.model.po.order.ArticleInfoExample;
import com.exercise.demo.model.request.GetArticles;
import com.exercise.demo.model.response.GetArticleListResponse;
import com.exercise.demo.service.inter.IArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

/**
 * @Author: xdz
 * @Descrption:  文章相关服务
 * @Date: 2019/5/25 22:59
 */
@Service
public class ArticleService implements IArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;

    @Autowired
    ArticleInfoCustomizedMapper articleInfoCustomizedMapper;

    /**
     * 新增文章
     *
     * @param request
     * @return
     */
    @Override
    public Response addArticle(ArticleInfo request) {
        Response response = new Response();
        request.setCreateTime(new Date());
        if (articleInfoMapper.insertSelective(request) > 0) {
            response.setSucceed(true);
            response.setMessage("新增数据成功");
            return response;
        }
        response.setSucceed(true);
        response.setMessage("新增数据失败");
        return response;
    }

    /**
     * 更新文章
     *
     * @param request
     * @return
     */
    @Override
    public Response updateArticle(ArticleInfo request) {
        Response response = new Response();
        if (articleInfoMapper.updateByPrimaryKeySelective(request) > 0) {
            response.setSucceed(true);
            response.setMessage("更新数据成功");
            return response;
        }
        response.setSucceed(true);
        response.setMessage("更新数据失败");
        return response;
    }

    @Override
    public Response<GetArticleListResponse> getArticleList(GetArticles request) {
        AbstractSearchCondition params = new AbstractSearchCondition();
        params.setPageIndex(request.getPageIndex());
        params.setPageSize(request.getPageSize());
        params.setSortBy("id");
        params.setOrderBy(request.getOrderType() == 0 ? OrderDirection.ASC : OrderDirection.DESC);

        ArticleInfoExample articleInfoExample = new ArticleInfoExample();
        ArticleInfoExample.Criteria cri = articleInfoExample.createCriteria();
        cri.andStateEqualTo(1);
        if (StringUtils.isNotBlank(request.getTitle())) {
            cri.andTitleLike("%" + request.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(request.getCountry())) {
            cri.andCountryEqualTo(request.getCountry());
        }
        if (request.getImportantLevel() > 0) {
            cri.andImportantLevelEqualTo(request.getImportantLevel());
        }
        if (request.getCategoryId()!=null&&  request.getCategoryId()>0) {
            cri.andCategoryIdEqualTo(request.getCategoryId());
        }

        Pagination<ArticleInfo> pageList = PaginationUtils.
                pagingBy(params, () -> articleInfoMapper.selectByExample(articleInfoExample));
        if (pageList == null) {
            return null;
        }

        Response<GetArticleListResponse> resp = new Response<GetArticleListResponse>();
        GetArticleListResponse getArticleListResponse = new GetArticleListResponse();
        getArticleListResponse.setPageIndex(pageList.getCurrentPage());
        getArticleListResponse.setPageSize(pageList.getPageSize());
        getArticleListResponse.setTotalCount(pageList.getTotalCount());
        getArticleListResponse.setArticleInfoList(pageList.getElements());
        getArticleListResponse.setHasMore(pageList.getElements().size()==request.getPageSize());
        resp.setSucceed(true);
        resp.setBody(getArticleListResponse);

        return resp;
    }

    @Override
    public Response<ArticleInfo> getArticleInfoById(long id) {
        Response<ArticleInfo> resp = new Response<ArticleInfo>();
        resp.setSucceed(true);
    resp.setBody(articleInfoMapper.selectByPrimaryKey(id));
       // resp.setData(articleInfoCustomizedMapper.getArticleInfoById(id).get(0));
        return resp;
    }

}
