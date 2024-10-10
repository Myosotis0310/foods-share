package com.group2.foodsshare.controller;

import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Notebook;
import com.group2.foodsshare.service.NotebookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 美食笔记前端操作接口
 **/
@RestController
@RequestMapping("/notebook")
public class NotebookController {

    @Resource
    private NotebookService notebookService;


    @PutMapping("/updateCount/{id}")
    public Result updateCount(@PathVariable Integer id) {
        notebookService.updateCount(id);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notebook notebook = notebookService.selectById(id);
        return Result.success(notebook);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notebook notebook,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notebook> page = notebookService.selectPage(notebook, pageNum, pageSize);
        return Result.success(page);
    }


}
