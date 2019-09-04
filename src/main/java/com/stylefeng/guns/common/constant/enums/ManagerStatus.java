package com.stylefeng.guns.common.constant.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public enum ManagerStatus {
    OK(1, "启用"),

    FREEZED(2, "冻结"),

    DELETED(3, "被删除");

    private static Map<Integer, ManagerStatus> HOLDER = Maps.newHashMap();

    static {
        for (ManagerStatus s : values()) {
            HOLDER.put(s.getCode(), s);
        }
    }

    int code;
    String message;

    ManagerStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        }
        ManagerStatus s = HOLDER.get(value);
        if (s == null) {
            return "";
        }
        return s.getMessage();
    }
}
