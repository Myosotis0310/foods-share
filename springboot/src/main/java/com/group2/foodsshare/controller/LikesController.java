package com.group2.foodsshare.controller;

import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Likes;
import com.group2.foodsshare.service.LikesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Resource
    LikesService likesService;

    @PostMapping("/set")
    public Result set(@RequestBody Likes likes) {
        likesService.set(likes);
        return Result.success();
    }

}
