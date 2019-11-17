package com.exercise.demo.dao.order.customized;

import com.exercise.demo.model.po.order.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/8/21 18:02
 */
@Mapper
//@Repository
public interface ArticleInfoCustomizedMapper {

    /**
     *
     */
    @Select("SELECT \n" +
            "    ai.title, ai.author\n" +
            "FROM\n" +
            "    xdz_db.article_info ai\n" +
            "WHERE\n" +
            "    id = #{id};")
    List<ArticleInfo> getArticleInfoById(@Param("id") long id);
}
