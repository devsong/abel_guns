package com.stylefeng.guns.common.constant.dictmap.factory;

import com.stylefeng.guns.common.constant.dictmap.base.AbstractDictMap;
import com.stylefeng.guns.common.constant.dictmap.base.SystemDict;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;

public class DictMapFactory {
    private static final String basePath = "com.stylefeng.guns.common.constant.dictmap.";

    @SuppressWarnings("unchecked")
    public static AbstractDictMap createDictMap(String className) {
        if ("SystemDict".equals(className)) {
            return new SystemDict();
        }
        try {
            Class<AbstractDictMap> clazz = (Class<AbstractDictMap>) Class.forName(basePath + className);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BussinessException(BizExceptionEnum.ERROR_CREATE_DICT);
        }
    }
}
