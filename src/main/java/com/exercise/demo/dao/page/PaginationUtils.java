package com.exercise.demo.dao.page;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 21:41
 */
public class PaginationUtils {

    /**
     * 限定排序字段
     */
    private static final List<String> LIMITED_SORT_BY_FIELDS = newArrayList(
            "id",
            "create_date",
            "update_date",
            "data_flag"
    );

    /**
     * Mybatis 分页收口。
     *
     * @param condition   查询条件
     * @param select      查询语句
     * @param <Element>   分页实体
     * @param <Condition> 分页查询条件
     * @return 分页结果
     */
    public static <Element, Condition extends AbstractSearchCondition> Pagination<Element> pagingBy(Condition condition, ISelect select) {
        final Page<Element> result = PageHelper.startPage(condition.getPageIndex(), condition.getPageSize());
        if (condition.getPageSize() == -1) {
            result.setPageSizeZero(true);
            result.setPageSize(0);
        }
        if (condition.getSortBy() != null && LIMITED_SORT_BY_FIELDS.contains(condition.getSortBy())) {
            final String sortBy = condition.getSortBy();
            final OrderDirection orderBy = condition.getOrderBy();
            result.setOrderBy(orderBy != null ? sortBy + " " + orderBy : sortBy);
        }
        result.doSelectPage(select);

        Pagination<Element> retval = new Pagination<>();
        retval.setCurrentPage(result.getPageNum());
        retval.setElements(result.getResult());
        retval.setPageSize(result.getPageSize());
        retval.setTotalCount(result.getTotal());
        retval.setTotalPage(result.getPages());
        return retval;
    }

    /**
     * Mybatis 分页收口。
     *
     * @param condition   查询条件
     * @param select      查询语句
     * @param sortBy      重新指定分页字段
     * @param orderBy     重新指定分页方向
     * @param <Element>   分页实体
     * @param <Condition> 分页查询条件
     * @return 分页结果
     */
    public static <Element, Condition extends AbstractSearchCondition> Pagination<Element> pagingBy(Condition condition, String sortBy, OrderDirection orderBy, ISelect select) {
        final Page<Element> result = PageHelper.startPage(condition.getPageIndex(), condition.getPageSize());
        if (condition.getPageSize() == -1) {
            result.setPageSizeZero(true);
            result.setPageSize(0);
        }
        result.setOrderBy(orderBy != null ? sortBy + " " + orderBy : sortBy);
        result.doSelectPage(select);

        Pagination<Element> retval = new Pagination<>();
        retval.setCurrentPage(result.getPageNum());
        retval.setElements(result.getResult());
        retval.setPageSize(result.getPageSize());
        retval.setTotalCount(result.getTotal());
        retval.setTotalPage(result.getPages());
        return retval;
    }
}
