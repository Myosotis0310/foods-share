package com.group2.foodsshare.controller;


import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Collect;
import com.group2.foodsshare.service.CollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/collect")
public class CollectController {
//
    @Resource
    CollectService collectService;

    @PostMapping("/set")
    public Result set(@RequestBody Collect collect) {
        collectService.set(collect);
        return Result.success();
    }


}
