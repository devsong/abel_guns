package com.stylefeng.guns.core.log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.stylefeng.guns.common.constant.factory.NamedThreadFactory;

/**
 * 日志管理器
 *
 * @author fengshuonan
 * @date 2017-03-30 16:29
 */
public class LogManager {
    // 日志记录操作延时
    private final int OPERATE_DELAY_TIME = 10;

    // 异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, new NamedThreadFactory("asynclogs"));

    private LogManager() {
    }

    private static LogManager logManager = new LogManager();

    public static LogManager me() {
        return logManager;
    }

    public void executeLog(Runnable task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
