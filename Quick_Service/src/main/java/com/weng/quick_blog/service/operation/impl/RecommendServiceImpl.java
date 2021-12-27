/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.request.recommend.QueryRecommendListPageRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Recommend;
import com.weng.quick_blog.entity.operation.vo.RecommendVO;
import com.weng.quick_blog.entity.security.SafeUserDetails;
import com.weng.quick_blog.mapper.operation.RecommendMapper;
import com.weng.quick_blog.service.article.ArticleService;
import com.weng.quick_blog.service.book.BookNoteService;
import com.weng.quick_blog.service.book.BookService;
import com.weng.quick_blog.service.operation.CategoryService;
import com.weng.quick_blog.service.operation.RecommendService;
import com.weng.quick_blog.service.operation.TagLinkService;
import com.weng.quick_blog.service.operation.TagService;
import com.weng.quick_blog.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version RecommendServiceImpl:RecommendServiceImpl.java v1.0 2021/12/2 7:43 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookNoteService bookNoteService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public PageQuery<Recommend> queryPage(QueryRecommendListPageRequest request) {
       IPage<Recommend> page = new Page<>(request.getPageNum(),request.getPageSize());

       baseMapper.selectPage(page,new QueryWrapper<Recommend>().lambda()
               .like(StringUtils.isNotBlank(request.getTitle()),Recommend::getTitle,request.getTitle())
               .eq(request.getType()!=-1,Recommend::getType,request.getType())
               .eq(request.getTop()!=-1,Recommend::getTop,request.getTop())
               .orderByDesc(Recommend::getUpdateTime)
               .orderByDesc(Recommend::getCreateTime));
        PageQuery<Recommend> pageQuery = new PageQuery(page);
        return pageQuery;
    }




    @Override
    public RecommendVO findVOById(Integer id) {
        Recommend recommend = baseMapper.selectById(id);
        RecommendVO recommendVO = new RecommendVO(recommend);

        Object properties = null;

        Integer linkId = recommendVO.getLinkId();

        switch(recommendVO.getType()){
            case 0:
                properties =  articleService.getById(linkId);
                break;
            case 1:
                properties = bookService.getById(linkId);
                break;
            case 2:
                properties = bookNoteService.getById(id);
                break;
            default:
                properties = new Object();
                break;
        }
        BeanUtils.copyProperties(properties,recommendVO);

        //设置标签
        recommendVO.setTagList(tagService.listByLinkId(linkId,recommendVO.getType()));

        String[] categories = recommendVO.getCategoryId().split(",");

        String categoryId = categories[categories.length-1];
        // 设置分类
        recommendVO.setCategory(categoryService.getById(categoryId));

        return recommendVO;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void deleteBatch(Integer[] ids) {
        SafeUserDetails currentSysUser = userService.getCurrentSysUser();
        List<Integer> list = Arrays.asList(ids);
        baseMapper.deleteBatchIds(list);
        log.info("用户:{} 在 {} 删除了id为:{}的推荐",currentSysUser.getAppellation(),new Date(),list);
    }
}
