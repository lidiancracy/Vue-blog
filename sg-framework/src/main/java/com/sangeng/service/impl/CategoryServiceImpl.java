package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.entity.Article;
import com.sangeng.entity.Category;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-08-10 12:56:48
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult getCategoryList() {
//        查询文章表，状态为已发布的文章,如果直接list默认 查询本类的list
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
//        获取不重复的分类id
        Set<Long> catesets = new HashSet<>();
        for (Article article : articleList) {
            catesets.add(article.getCategoryId());
        }
//封装到catevo 里面 只需要id 和 name
        List<Category> categories = categoryService.listByIds(catesets);
//这里有一个问题, listbyid可以查出所有满足id的list，但是我希望加入status判断条件
        List<Category> collect = categories.stream().filter(new Predicate<Category>() {
            @Override
            public boolean test(Category category) {
                return category.getStatus().equals(SystemConstants.STATUS_NORMAL);
            }
        }).collect(Collectors.toList());
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(collect, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);

    }
}
