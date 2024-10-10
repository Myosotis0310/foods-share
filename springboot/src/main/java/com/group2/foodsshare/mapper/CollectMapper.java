package com.group2.foodsshare.mapper;

import com.group2.foodsshare.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface CollectMapper {

    List<Collect> selectCollect(Collect collect);

    @Delete("delete from collect where fid = #{fid} and user_id = #{userId} and module = #{module}")
    void delete(Collect collect);

    @Insert("insert into collect (fid, user_id, module) values(#{fid}, #{userId}, #{module})")
    void insert(Collect collect);

}
