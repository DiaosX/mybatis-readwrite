package com.mytest.mybatisreadwriteway1.common.config;

import com.mytest.mybatisreadwriteway1.common.enums.DbTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private static final String MASTER_DATASOURCE_NAME = "masterDataSource";
    private static final String SLAVE1_DATASOURCE_NAME = "slave1DataSource";
    private static final String SLAVE2_DATASOURCE_NAME = "slave2DataSource";
    public static final String ROUTING_DATASOURCE_NAME = "routingDataSource";

    @Bean(name = MASTER_DATASOURCE_NAME)
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = SLAVE1_DATASOURCE_NAME)
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = SLAVE2_DATASOURCE_NAME)
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = ROUTING_DATASOURCE_NAME)
    public DataSource routingDataSource(@Qualifier(MASTER_DATASOURCE_NAME) DataSource masterDataSource,
                                        @Qualifier(SLAVE1_DATASOURCE_NAME) DataSource slave1DataSource,
                                        @Qualifier(SLAVE2_DATASOURCE_NAME) DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DbTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DbTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DbTypeEnum.SLAVE2, slave2DataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }
}
