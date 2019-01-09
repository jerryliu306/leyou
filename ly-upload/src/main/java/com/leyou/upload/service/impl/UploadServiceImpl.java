package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.upload.config.UploadProperties;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author LiuJinmai
 * @Description 上传文件业务层
 * @Date 2019/1/5 21:16
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {
    /**
     * 支持的文件类型
     */
    //private static final List<String> SUFFIXES = Arrays.asList("image/png", "image/jpeg");

    private final UploadProperties properties;
    private final FastFileStorageClient storageClient;

    @Autowired
    public UploadServiceImpl(FastFileStorageClient storageClient, UploadProperties properties) {
        this.storageClient = storageClient;
        this.properties = properties;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        //图片信息校验-校验文件类型
        String fileContentType = file.getContentType();
        if (!properties.getAllowTypes().contains(fileContentType)) {
            throw new LyException(ExceptionInfoEnum.IMAGE_TYPE_ERROR);
        }
        try {
            //图片信息校验-校验图片内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                throw new LyException(ExceptionInfoEnum.IMAGE_TYPE_ERROR);
            }
            //生成保存目录,保存到nginx所在目录的html下，这样可以直接通过nginx来访问到
            File dir = new File("D:\\Tools\\nginx-1.12.2\\image");
            if (!dir.exists()) {
                boolean b = dir.mkdirs();
                if (!b) {
                    log.error("com.leyou.upload.service.impl: uploadImage -- create folder "
                            + dir.getAbsolutePath() + "fail.");
                }
            }
            //后缀名
            String newName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            //保存图片
            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), newName, null);
            //拼接图片地址
            return properties.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            log.debug("上传文件失败：" + e);
            throw new LyException(ExceptionInfoEnum.FILE_UPLOAD_ERROR, e);
        }
    }
}
