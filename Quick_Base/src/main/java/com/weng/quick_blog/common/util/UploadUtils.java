package com.weng.quick_blog.common.util;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 20:37
 * @Version 1.0.0
 */
public class UploadUtils {

    public static final String generateFileName(String oldName){
        String suffix =oldName.substring(oldName.lastIndexOf("."));
        return IDUtils.generate() + suffix;
    }
}
