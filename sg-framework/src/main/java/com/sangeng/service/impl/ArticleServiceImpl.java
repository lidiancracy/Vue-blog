package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.entity.Category;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.entity.Article;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.ArticleService;

import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.vo.ArticleListVo;
import com.sangeng.vo.HotArticlevo;
import com.sangeng.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-08-09 17:30:03
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    /**
     * 查询出浏览量前十的文章, id,view,title
     * @return
     */
    @Autowired
    private CategoryService categoryService;
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
//        List<HotArticlevo> hotArticlevos = new ArrayList<>();
//        for (Article record : records) {
//            HotArticlevo vo = new HotArticlevo();
//            BeanUtils.copyProperties(record,vo);
//            hotArticlevos.add(vo);
//        }
        List<HotArticlevo> hotArticlevos = BeanCopyUtils.copyBeanList(records, HotArticlevo.class);

        return ResponseResult.okResult(hotArticlevos);

    }

    /**
     * index 和分类页面的blog显示,这里我们希望return对象包含双表中的数据
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        是否正式发布，按照置顶字段排序
        articleLambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop);
//        如果前端传过来了分类id,我就加上，没传，我就显示所有，这里其实对应两个功能
        if(categoryId!=null&& categoryId>0){
            articleLambdaQueryWrapper.eq(Article::getCategoryId,categoryId);
        }
//        分页查询
        Page<Article> articlePage = new Page<>(pageNum,pageSize);
        page(articlePage, articleLambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();
        /**
         * 使用 for循环赋值
         */
//        for (Article record : records) {
//            Category byId = categoryService.getById(record.getCategoryId());
//            record.setCategoryName(byId.getName());
//        }
        /**
         * 使用stream流赋值,本质也是循环赋值
         */
        //            参数是每个小的article
        List<Article> collect = records.stream().map(article -> {
            Category cateid = categoryService.getById(article.getCategoryId());
            String name = cateid.getName();
            article.setCategoryName(name);
            return article;
        }).collect(Collectors.toList());


//        将article封装到vo
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(collect, ArticleListVo.class);
//        不穿articlevo，传pagevo,pagevo里面的list就是页面数据
        PageVo pageVo = new PageVo(articleListVos, articlePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }


}

