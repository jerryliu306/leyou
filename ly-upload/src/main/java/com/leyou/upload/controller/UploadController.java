package com.leyou.upload.controller;

import com.leyou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LiuJinmai
 * @Description //TODO
 * @Date 2019/1/5 20:41
 */
@RestController
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * 上传图片功能
     *
     * @param file 图片对象
     * @return 图片url路径
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // 返回200，并且携带url路径
        return ResponseEntity.ok(this.uploadService.uploadImage(file));
    }
}