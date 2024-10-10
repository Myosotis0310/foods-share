package com.group2.foodsshare.mapper;


import com.group2.foodsshare.entity.Foods;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作foods相关数据接口
 *///
public interface FoodsMapper {

    /**
     * 根据ID查询
     */
    Foods selectById(Integer id);

    /**
     * 查询所有
     */
    List<Foods> selectAll(Foods foods);

    List<Foods> selectTop10();

    @Update("update foods set count = count + 1 where id = #{id}")
    void updateCount(Integer id);



}
