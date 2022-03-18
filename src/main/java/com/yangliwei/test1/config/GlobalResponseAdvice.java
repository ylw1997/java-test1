package com.yangliwei.test1.config;

import com.yangliwei.test1.common.AjaxResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * @author ylw12
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 在返回响应之前，对响应进行处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)){
            if(body instanceof AjaxResult){
                AjaxResult ajaxResponse = (AjaxResult) body;
                if(ajaxResponse.getCode() != HttpStatus.OK.value()){
                    response.setStatusCode(HttpStatus.valueOf(ajaxResponse.getCode()));
                }
            }else{
                return AjaxResult.success(body);
            }
        }
        return body;
    }
}
