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
//    @Autowired
//    private RedisCache redisCache;
//    @Autowired
//    private ArticleService articleService;
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void updateview(){
////        获取redies浏览量
//        Map<String, Integer> viewcount = redisCache.getCacheMap("viewcount");
//        List<Article> collect = viewcount.entrySet()
//                .stream()
//                .map(new Function<Map.Entry<String, Integer>, Article>() {
//                    @Override
//                    public Article apply(Map.Entry<String, Integer> stringIntegerEntry) {
//
//                        return new Article(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
//                    }
//                }).collect(Collectors.toList());
//        articleService.updateBatchById(collect);
//    }
}
