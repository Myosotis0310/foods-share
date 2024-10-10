package com.group2.foodsshare.controller;


import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Orders;
import com.group2.foodsshare.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 **/
@RestController
@RequestMapping("/orders")
public class OrdersController {
//
    @Resource
    private OrdersService ordersService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return Result.success();
    }




    /*@PutMapping("/cancel")
    public Result cancel(@RequestBody Orders orders) {
        ordersService.cancel(orders);
        return Result.success();
    }*/



}
