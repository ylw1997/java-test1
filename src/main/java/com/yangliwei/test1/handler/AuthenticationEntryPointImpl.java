package com.yangliwei.test1.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.common.WebUtil;
import com.yangliwei.test1.config.error.CustomException;
import com.yangliwei.test1.config.error.CustomExceptionType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  用户认证过程中，认证失败时，返回的错误信息
 * @author yangliwei
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 处理异常
        AjaxResult error = AjaxResult.error(new CustomException(CustomExceptionType.USER_UNAUTHORIZED));
        ObjectMapper mapper = new ObjectMapper();
        String errorString = mapper.writeValueAsString(error);
        WebUtil.renderString(response, errorString);
    }
}
