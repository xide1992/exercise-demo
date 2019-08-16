package com.exercise.demo.model.response;

import com.exercise.demo.model.po.order.ArticleInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/25 23:40
 */
public class GetArticleListResponse {

    @ApiModelProperty("总记录数")
    private long totalCount;
    @ApiModelProperty("当前页数")
    private int pageIndex;
    @ApiModelProperty("每页数量")
    private int pageSize;
    @ApiModelProperty("文章列表")
    private List<ArticleInfo> supplierCityMappingList;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ArticleInfo> getSupplierCityMappingList() {
        return supplierCityMappingList;
    }

    public void setSupplierCityMappingList(List<ArticleInfo> supplierCityMappingList) {
        this.supplierCityMappingList = supplierCityMappingList;
    }
}
