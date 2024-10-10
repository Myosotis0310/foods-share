package com.group2.foodsshare.mapper;



import com.group2.foodsshare.entity.Goods;

import java.util.List;

/**
 * 操作goods相关数据接口
 */
public interface GoodsMapper {

    /**
     * 修改
     */
    int updateById(Goods goods);

    /**
     * 根据ID查询
     */
    Goods selectById(Integer id);

    /**
     * 查询所有
     */
    List<Goods> selectAll(Goods goods);

}
