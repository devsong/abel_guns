package com.stylefeng.guns.config.properties;

import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;

@Data
public class BaseDataSourceProperties {
    protected String driverClassName;

    protected Integer initialSize = 2;

    protected Integer minIdle = 1;

    protected Integer maxActive = 20;

    protected Integer maxWait = 60000;

    protected Integer timeBetweenEvictionRunsMillis = 60000;

    protected Integer minEvictableIdleTimeMillis = 300000;

    protected String validationQuery = "SELECT 'x'";

    protected Boolean testWhileIdle = true;

    protected Boolean testOnBorrow = false;

    protected Boolean testOnReturn = false;

    protected Boolean poolPreparedStatements = true;

    protected Integer maxPoolPreparedStatementPerConnectionSize = 20;

    protected String filters = "stat";

    protected void config(DruidDataSource dataSource) {
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize); // 定义初始连接数
        dataSource.setMinIdle(minIdle); // 最小空闲
        dataSource.setMaxActive(maxActive); // 定义最大连接数
        dataSource.setMaxWait(maxWait); // 最长等待时间

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
