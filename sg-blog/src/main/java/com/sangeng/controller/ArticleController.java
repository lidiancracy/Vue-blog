package com.sangeng.controller;


import com.sangeng.entity.Article;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章表(Article)表控制层
 *
 * @author makejava
 * @since 2022-08-09 17:29:59
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 最热文章显示
     *
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotarticle() {
        return articleService.hotArticlelist();
    }

    /**
     * 主页 分类主页 文章显示,分页查询
     * 传参默认是query风格
     */

    @GetMapping("/articleList")
    public ResponseResult Articlelist(Long categoryId, Integer pageNum, Integer pageSize) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 根据id查询文章详情,传参path风格
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getblogdetail(@PathVariable("id") Long id){
        return articleService.getarticledetail(id);
    }
}

