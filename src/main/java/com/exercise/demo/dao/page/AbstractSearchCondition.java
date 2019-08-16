package com.exercise.demo.dao.page;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 21:44
 */
public class AbstractSearchCondition {
    /**
     * 页索引
     */
    @Range(
            min = 1L,
            message = "错误的分页属性。"
    )
    @ApiModelProperty(value = "页索引",notes = "页索引")
    private int pageIndex = 1;

    /**
     * 每一页的记录条数
     */
    @Range(
            max = 1000L,
            message = "错误的分页属性。"
    )
    @ApiModelProperty(value = "每一页的记录条数",notes = "每一页的记录条数")
    private int pageSize = 10;

    /**
     * 升序/降序
     */
    @ApiModelProperty(value = "升序/降序",notes = "升序/降序")
    private OrderDirection orderBy;
    /**
     * 排序字段
     */
    @ApiModelProperty(value ="排序字段")
    private String sortBy;

    public AbstractSearchCondition() {
        this.orderBy = OrderDirection.ASC;
        this.sortBy = "id";
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public OrderDirection getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(OrderDirection orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
