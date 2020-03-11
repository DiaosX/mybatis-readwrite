package com.mytest.mybatisreadwriteway1.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedApiResponse<T> extends ApiResponse<T> {
    private PagedResult<T> pager;

    public PagedApiResponse() {

    }

    public PagedApiResponse(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public PagedApiResponse(String code, String errMsg) {
        this.success = false;
        this.code = code;
        this.errMsg = errMsg;
    }

    public PagedApiResponse(String code, PagedResult<T> pager) {
        this.code = code;
        this.pager = pager;
        this.success = true;
    }

    /**
     * 成功
     *
     * @param pager
     * @return
     */
    public static <T> PagedApiResponse<T> success(PagedResult<T> pager) {
        return new PagedApiResponse<>(CommonEnum.SUCCESS.getCode(), pager);
    }

    /**
     * 失败
     */
    public static PagedApiResponse error(BaseError errorInfo) {
        return new PagedApiResponse(errorInfo);
    }

    /**
     * 失败
     */
    public static PagedApiResponse error(String code, String message) {
        return new PagedApiResponse(code, message);
    }

    /**
     * 失败
     */
    public static PagedApiResponse error(String message) {
        return new PagedApiResponse("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
