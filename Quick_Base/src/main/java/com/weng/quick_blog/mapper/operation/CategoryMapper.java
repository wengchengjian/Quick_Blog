/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.quick_blog.entity.operation.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryMapper:CategoryMapper.java v1.0 2021/12/1 9:46 下午 wengchengjian Exp $
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> queryAll(@Param("name") String name,@Param("type") Integer type);

}
