/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.service.operation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.quick_blog.common.request.QueryCategoryListRequest;
import com.weng.quick_blog.common.util.PageQuery;
import com.weng.quick_blog.entity.operation.Category;
import com.weng.quick_blog.mapper.article.ArticleMapper;
import com.weng.quick_blog.mapper.book.BookMapper;
import com.weng.quick_blog.mapper.book.BookNoteMapper;
import com.weng.quick_blog.mapper.operation.CategoryMapper;
import com.weng.quick_blog.service.article.ArticleService;
import com.weng.quick_blog.service.operation.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryServiceImpl:CategoryServiceImpl.java v1.0 2021/12/1 9:57 下午 wengchengjian Exp $
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookNoteMapper bookNoteMapper;


    @Override
    public List<Category> queryWithParentName(String parentName,Integer type) {
        return baseMapper.queryAll(parentName,type);
    }

    @Override
    public List<Category> queryCategoryById(Integer id) {

        LinkedList<Category> push = new LinkedList<>();
        LinkedList<Category> pop = new LinkedList<>();
        Category category = baseMapper.selectById(id);

        push.push(category);

        while(!push.isEmpty()){
            Category now = push.pop();

            List<Category> children = baseMapper.selectList(new QueryWrapper<Category>().lambda()
                    .eq(now!=null,Category::getParentId,now.getId()));
            push.addAll(children);
            pop.add(now);
        }

        log.info("当前分类id的子孙分类:{}",pop);
        return pop;
    }

    @Override
    public List<Category> queryAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<Category> queryWithParenId(Integer parentId) {
        return baseMapper.selectList(new QueryWrapper<Category>()
                .lambda().eq(Category::getParentId,parentId));
    }

    @Override
    public String queryCategoryArr(String categoryIds) {


        List<String> ids = Arrays.asList(categoryIds.split(","));

        StringBuffer list = new StringBuffer();

        for(int i = 0;i<ids.size();i++){

            String id = ids.get(i);

            Category category = baseMapper.selectById(id);

            list.append(category.getName());
        }
        return list.toString();
    }

    @Override
    public List<Category> queryByName(String name) {
        return baseMapper.selectList(new QueryWrapper<Category>().lambda()
                .like(StringUtils.isNotBlank(name),Category::getName,name).orderByDesc(Category::getUpdateTime));
    }

    @Override
    public PageQuery<Category> queryPage(QueryCategoryListRequest request) {
        IPage<Category> page = new Page<>(request.getPageNum(),request.getPageSize());
        baseMapper.selectPage(page,new QueryWrapper<Category>().lambda().select()
                .like(StringUtils.isNotBlank(request.getName()),Category::getName,request.getName())
                .eq(request.getParentId()!=-1,Category::getParentId,request.getParentId())
                .orderByDesc(Category::getUpdateTime));
        PageQuery<Category> pageQuery = new PageQuery<>(page);
        pageQuery.getList().forEach(item->{

             Category parent = baseMapper.selectOne(new QueryWrapper<Category>().lambda()
                        .eq(Category::getId,item.getParentId())
                        .select(Category::getName));
             if(parent!=null){
                 item.setParentName(parent.getName());
             }

        });
        return pageQuery;
    }

    @Override
    public boolean linkWithArticle(Integer id) {
        return articleMapper.checkByCategory(id) > 0;
    }

    @Override
    public boolean linkWithBook(Integer id) {
        return bookMapper.checkByCategory(id) > 0;
    }

    @Override
    public boolean linkWithBookNote(Integer id) {
        return bookNoteMapper.checkByCategory(id) > 0;
    }

    @Override
    public Category findByParentId(Integer parentId) {
        return baseMapper.selectOne(new QueryWrapper<Category>().lambda().eq(parentId!=null,Category::getId,parentId));
    }

    @Override
    public boolean checkCurrentCategory(Category category) {
        Integer oldParentId = baseMapper.selectById(category.getId()).getParentId();
        Integer newParentId = category.getParentId();

        List<Category> categories = queryCategoryById(oldParentId);


        //当前子孙分类是否包含更新节点，是则失败.
        return categories.stream().allMatch(item->{
            if(item.getId().equals(newParentId)){
                return false;
            }
            return true;
        });

    }


}
