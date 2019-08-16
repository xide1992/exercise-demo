package com.exercise.demo.dao.page;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 21:45
 */
public class Pagination<T> {
    private List<T> elements;

    private long totalCount;

    private int totalPage;

    private int pageSize;

    private int currentPage;

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
