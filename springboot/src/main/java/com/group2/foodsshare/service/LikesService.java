package com.group2.foodsshare.service;

import cn.hutool.core.collection.CollUtil;
import com.group2.foodsshare.entity.Likes;
import com.group2.foodsshare.mapper.LikesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikesService {

    @Resource
    LikesMapper likesMapper;

    /**
     * 1. 点赞
     * 2. 取消点赞
     * 取决于当前的模块数据有没有被点赞
     */
    public void set(Likes likes) {
        List<Likes> likesList = likesMapper.selectLikes(likes);
        if (CollUtil.isNotEmpty(likesList)) {  // 收藏过了
            likesMapper.delete(likes);
        } else {
            likesMapper.insert(likes);
        }
    }

    public List<Likes> selectLikes(Integer fid, Integer userId, String module) {
        Likes likes = new Likes();
        likes.setFid(fid);
        likes.setUserId(userId);
        likes.setModule(module);
        return likesMapper.selectLikes(likes);
    }

}
