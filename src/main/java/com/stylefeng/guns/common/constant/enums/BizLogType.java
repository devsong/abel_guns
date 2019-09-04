package com.stylefeng.guns.common.constant.enums;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;

/**
 * 业务日志类型
 */
@Getter
public enum BizLogType {

    ALL(0, null),

    BUSSINESS(1, "业务日志"),

    EXCEPTION(2, "异常日志");

    Integer val;
    String message;

    private static Map<Integer, BizLogType> holder = Maps.newHashMap();

    static {
        for (BizLogType v : values()) {
            holder.put(v.getVal(), v);
        }
    }

    BizLogType(Integer val, String message) {
        this.val = val;
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        BizLogType logType = holder.get(value);
        if (logType == null) {
            return "";
        }
        return logType.getMessage();
    }
}
