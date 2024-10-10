package com.group2.foodsshare.service;

import cn.hutool.core.collection.CollUtil;

import com.group2.foodsshare.entity.Collect;
import com.group2.foodsshare.mapper.CollectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectService {

    @Resource
    CollectMapper collectMapper;

    /**
     * 1. 收藏
     * 2. 取消收藏
     * 取决于当前的模块数据有没有被收藏
     */
    public void set(Collect collect) {
        List<Collect> collectList = collectMapper.selectCollect(collect);
        if (CollUtil.isNotEmpty(collectList)) {  // 收藏过了
            collectMapper.delete(collect);
        } else {
            collectMapper.insert(collect);
        }
    }
    public List<Collect> selectCollect(Integer fid, Integer userId, String module) {
        Collect collect = new Collect();
        collect.setFid(fid);
        collect.setUserId(userId);
        collect.setModule(module);
        return collectMapper.selectCollect(collect);
    }


}
