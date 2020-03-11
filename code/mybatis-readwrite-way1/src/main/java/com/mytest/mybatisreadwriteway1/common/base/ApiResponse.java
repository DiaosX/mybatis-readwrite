package com.mytest.mybatisreadwriteway1.common.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    protected boolean success;
    protected String code;
    protected String errMsg;
    protected T data;

    public ApiResponse() {

    }

    public ApiResponse(BaseError errorInfo) {
        this(errorInfo.getCode(), errorInfo.getErrMsg());
    }

    public ApiResponse(String code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
        this.success = false;
    }

    public ApiResponse(String code, T data) {
        this.code = code;
        this.data = data;
        this.success = true;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ApiResponse success() {
        return ApiResponse.success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(CommonEnum.SUCCESS.getCode(), data);
    }

    /**
     * 失败
     */
    public static ApiResponse error(BaseError errorInfo) {
        return new ApiResponse(errorInfo);
    }

    /**
     * 失败
     */
    public static ApiResponse error(String code, String message) {
        return new ApiResponse(code, message);
    }

    /**
     * 失败
     */
    public static ApiResponse error(String message) {
        return new ApiResponse("-1", message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}