package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LiuJinmai
 * @Description //TODO
 * @Date 2019/1/5 20:47
 */
public interface UploadService {

    /**
     * 图片上传
     *
     * @param file 图片对象
     * @return 存储的图片后的url
     */
    String uploadImage(MultipartFile file);
}
