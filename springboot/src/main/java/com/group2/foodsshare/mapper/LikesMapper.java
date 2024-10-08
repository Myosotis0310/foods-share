package com.group2.foodsshare.mapper;

import com.group2.foodsshare.entity.Likes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface LikesMapper {

    List<Likes> selectLikes(Likes likes);

    @Delete("delete from likes where fid = #{fid} and user_id = #{userId} and module = #{module}")
    void delete(Likes likes);

    @Insert("insert into likes (fid, user_id, module) values(#{fid}, #{userId}, #{module})")
    void insert(Likes likes);
}
