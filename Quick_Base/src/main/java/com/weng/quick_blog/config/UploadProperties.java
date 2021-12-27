package com.weng.quick_blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author 翁丞健
 * @Date 2021/12/26 20:05
 * @Version 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix="file.upload")
public class UploadProperties {

    private String path;

    private List<String> allowTypes;
}
