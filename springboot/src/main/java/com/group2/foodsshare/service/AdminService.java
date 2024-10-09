package com.group2.foodsshare.service;

import com.group2.foodsshare.entity.Admin;
import com.group2.foodsshare.mapper.AdminMapper;

import javax.annotation.Resource;

/**
 * @Author Joey
 * @Date 2024/10
 *
 * 管理员 业务处理
 */
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Admin selectById(Integer id){
        return adminMapper.selectById(id);
    }

}
