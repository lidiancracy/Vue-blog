package com.sangeng.controller;


import com.sangeng.entity.Category;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类表(Category)表控制层
 *
 * @author makejava
 * @since 2022-08-10 13:11:31
 */
@RestController
@RequestMapping("category")
public class CategoryController {
   @Autowired
   private CategoryService categoryService;
   @GetMapping("/getCategoryList")
   public ResponseResult getcate(){
      return categoryService.getCategoryList();
   }
}

