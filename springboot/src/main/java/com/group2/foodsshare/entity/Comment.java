package com.group2.foodsshare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 评论表
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 内容 */
    private String content;
    /** 评论时间 */
    private String time;
    /** 模块 */
    private String module;
    private Integer userId;
    private Integer pid;
    private Integer fid;
    private Integer rootId;

    private String avatar;
    private String userName;

    private List<Comment> children;

    private String parentUserName;

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

}