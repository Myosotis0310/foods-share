package com.group2.foodsshare.common.enums;

/**
 * 定义模块数据
 */
public enum ModuleEnum {
    FOODS("菜谱"),
    NOTEBOOK("笔记"),
    ;

    private String value;

    public String getValue() {
        return value;
    }

    ModuleEnum(String value) {
        this.value = value;
    }


}
