package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.dao.ArticleDao;
import com.sangeng.entity.Article;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.ArticleService;

import com.sangeng.vo.HotArticlevo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-08-09 17:30:03
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {
    /**
     * 查询出浏览量前十的文章, id,view,title
     * @return
     */
    @Override
    public ResponseResult hotArticlelist() {
//        查询热门
        LambdaQueryWrapper<Article> aq = new LambdaQueryWrapper<>();
//        未被删除掉,按照浏览量从高到低排序
        aq.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        aq.orderByDesc(Article::getViewCount);
//        只查询十条
        Page<Article> articles = new Page<Article>(1, 10);
        page(articles,aq);
        List<Article> records = articles.getRecords();

//        bean拷贝,将atricle值传入articlevo中，遍历article
        List<HotArticlevo> hotArticlevos = new ArrayList<>();
        for (Article record : records) {
            HotArticlevo vo = new HotArticlevo();
            BeanUtils.copyProperties(record,vo);
            hotArticlevos.add(vo);
        }


        return ResponseResult.okResult(records);

    }
}

