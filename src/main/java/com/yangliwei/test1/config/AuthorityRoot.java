package com.yangliwei.test1.config;

import com.yangliwei.test1.service.impl.LoginUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  权限自定义验证
 * @author yangliwei
 */
@Component("ex")
public class AuthorityRoot {
    public boolean hasAuthority(String authority){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        LoginUser loginUser = (LoginUser) securityContext.getAuthentication().getPrincipal();
        List<String> authorities = loginUser.getPermissions();
        return authorities.contains(authority);
    }
}
