package com.yangliwei.test1.config.error;

import com.yangliwei.test1.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 *  异常处理类
 * @author yangliwei
 */
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult exception(Exception e){
        log.error("系统异常：",e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResult customException(CustomException e){
        log.error("自定义异常：${}",e.getMessage());
        return AjaxResult.error(e.getCode(),e.getMessage());
    }

    /**
     * 数据验证异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResult bindException(BindException e){
        log.error("数据验证异常：",e);
        return AjaxResult.error(new CustomException(CustomExceptionType.PARAM_ERROR, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    /**
     * 数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult handleBindException(MethodArgumentNotValidException e){
        log.error("数据验证异常：${}",e.getBindingResult().getFieldError());
        return AjaxResult.error(new CustomException(CustomExceptionType.PARAM_ERROR, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public  AjaxResult runtimeException(RuntimeException e){
        log.error("运行时异常：",e);
        return AjaxResult.error(e.getMessage());
    }


}
