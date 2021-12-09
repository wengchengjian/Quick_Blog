/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version CategoryRankEnum:CategoryRankEnum.java v1.0 2021/12/2 10:56 上午 wengchengjian Exp $
 */
@Getter
public enum CategoryRankEnum {
    /**
     * 一级
     */
    ROOT(-1),
    /**
     * 一级
     */
    FIRST(0),
    /**
     * 二级
     */
    SECOND(1),
    /**
     * 三级
     */
    THIRD(2);

    private int value;

    CategoryRankEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
