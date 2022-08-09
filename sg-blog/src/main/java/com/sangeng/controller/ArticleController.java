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
   @GetMapping("/hotArticleList")
    public ResponseResult hotarticle(){
       return articleService.hotArticlelist();
   }
}

