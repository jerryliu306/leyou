package com.leyou.upload.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @Author LiuJinmai
 * @Description FDFS的java客户端的配置类
 * @Date 2019/1/6 17:58
 * <p>
 * {@EnableMBeanExport} 是解决jmx重复注册bean的问题
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {
}
