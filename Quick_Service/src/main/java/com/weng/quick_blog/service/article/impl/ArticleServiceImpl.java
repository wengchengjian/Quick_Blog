/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.article.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.enums.ModuleEnum;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.article.Article;
import com.weng.quick_blog.entity.article.vo.ArticleDTO;
import com.weng.quick_blog.entity.article.vo.ArticleVO;
import com.weng.quick_blog.entity.operation.Tag;
import com.weng.quick_blog.mapper.article.ArticleMapper;
import com.weng.quick_blog.service.article.ArticleService;
import com.weng.quick_blog.service.operation.CategoryService;
import com.weng.quick_blog.service.operation.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version articleServiceImpl:ArticleServiceImpl.java v1.0 2021/12/2 5:04 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public PageQuery<ArticleVO> queryPage(Integer pageNum, Integer pageSize, String title, String author, Integer publish, Integer recommend, Integer top, String startTime, String endTime) {
        Page<ArticleVO> page = new Page<>(pageNum,pageSize);

        List<ArticleVO> articleList = baseMapper.listArticleVO(page,title,author,publish,recommend,top,startTime,endTime);

        Optional.ofNullable(articleList).ifPresent((articleVOS -> {
            articleVOS.forEach(articleVO -> {
                articleVO.setCategoryList(categoryService.queryCategoryArr(articleVO.getCategoryId()));
                articleVO.setTagList(tagService.listByLinkId(articleVO.getId(), ModuleEnum.ARTICLE.getValue()));
            });
        }));
        page.setRecords(articleList);
        return new PageQuery<ArticleVO>(page);
    }

    @Override
    public void saveArticle(ArticleDTO article) {
        baseMapper.insert(article);
        tagService.saveTagOrInsert(article.getTagList(),article.getId(),ModuleEnum.ARTICLE.getValue());
    }

    @Override
    public void deleteBatch(Integer[] articleIds) {
        // 先删除与tag的联系
        Arrays.stream(articleIds).forEach(articleId ->{
            tagService.deleteTagLink(articleId,ModuleEnum.ARTICLE.getValue());
        });

        // 再删除文章
        baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }

    @Override
    public void updateArticle(ArticleDTO artilce) {
        // 先删除之前的联系
        tagService.deleteTagLink(artilce.getId(),ModuleEnum.ARTICLE.getValue());
        // 再更新现在的联系
        tagService.saveTagOrInsert(artilce.getTagList(),artilce.getId(),ModuleEnum.ARTICLE.getValue());
        //更新博客
        baseMapper.updateById(artilce);
    }

    @Override
    public ArticleDTO getArticle(Integer articleId) {
        ArticleDTO articleDTO = null;

        Article article =baseMapper.selectById(articleId);
        if(article!=null){
            articleDTO = new ArticleDTO();
            // 复制对象的属性到新对象上
            BeanUtils.copyProperties(article,articleDTO);
            // 查询所属标签
            List<Tag> tags = tagService.listByLinkId(articleId,ModuleEnum.ARTICLE.getValue());

            articleDTO.setTagList(tags);
        }else{

        }
        return articleDTO;
    }

    @Override
    public Boolean checkByCategory(Integer id) {
        return baseMapper.checkByCategory(id) > 0;
    }
}
