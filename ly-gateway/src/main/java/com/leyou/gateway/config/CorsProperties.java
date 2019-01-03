package com.leyou.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description CORS配置信息
 * @Date 2019/1/3 20:53
 */
@Data
@Component
@ConfigurationProperties(prefix = "leyou.cors")
public class CorsProperties {
    private List<String> allowedOrigins;
    private Boolean allowCredentials;
    private List<String> allowedMethods;
    private Long maxAge;
    private String filterPath;
    private List<String> allowedHeaders;
}
