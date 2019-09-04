package com.stylefeng.guns.config.properties;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 多数据源的配置
 *
 */
@Component
@ConfigurationProperties(prefix = "logs.datasource")
@Data
@EqualsAndHashCode(callSuper = false)
public class LogDataSourceProperties extends BaseDataSourceProperties {
    private String name;

    private String url;

    private String username;

    private String password;

    public void config(DruidDataSource dataSource) {
        dataSource.setName(getName());
        dataSource.setUrl(getUrl());
        dataSource.setUsername(getUsername());
        dataSource.setPassword(getPassword());
        super.config(dataSource);
    }
}
