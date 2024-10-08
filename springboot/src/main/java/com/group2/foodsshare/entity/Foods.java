package com.group2.foodsshare.entity;

/**
 * 菜谱
 */
public class Foods {

    /** ID */
    private Integer id;
    /** 名称 */
    private String name;
    /** 封面 */
    private String cover;
    /** 简介 */
    private String descr;
    /** 技巧 */
    private String skills;
    /** 作者ID */
    private Integer userId;
    /** 上传时间 */
    private String time;
    /** 浏览量 */
    private Integer count;
    private String material;
    private String steps;
    private String status;
    private String category;

    private String userName;
    private String avatar;

    /** 表示用户是否收藏过这个菜谱  */
    private Boolean userCollect;
    /** 表示菜谱的收藏量  */
    private Integer collectCount;

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Boolean getUserCollect() {
        return userCollect;
    }

    public void setUserCollect(Boolean userCollect) {
        this.userCollect = userCollect;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}