package com.sangeng.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import com.sangeng.entity.R.ResponseResult;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;

import com.sangeng.service.uploadservice;

import com.sangeng.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements uploadservice {

    @Override
    public ResponseResult uploadimgs(MultipartFile img) throws IOException {
//        文件类型判断 只允许上传 png
        if(!img.getOriginalFilename().endsWith("png")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        String generateFilePath = PathUtils.generateFilePath(img.getOriginalFilename());
        String url = uploadOss(img,generateFilePath);
        return ResponseResult.okResult(url);
    }

    private String accessKey;
    private String secretKey;
    private String bucket;


    public String uploadOss(MultipartFile img, String orinalname) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = getAccessKey();
        String secretKey = getSecretKey();
        String bucket = getBucket();
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "D:\\桌面\\图片\\epoch.pdf";
        InputStream inputStream=img.getInputStream();
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = orinalname;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(inputStream, key, upToken,null,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);

            return "http://rgprmx7la.hb-bkt.clouddn.com/"+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "notry";
    }
}
