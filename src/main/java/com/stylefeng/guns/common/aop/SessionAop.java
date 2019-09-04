package com.stylefeng.guns.common.aop;

import com.stylefeng.guns.common.controller.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.stylefeng.guns.core.util.HttpSessionHolder;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 静态调用session的拦截器
 */
@Aspect
@Component
public class SessionAop extends BaseController implements Ordered {
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {
        HttpSessionHolder.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionHolder.remove();
        }
    }

    @Override
    public int getOrder() {
        return AopOrder.SESSION_ORDER;
    }
}
