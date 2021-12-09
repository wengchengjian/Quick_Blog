/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.weng.quick_blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 * @author wengchengjian
 * @version MenuTypeEnum:MenuTypeEnum.java v1.0 2021/12/7 11:06 上午 wengchengjian Exp $
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    ;
    private Integer value;

}
