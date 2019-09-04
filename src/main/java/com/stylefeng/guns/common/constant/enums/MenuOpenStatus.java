package com.stylefeng.guns.common.constant.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum MenuOpenStatus {
    OPEN(1, "打开"),

    CLOSE(0, "关闭");

    private static Map<Integer, MenuOpenStatus> HOLDER = Maps.newHashMap();

    static {
        for (MenuOpenStatus s : values()) {
            HOLDER.put(s.getCode(), s);
        }
    }

    int code;
    String message;

    MenuOpenStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        }
        MenuOpenStatus s = HOLDER.get(status);
        if (s == null) {
            return "";
        }
        return s.getMessage();
    }
}
