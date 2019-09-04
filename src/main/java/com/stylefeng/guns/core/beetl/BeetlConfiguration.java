package com.stylefeng.guns.core.beetl;

import java.util.HashMap;
import java.util.Map;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.core.util.KaptchaUtil;
import com.stylefeng.guns.core.util.ToolUtil;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {
        // 全局共享变量
        Map<String, Object> shared = new HashMap<>();
        shared.put("systemName", Const.DEFAULT_SYSTEM_NAME);
        shared.put("welcomeTip", Const.DEFAULT_WELCOME_TIP);
        groupTemplate.setSharedVars(shared);

        // 全局共享方法
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    }

}
