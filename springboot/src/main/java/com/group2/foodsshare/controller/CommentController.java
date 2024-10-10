package com.group2.foodsshare.controller;


import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Comment;
import com.group2.foodsshare.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表前端操作接口
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {
//
    @Resource
    private CommentService commentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
        return Result.success();
    }



    @GetMapping("/selectCount/{fid}/{module}")
    public Result selectCount(@PathVariable Integer fid, @PathVariable String module) {
        Integer count = commentService.selectCount(fid, module);
        return Result.success(count);
    }

    @GetMapping("/selectTree/{fid}/{module}")
    public Result selectTree(@PathVariable Integer fid, @PathVariable String module) {
        List<Comment> list = commentService.selectTree(fid, module);
        return Result.success(list);
    }

}
