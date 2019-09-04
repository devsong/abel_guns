package com.stylefeng.guns.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.common.annotion.DataSource;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.core.mutidatesource.DataSourceContextHolder;

/**
 * 多数据源切换的aop
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
public class MultiSourceExAop implements Ordered {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "within(@com.stylefeng.guns.common.annotion.DataSource *)")
    private void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());

        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        if (datasource == null) {
            datasource = target.getClass().getAnnotation(DataSource.class);
        }
        if (datasource != null) {
            DataSourceContextHolder.setDataSourceType(datasource.value());
            log.info("设置数据源为：" + datasource.value());
        } else {
            DataSourceContextHolder.setDataSourceType(DSEnum.DATA_SOURCE_GUNS);
            log.info("设置数据源为：dataSourceCurrent");
        }

        try {
            return point.proceed();
        } finally {
            log.debug("清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    @Override
    public int getOrder() {
        return AopOrder.DS_ORDER;
    }
}
