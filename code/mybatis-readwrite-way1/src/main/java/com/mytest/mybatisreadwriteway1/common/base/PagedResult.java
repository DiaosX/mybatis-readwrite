package com.mytest.mybatisreadwriteway1.common.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedResult<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalCount;
    private List<T> items;
}