package com.sangeng.job;

import com.sangeng.entity.Article;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName updateviewjob
 * @Description TODO
 * @Date 2022/9/12 20:37
 */
@Component
public class updateviewjob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private  ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateview() {
//        获取redies浏览量
        Map<String, Integer> viewcount = redisCache.getCacheMap("viewcount");
//        List<Article> articles = viewcount.entrySet()
//                .stream()
//                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue())).collect(Collectors.toList());
//        articleService.updateBatchById(articles);

    }
}
