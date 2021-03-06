package com.exercise.demo.dao.order.generated;

import com.exercise.demo.model.po.order.ArticleInfo;
import com.exercise.demo.model.po.order.ArticleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    long countByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int deleteByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int insert(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int insertSelective(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    ArticleInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ArticleInfo record);
}