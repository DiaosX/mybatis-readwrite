package com.mytest.mybatisreadwriteway2.common.config;

import com.mytest.mybatisreadwriteway2.common.mybatis.DataSourceToggleInterceptor;
import com.mytest.mybatisreadwriteway2.common.mybatis.PrintSqlStatementInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig {
    @Resource(name = DataSourceConfig.ROUTING_DATASOURCE_NAME)
    private DataSource routingDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.mytest.mybatisreadwriteway1.model.entity");
        sqlSessionFactoryBean.setPlugins(new DataSourceToggleInterceptor(), new PrintSqlStatementInterceptor());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(routingDataSource);
    }
}
