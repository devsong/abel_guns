package com.stylefeng.guns.common.constant;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.stylefeng.guns.common.constant.factory.NamedThreadFactory;

/**
 * 系统常量
 *
 * @date 2017年2月12日 下午9:42:53
 */
public interface Const {
    /**
     * 产品线
     */
    String PRODUCT_LINE = "foo";

    /**
     * 服务名
     */
    String SERVICE_NAME = "bar";

    /**
     * 模块名
     */
    String MODULE_NAME = "foo.bar";

    /**
     * 默认管理系统的名称
     */
    String DEFAULT_SYSTEM_NAME = "Guns管理系统";

    /**
     * 默认欢迎界面的提示
     */
    String DEFAULT_WELCOME_TIP = "欢迎使用Guns管理系统!";

    /**
     * 系统默认的管理员密码
     */
    String DEFAULT_PWD = "111111";

    /**
     * 管理员角色的名字
     */
    String ADMIN_NAME = "administrator";

    /**
     * 管理员id
     */
    Integer ADMIN_ID = 1;

    /**
     * 超级管理员角色id
     */
    Integer ADMIN_ROLE_ID = 1;

    /**
     * 接口文档的菜单名
     */
    String API_MENU_NAME = "接口文档";

    /**
     * 系统后台任务线程池(主要用于异步日志任务)
     */
    Executor SYSTEM_POOL = Executors.newFixedThreadPool(10, new NamedThreadFactory());

    String DRUID_URL = "/druid/*";

    /**
     * 静态资源目录
     */
    String STATIC_RESOURCES = "/assets/*,/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*";

    String[] STATIC_RESOURCES_ARRAY = STATIC_RESOURCES.split(",");

    /**
     * service 横切点
     */
    String SERVICE_POINTCUT = "com.stylefeng.guns.modular.*.service.*";
}
