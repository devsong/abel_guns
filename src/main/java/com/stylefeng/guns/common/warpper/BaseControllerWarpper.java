package com.stylefeng.guns.common.warpper;

import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

/**
 * 控制器查询结果的包装类基类
 */
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object warp() {
        if (this.obj instanceof List) {
            List<?> list = (List<?>) this.obj;
            for (Object obj : list) {
                if (obj instanceof Map) {
                    warpTheMap((Map) obj);
                } else {
                    warpTheMap(BeanMap.create(obj));
                }
            }
            return list;
        }
        if (this.obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.obj;
            warpTheMap(map);
            return map;
        }
        return this.obj;
    }

    protected abstract void warpTheMap(Map<String, Object> map);
}
