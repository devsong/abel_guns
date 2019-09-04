package com.stylefeng.guns.common.constant.enums;

import lombok.Getter;

@Getter
public enum Order {
    ASC("asc"),

    DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }
}
