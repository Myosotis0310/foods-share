package com.group2.foodsshare.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.entity.Category;
import com.group2.foodsshare.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
//
/**
 * 菜谱分类表业务处理
 **/
@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;



    /**
     * 查询所有
     */
    public List<Category> selectAll(Category category) {
        return categoryMapper.selectAll(category);
    }



}
