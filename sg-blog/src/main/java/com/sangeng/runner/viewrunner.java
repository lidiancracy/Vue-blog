package com.sangeng.runner;

import com.sangeng.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName viewrunner
 * @Description 在项目启动的时候执行一些操作
 * @Date 2022/9/3 17:37
 */

@Component
public class viewrunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
//        查询博客信息 id 以及 viewcount
        List<Article> articleList = articleMapper.selectList(null);
        Map<String, Integer> collect = articleList.stream()
                .collect(Collectors.toMap(new Function<Article, String>() {
                    @Override
                    public String apply(Article article) {
                        return article.getId().toString();
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));
        redisCache.setCacheMap("viewcount",collect);
    }
}
