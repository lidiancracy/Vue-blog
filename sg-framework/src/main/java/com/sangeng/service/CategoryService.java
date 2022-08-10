package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Category;
import com.sangeng.entity.R.ResponseResult;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-08-10 12:56:48
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
