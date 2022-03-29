package com.yangliwei.test1.config;

import com.yangliwei.test1.filter.JwtAuthenticationTokenFilter;
import com.yangliwei.test1.handler.AccessDeniedHandlerImpl;
import com.yangliwei.test1.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  spring security配置类
 * @author yangliwei
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 创建BCryptPasswordEncoder注入容器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建AuthenticationManager注入容器 实现登录接口的自定义认证
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *  接口放行 Security配置
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //禁用csrf
                .csrf().disable()
                //不用过session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //放行登录接口
                .authorizeRequests()
                // 放行登录接口,允许匿名访问
                .antMatchers("/api/user/login") .anonymous()
                // 除了登录注册接口，其他接口都需要登录认证
                .anyRequest().authenticated()
                .and()
                // 添加JWT 过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加自定义认证异常处理器
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());
        // 添加自定义授权异常处理器
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());
    }
}
