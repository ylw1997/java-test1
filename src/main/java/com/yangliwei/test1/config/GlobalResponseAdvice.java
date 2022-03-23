package com.yangliwei.test1.config;

import com.yangliwei.test1.common.AjaxResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 *  全局响应处理
 * @author ylw12
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {
//    /**
//     * 在返回响应之前，对响应进行处理
//     */
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        if(mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)){
//            if(body instanceof AjaxResult){
//                AjaxResult ajaxResponse = (AjaxResult) body;
//                if(ajaxResponse.getCode() != HttpStatus.OK.value()){
//                    response.setStatusCode(HttpStatus.valueOf(ajaxResponse.getCode()));
//                }
//            }else{
//                String s = "";
//                try {
//                     s = objectMapper.writeValueAsString(body);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                return s;
//
//            }
//        }
//        return body;
//    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return super.supports(returnType, converterType) && returnType.getParameterType().isAssignableFrom(AjaxResult.class);
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        Object value = bodyContainer.getValue();
        if (value instanceof AjaxResult) {
            AjaxResult ajaxResult = (AjaxResult) value;
            response.setStatusCode(HttpStatus.valueOf(ajaxResult.getCode()));
        }
    }
}
