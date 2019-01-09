package com.leyou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 上传功能配置类
 * @Date 2019/1/6 20:47
 */
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {
    /**
     * 图片服务器域名
     */
    private String baseUrl;
    /**
     * 图片类型集合
     */
    private List<String> allowTypes;
}
