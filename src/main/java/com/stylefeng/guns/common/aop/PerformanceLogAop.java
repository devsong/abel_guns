package com.stylefeng.guns.common.aop;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.google.common.base.Stopwatch;

public class PerformanceLogAop {
    /**
     * 对所有controller切片 (方法说明描述)
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void log4Controller() {
    }

    @Around("log4Controller()")
    public Object aroundController(ProceedingJoinPoint point) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object[] args = point.getArgs();
        Object returnValue = null;
        Exception exception = null;
        try {
            returnValue = point.proceed(args);
        } catch (Exception e) {
            exception = e;
        } finally {
            stopwatch.stop();
            long cost = stopwatch.elapsed(TimeUnit.MICROSECONDS);
            postProcess(point, exception, cost);
        }
        return returnValue;
    }

    @Pointcut("@annotation(com.stylefeng.guns.common.annotion.PerformanceAnnotation)")
    public void log4Perf() {
    }

    @Around("log4Perf()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object[] args = point.getArgs();
        Object returnValue = null;
        Exception exception = null;
        try {
            returnValue = point.proceed(args);
        } catch (Exception e) {
            exception = e;
        } finally {
            stopwatch.stop();
            long cost = stopwatch.elapsed(TimeUnit.MICROSECONDS);
            postProcess(point, exception, cost);
        }
        return returnValue;
    }

    private void postProcess(ProceedingJoinPoint point, Exception exception, long cost) {

    }
}
