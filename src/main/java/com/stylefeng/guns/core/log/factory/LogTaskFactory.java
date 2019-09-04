package com.stylefeng.guns.core.log.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stylefeng.guns.common.constant.enums.LogSucceed;
import com.stylefeng.guns.common.constant.enums.LogType;
import com.stylefeng.guns.common.persistence.model.logs.LoginLog;
import com.stylefeng.guns.common.persistence.model.logs.OperationLog;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.biz.service.ISystemLogService;

/**
 * 日志操作任务创建工厂
 */
public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static ISystemLogService systemLogService = SpringContextHolder.getBean(ISystemLogService.class);

    public static Runnable loginLog(final Integer userId, final String ip) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, null, ip);
                    systemLogService.saveLoginLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static Runnable loginLog(final String username, final String msg, final String ip) {
        return new Runnable() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                    systemLogService.saveLoginLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static Runnable exitLog(final Integer userId, final String ip) {
        return new Runnable() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
                try {
                    systemLogService.saveLoginLog(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static Runnable bussinessLog(final Integer userId, final String bussinessName, final String clazzName, final String methodName,
            final String msg) {
        return new Runnable() {
            @Override
            public void run() {
                OperationLog operationLog = LogFactory.createOperationLog(LogType.BUSSINESS, userId, bussinessName, clazzName, methodName,
                        msg, LogSucceed.SUCCESS);
                try {
                    systemLogService.saveOperationLog(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static Runnable exceptionLog(final Integer userId, final Exception exception) {
        return new Runnable() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                OperationLog operationLog = LogFactory.createOperationLog(LogType.EXCEPTION, userId, "", null, null, msg, LogSucceed.FAIL);
                try {
                    systemLogService.saveOperationLog(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}
