package com.mytest.mybatisreadwriteway2.common.config;

import com.mytest.mybatisreadwriteway2.common.base.DbTypeHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        if (DbTypeHolder.get() == null) {
            DbTypeHolder.master();
        }
        return DbTypeHolder.get();
    }
}