package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 数据库数据源配置
 * </p>
 * <p>
 * 说明:这个类中包含了许多默认配置,若这些配置符合您的情况,您可以不用管,若不符合,建议不要修改本类,建议直接在"application.yml"中配置即可
 * </p>
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
@EqualsAndHashCode(callSuper = false)
public class GunsDataSourceProperties extends BaseDataSourceProperties {
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
