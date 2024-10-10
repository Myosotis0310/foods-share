package com.group2.foodsshare.controller;


import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Category;
import com.group2.foodsshare.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜谱分类信息表前端操作接口
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
//
    @Resource
    private CategoryService categoryService;




    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Category category ) {
        List<Category> list = categoryService.selectAll(category);
        return Result.success(list);
    }



}
