package com.stylefeng.guns.common.constant.dictmap.base;

import java.util.Map;

import com.google.common.collect.Maps;

public abstract class AbstractDictMap {
    protected Map<String, String> dictory = Maps.newHashMap();
    protected Map<String, String> fieldWarpperDictory = Maps.newHashMap();

    public AbstractDictMap() {
        put("id", "主键id");
        init();
        initBeWrapped();
    }

    public abstract void init();

    protected abstract void initBeWrapped();

    public String get(String key) {
        return this.dictory.get(key);
    }

    public void put(String key, String value) {
        this.dictory.put(key, value);
    }

    public String getFieldWarpperMethodName(String key) {
        return this.fieldWarpperDictory.get(key);
    }

    public void putFieldWrapperMethodName(String key, String methodName) {
        this.fieldWarpperDictory.put(key, methodName);
    }
}
