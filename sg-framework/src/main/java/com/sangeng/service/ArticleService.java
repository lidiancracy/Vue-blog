package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Article;
import com.sangeng.entity.R.ResponseResult;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2022-08-09 17:30:03
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticlelist();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getarticledetail(Long id);

    ResponseResult updateview(Long id);
}

