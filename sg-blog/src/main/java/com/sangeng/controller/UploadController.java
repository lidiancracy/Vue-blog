package com.sangeng.controller;

import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.uploadservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Date 2022/8/31 16:31
 */
@RestController
public class UploadController {
    @Autowired
    private uploadservice uploadservice;
    @PostMapping("/upload")
    public ResponseResult uploadimg(MultipartFile img) throws IOException {
        return uploadservice.uploadimgs(img);
    }


}
