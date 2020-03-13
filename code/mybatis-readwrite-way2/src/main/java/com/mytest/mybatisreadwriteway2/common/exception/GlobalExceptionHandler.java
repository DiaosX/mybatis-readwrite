package com.mytest.mybatisreadwriteway2.common.exception;

import com.mytest.mybatisreadwriteway2.common.base.ApiResponse;
import com.mytest.mybatisreadwriteway2.common.base.CommonEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ApiResponse bizExceptionHandler(HttpServletRequest req, BizException e) {
        logger.error("请求url:[{}],发生业务异常！原因是：{}", req.getRequestURI(), e.getErrorMsg());
        return ApiResponse.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("请求url:[{}],发生空指针异常！原因是:[{}]", req.getRequestURI(), e);
        return ApiResponse.error(CommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 参数验证统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        List<String> errorMsgList = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMsgList.add(error.getDefaultMessage());
        }
        return ApiResponse.error(String.join("|", errorMsgList));
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("请求url:[{}],未知异常！原因是:[{}]", req.getRequestURI(), e);
        return ApiResponse.error(CommonEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
