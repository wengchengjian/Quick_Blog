/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weng.quick_blog.entity.article.Article;
import com.weng.quick_blog.entity.article.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version articleMapper:ArticleMapper.java v1.0 2021/12/2 4:18 下午 wengchengjian Exp $
 */

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询列表
     * @param page
     * @param author
     * @param publish
     * @param recommend
     * @param top
     * @param startTime
     * @param endTime
     * @return
     */
    List<ArticleVO> listArticleVO(Page<ArticleVO> page, @Param("title") String title,
                                  String author, Integer publish, Integer recommend,
                                  Integer top, String startTime, String endTime);

    /**
     * 根据条件分页
     * @param page
     * @return
     */
    List<ArticleVO> queryPageCondition(Page<ArticleVO> page,Boolean recommend,Integer categoryId,Boolean latest,Boolean favorite);

    /**
     * 更新阅读记录+1
     * @param id
     */
    void updateReadNum(Integer id,Integer num);

    /**
     * 获取简单article对象
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVO(Integer articleId);

    /**
     * 更新点赞记录+1
     * @param id
     */
    void updateLikeNum(Integer id,Integer num);

    /**
     * 判断类别下是否有文章
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);
}
