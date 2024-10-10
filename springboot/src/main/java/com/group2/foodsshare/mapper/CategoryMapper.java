package com.group2.foodsshare.mapper;


import com.group2.foodsshare.entity.Category;

import java.util.List;
//
/**
 * 操作category相关数据接口
*/
public interface CategoryMapper {


    /**
      * 查询所有
    */
    List<Category> selectAll(Category category);

}
