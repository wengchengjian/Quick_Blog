/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version mybatisPlusAutoFillHandler:MybatisPlusAutoFillHandler.java v1.0 2021/12/1 3:42 下午 wengchengjian Exp $
 */
@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {
    /**
     *  插入时填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("createTime",now,metaObject);
        this.setFieldValByName("updateTime",now,metaObject);
    }

    /**
     * 更新时填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("updateTime",now,metaObject);
    }
}
