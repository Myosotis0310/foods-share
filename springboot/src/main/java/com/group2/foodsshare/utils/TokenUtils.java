package com.group2.foodsshare.utils;

import com.group2.foodsshare.service.AdminService;
import com.group2.foodsshare.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author Joey
 * @Date 2024/10
 *
 * Token工具类
 */
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private static AdminService staticAdminService;
    private static UserService staticUserService;

    @Resource
    AdminService adminService;

    @Resource
    UserService userService;

    @PostConstruct      // 表示该方法会在依赖注入完成之后执行，通常用于初始化操作
    public void setUserService(){
        staticAdminService = adminService;
        staticUserService = userService;
    }

}
