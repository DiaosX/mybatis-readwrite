package com.mytest.mybatisreadwriteway1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mytest.mybatisreadwriteway1.dao"})
public class MybatisReadwriteWay1Application {
    public static void main(String[] args) {
        SpringApplication.run(MybatisReadwriteWay1Application.class, args);
    }
}
