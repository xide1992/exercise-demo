package com.exercise.demo.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 21:55
 */
public class PageTestModel {

    @ApiModelProperty(value = "每页数量", dataType = "int")
    private  int pageSize;

    @ApiModelProperty(value = "当前页码", dataType = "int")
    private  int pageIndex;

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
}
