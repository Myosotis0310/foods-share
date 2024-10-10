package com.group2.foodsshare.controller;

import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Foods;
import com.group2.foodsshare.service.FoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 美食前端操作接口
 **/
@RestController
@RequestMapping("/foods")
public class FoodsController {

    @Resource
    private FoodsService foodsService;


    @PutMapping("/updateCount/{id}")
    public Result updateCount(@PathVariable Integer id) {
        foodsService.updateCount(id);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Foods foods = foodsService.selectById(id);
        return Result.success(foods);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Foods foods,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Foods> page = foodsService.selectPage(foods, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectRecommend")
    public Result selectRecommend() {
        List<Foods> list = foodsService.selectRecommend();
        return Result.success(list);
    }




}
