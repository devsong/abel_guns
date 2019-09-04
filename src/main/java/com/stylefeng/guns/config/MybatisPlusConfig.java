package com.stylefeng.guns.config;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import com.stylefeng.guns.common.aop.AopOrder;
import com.stylefeng.guns.common.constant.DSEnum;
import com.stylefeng.guns.config.properties.GunsDataSourceProperties;
import com.stylefeng.guns.config.properties.LogDataSourceProperties;
import com.stylefeng.guns.core.datascope.DataScopeInterceptor;
import com.stylefeng.guns.core.mutidatesource.DynamicDataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * MybatisPlus配置
 */
@Configuration
@EnableTransactionManagement(order = AopOrder.TRANSACTION_ORDER, proxyTargetClass = true)
// 由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = { "com.stylefeng.guns.common.persistence.dao" })
public class MybatisPlusConfig {
    @Autowired
    GunsDataSourceProperties gunsDataSourceProperties;

    @Autowired
    LogDataSourceProperties logDataSourceProperties;

    /**
     * 另一个数据源
     */
    private DruidDataSource dataSourceLogs() {
        DruidDataSource dataSource = new DruidDataSource();
        logDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceGuns() {
        DruidDataSource dataSource = new DruidDataSource();
        gunsDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceGuns();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {
        DruidDataSource dataSourceGuns = dataSourceGuns();
        DruidDataSource dataSourceLogs = dataSourceLogs();

        try {
            dataSourceGuns.init();
            dataSourceLogs.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> map = Maps.newHashMap();
        map.put(DSEnum.DATA_SOURCE_GUNS, dataSourceGuns);
        map.put(DSEnum.DATA_SOURCE_LOGS, dataSourceLogs);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
