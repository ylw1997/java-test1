package com.yangliwei.test1.filter;

import com.yangliwei.test1.common.JwtUtil;
import com.yangliwei.test1.common.RedisCache;
import com.yangliwei.test1.constant.Constant;
import com.yangliwei.test1.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 *  token验证过滤器
 * @author yangliwei
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String authorization = request.getHeader("Authorization");
        // 判断token是否为空
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            // 如果token为空，直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJwt(authorization.substring(7));
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token解析失败");
        }
        // 从redis 获取用户信息
        User loginUser = redisCache.getCacheObject(Constant.REDIS_LOGIN_KEY + userId);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户登录异常");
        }
        System.out.println("用户验证通过：" + loginUser);
        // 存入SecurityContextHolder中
        // TODO 获取权限信息放在authentication中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放行
        filterChain.doFilter(request, response);
    }
}
