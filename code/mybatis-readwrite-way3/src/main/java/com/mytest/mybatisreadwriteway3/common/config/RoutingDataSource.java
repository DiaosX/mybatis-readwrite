package com.mytest.mybatisreadwriteway3.common.config;

import com.mytest.mybatisreadwriteway3.common.base.DbTypeHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DbTypeHolder.get();
    }
}