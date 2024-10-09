package com.group2.foodsshare.common.enums;

public enum OrderStatusEnum {
    CANCEL("已取消"),
    NO_SEND("待发货"),
    NO_ACCEPT("待收货"),
    DONE("已完成");

    private String value;

    public String getValue() {
        return value;
    }

    OrderStatusEnum(String value) {
        this.value = value;
    }

}
