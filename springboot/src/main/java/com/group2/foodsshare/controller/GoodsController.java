package com.group2.foodsshare.controller;


import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Goods;
import com.group2.foodsshare.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品信息前端操作接口
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
//
    @Resource
    private GoodsService goodsService;

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Goods goods = goodsService.selectById(id);
        return Result.success(goods);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Goods goods,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Goods> page = goodsService.selectPage(goods, pageNum, pageSize);
        return Result.success(page);
    }

}
