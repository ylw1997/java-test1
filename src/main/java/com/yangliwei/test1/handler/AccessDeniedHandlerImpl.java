package com.yangliwei.test1.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.common.WebUtil;
import com.yangliwei.test1.config.error.CustomException;
import com.yangliwei.test1.config.error.CustomExceptionType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  自定义AccessDeniedHandler 实现类
 * @author yangliwei
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 处理异常
        AjaxResult error = AjaxResult.error(new CustomException(CustomExceptionType.USER_NOT_PERMISSION));
        ObjectMapper mapper = new ObjectMapper();
        String errorString = mapper.writeValueAsString(error);
        WebUtil.renderString(response, errorString);
    }
}
