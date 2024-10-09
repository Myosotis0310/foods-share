package com.group2.foodsshare.controller;

import com.group2.foodsshare.common.Result;
import com.group2.foodsshare.entity.Notebook;
import com.group2.foodsshare.service.NotebookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Joey
 * @Date 2024/10
 */
@RestController
@RequestMapping("/notebook")
public class NotebookController {

    @Resource
    private NotebookService notebookService;

    /**
     * 新增
     * @param notebook
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Notebook notebook) {
        notebookService.add(notebook);
        return Result.success(notebook);
    }

    /**
     * 查询所有
     * @param notebook
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notebook notebook){
        List<Notebook> list = notebookService.selectAll(notebook);
        return Result.success(list);
    }

}
