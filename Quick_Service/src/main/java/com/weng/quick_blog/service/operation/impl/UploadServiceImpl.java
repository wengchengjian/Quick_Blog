package com.weng.quick_blog.service.operation.impl;

import com.weng.quick_blog.common.util.UploadUtils;
import com.weng.quick_blog.config.UploadProperties;
import com.weng.quick_blog.service.operation.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 19:30
 * @Version 1.0.0
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    private static final String Image_Path = "image/";

//    private static final String Image_Path = "image/";
//
//    private static final String Image_Path = "image/";


    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return upload(file,Image_Path);
    }

    public String upload(MultipartFile file,String path) throws IOException {

        if(!uploadProperties.getAllowTypes().contains(file.getContentType())){
            throw new IOException("文件上传类型错误!");
        }
        String fileName = UploadUtils.generateFileName(file.getOriginalFilename());

        file.transferTo(new File(uploadProperties.getPath()+path+fileName));

        return fileName;
    }



    @Override
    public void deleteImg(String fileName) throws IOException {
        File file = new File(uploadProperties.getPath()+Image_Path+fileName);
        if(file.exists()){
            if(file.isFile()){
                file.delete();
            }else{
                throw new IOException("文件类型错误");
            }
        }else{
            throw new IOException("文件不存在");
        }
    }
}
