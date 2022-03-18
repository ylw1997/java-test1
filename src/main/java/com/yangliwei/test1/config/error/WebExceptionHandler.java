package com.yangliwei.test1.config.error;

import com.yangliwei.test1.common.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  异常处理类
 * @author yangliwei
 */
@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult exception(Exception e){
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResult customException(CustomException e){
        return AjaxResult.error(e.getCode(),e.getMessage());
    }

}
