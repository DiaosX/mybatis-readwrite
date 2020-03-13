package com.mytest.mybatisreadwriteway2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.mytest.mybatisreadwriteway2.dao"})
@EnableTransactionManagement(order = 10)
public class MybatisReadwriteWay2Application {
    public static void main(String[] args) {
        SpringApplication.run(MybatisReadwriteWay2Application.class, args);
    }
}
