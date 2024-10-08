package com.group2.foodsshare.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 */
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 订单编号 */
    private String orderNo;
    /** 商品名称 */
    private String name;
    /** 商品图片 */
    private String img;
    /** 商品单价 */
    private BigDecimal price;
    /** 购买数量 */
    private Integer num;
    /** 总价 */
    private BigDecimal total;
    /** 状态 */
    private String status;
    /** 下单时间 */
    private String time;
    /** 用户ID */
    private Integer userId;
    /** 收货地址 */
    private String address;
    /** 手机号码 */
    private String phone;
    /** 商品ID */
    private Integer goodsId;

    private String userName;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

}