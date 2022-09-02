package com.sangeng;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName testoss
 * @Description TODO
 * @Date 2022/8/31 16:18
 */
@Component
@SpringBootTest

@Data
public class testoss {
    private String accessKey;
    private String secretKey;
    private String bucket;



}
