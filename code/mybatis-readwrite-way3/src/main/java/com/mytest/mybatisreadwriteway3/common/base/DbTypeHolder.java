package com.mytest.mybatisreadwriteway3.common.base;

import com.mytest.mybatisreadwriteway1.common.enums.DbTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class DbTypeHolder {

    private static Logger log = LoggerFactory.getLogger(DbTypeHolder.class);

    private static ThreadLocal<DbTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);


    public static void set(DbTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DbTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DbTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DbTypeEnum.SLAVE1);
            log.info("切换到slave1");
        } else {
            set(DbTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }
}
