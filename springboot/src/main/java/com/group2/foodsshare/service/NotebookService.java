package com.group2.foodsshare.service;

import cn.hutool.core.date.DateUtil;
import com.group2.foodsshare.entity.Notebook;
import com.group2.foodsshare.mapper.NotebookMapper;

import java.util.List;

/**
 * @Author Joey
 * @Date 2024/10
 */
public class NotebookService {

    private NotebookMapper notebookMapper;

    /**
     * 新增笔记
     * @param notebook
     */
    public void add(Notebook notebook) {
        notebook.setDate(DateUtil.today());
        notebookMapper.insert(notebook);
    }

    /**
     * 查询所有发布
     * @param notebook
     * @return
     */
    public List<Notebook> selectAll(Notebook notebook) {

        return null;
    }

    /**
     * 查询所有收藏
     * @param notebook
     * @return
     */
    public List<Notebook> selectCollect(Notebook notebook) {

        return null;
    }
}
