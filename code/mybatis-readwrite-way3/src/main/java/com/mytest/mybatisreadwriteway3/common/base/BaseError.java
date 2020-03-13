package com.mytest.mybatisreadwriteway3.common.base;

public interface BaseError {

    /**
     * 返回码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getErrMsg();
}
