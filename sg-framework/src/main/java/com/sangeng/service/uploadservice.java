package com.sangeng.service;

import com.sangeng.entity.R.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName uploadservice
 * @Description TODO
 * @Date 2022/8/31 16:35
 */
public interface uploadservice {
    public ResponseResult uploadimgs(MultipartFile img) throws IOException;
}
