package com.weng.quick_blog.admin.operation;

import com.weng.quick_blog.Result;
import com.weng.quick_blog.service.operation.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 18:52
 * @Version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/admin/operation/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/imgUpload")
    public Result<String> uploadImg(@RequestParam(value = "imgFile") MultipartFile file) throws IOException {
        String fileName = uploadService.uploadImage(file);
        return Result.Success(fileName);
    }

    @DeleteMapping("/imgDelete/{fileName}")
    public Result deleteImg(@PathVariable("fileName") String fileName) throws IOException {
        if(StringUtils.isNotBlank(fileName)){
            uploadService.deleteImg(fileName);
        }else{
            return Result.Failure("文件不存在");
        }
        return Result.Success();
    }
}
