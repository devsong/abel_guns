package com.stylefeng.guns.common.constant.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.stylefeng.guns.common.constant.Const;

/**
 * 
 * @author guanzhisong
 *
 */
public class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private String threadPoolName;

    public NamedThreadFactory() {
        this.threadPoolName = Const.MODULE_NAME;
    }

    public NamedThreadFactory(String name) {
        this.threadPoolName = Const.MODULE_NAME + '-' + name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(threadPoolName + '-' + threadNumber.getAndIncrement());
        return thread;
    }
}
