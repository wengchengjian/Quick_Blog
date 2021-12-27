package com.weng.quick_blog.service.operation;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 19:29
 * @Version 1.0.0
 */
public interface UploadService {
    /**
     * 上传图片并返回地址
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file) throws IOException;

    void deleteImg(String fileName) throws IOException;
}
