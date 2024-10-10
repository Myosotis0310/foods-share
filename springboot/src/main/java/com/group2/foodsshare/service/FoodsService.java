package com.group2.foodsshare.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.enums.ModuleEnum;
import com.group2.foodsshare.common.enums.RoleEnum;
import com.group2.foodsshare.entity.Account;
import com.group2.foodsshare.entity.Collect;
import com.group2.foodsshare.entity.Foods;
import com.group2.foodsshare.mapper.FoodsMapper;
import com.group2.foodsshare.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 美食业务处理
 **/
@Service
public class FoodsService {

    @Resource
    private FoodsMapper foodsMapper;
    @Resource
    CollectService collectService;


    /**
     * 根据ID查询
     */
    public Foods selectById(Integer id) {
        Foods foods = foodsMapper.selectById(id);
        Account currentUser = TokenUtils.getCurrentUser();
        Integer userId = null;
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            userId = currentUser.getId();
        }
        List<Collect> collectList = collectService.selectCollect(id, userId, ModuleEnum.FOODS.getValue());
        foods.setUserCollect(CollUtil.isNotEmpty(collectList));

        List<Collect> list = collectService.selectCollect(id, null, ModuleEnum.FOODS.getValue());
        foods.setCollectCount(list.size());
        return foods;
    }



    /**
     * 分页查询
     */
    public PageInfo<Foods> selectPage(Foods foods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Foods> list = foodsMapper.selectAll(foods);
        return PageInfo.of(list);
    }

    /**
     * 查询推荐的菜谱
     * 浏览榜单前4
     * 从前10的菜谱里面随机找到4个
     */
    public List<Foods> selectRecommend() {
        List<Foods> foodsList = foodsMapper.selectTop10();
        if (foodsList.size() <= 4) {
            return foodsList;
        }
        List<Foods> foods = new ArrayList<>();
        Set<Integer> nums = new HashSet<>();
        while (nums.size() < 4) {
            int num = RandomUtil.randomInt(0, foodsList.size() - 1); // 随机找到一个序号 这个序号就是 foodsList 其中一个元素的序号
            if (!nums.contains(num)) {
                nums.add(num);
                Foods f = foodsList.get(num);
                foods.add(f);
            }
        }
        return foods;
    }

    public void updateCount(Integer id) {
        foodsMapper.updateCount(id);
    }


}
