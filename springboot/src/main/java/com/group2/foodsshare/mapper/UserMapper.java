package com.group2.foodsshare.mapper;

import com.group2.foodsshare.entity.User;

/**
 * @Author Joey
 * @Date 2024/10
 *
 * 操作user相关数据接口
 */
public interface UserMapper {

    // 根据id查询
    User selectById(Integer id);

}
