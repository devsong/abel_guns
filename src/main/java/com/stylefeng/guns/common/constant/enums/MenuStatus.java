package com.stylefeng.guns.common.constant.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum MenuStatus {
    ENABLE(1, "启用"),

    DISABLE(0, "禁用");

    private static Map<Integer, MenuStatus> HOLDER = Maps.newHashMap();

    static {
        for (MenuStatus s : values()) {
            HOLDER.put(s.getCode(), s);
        }
    }

    int code;
    String message;

    MenuStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        }
        MenuStatus s = HOLDER.get(status);
        if (s == null) {
            return "";
        }
        return s.getMessage();
    }
}
