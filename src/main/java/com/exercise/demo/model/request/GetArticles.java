package com.exercise.demo.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/25 23:27
 */
public class GetArticles {

    @ApiModelProperty(value = "每页数量")
    private  int pageSize;

    @ApiModelProperty(value = "当前页码")
    private  int pageIndex;

    @ApiModelProperty(value = "排序方式 ,0:正序 1:倒序")
    private  int orderType;

    @ApiModelProperty(value = "标题")
    private  String title;

    @ApiModelProperty(value = "重要性")
    private  int importantLevel;

    @ApiModelProperty(value = "国家")
    private  String country;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(int importantLevel) {
        this.importantLevel = importantLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
