package com.group2.foodsshare.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group2.foodsshare.common.enums.ModuleEnum;
import com.group2.foodsshare.common.enums.RoleEnum;
import com.group2.foodsshare.entity.Account;
import com.group2.foodsshare.entity.Collect;
import com.group2.foodsshare.entity.Likes;
import com.group2.foodsshare.entity.Notebook;
import com.group2.foodsshare.mapper.NotebookMapper;
import com.group2.foodsshare.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 美食笔记业务处理
 **/
@Service
public class NotebookService {

    @Resource
    private NotebookMapper notebookMapper;

    @Resource
    private CollectService collectService;

    @Resource
    private LikesService likesService;





    /**
     * 根据ID查询
     */
    public Notebook selectById(Integer id) {
        Notebook notebook = notebookMapper.selectById(id);
        Account currentUser = TokenUtils.getCurrentUser();
        Integer userId = null;
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            userId = currentUser.getId();
        }
        List<Collect> collectList = collectService.selectCollect(id, userId, ModuleEnum.NOTEBOOK.getValue());
        notebook.setUserCollect(CollUtil.isNotEmpty(collectList));

        List<Collect> list = collectService.selectCollect(id, null, ModuleEnum.NOTEBOOK.getValue());
        notebook.setCollectCount(list.size());

        List<Likes> likesList = likesService.selectLikes(id, userId, ModuleEnum.NOTEBOOK.getValue());
        notebook.setUserLike(CollUtil.isNotEmpty(likesList));

        List<Likes> allLikesList = likesService.selectLikes(id, null, ModuleEnum.NOTEBOOK.getValue());
        notebook.setLikesCount(allLikesList.size());
        return notebook;
    }


//
    /**
     * 分页查询
     */
    public PageInfo<Notebook> selectPage(Notebook notebook, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notebook> list = notebookMapper.selectAll(notebook);
        return PageInfo.of(list);
    }

    public void updateCount(Integer id) {
        notebookMapper.updateCount(id);
    }






}
