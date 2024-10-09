package com.group2.foodsshare.service;

import com.group2.foodsshare.entity.User;
import com.group2.foodsshare.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * @Author Joey
 * @Date 2024/10
 */
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectById(Integer id){
        return userMapper.selectById(id);
    }

}
