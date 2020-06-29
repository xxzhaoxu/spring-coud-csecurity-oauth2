package com.demo.config;

import com.alibaba.fastjson.JSON;
import com.demo.handler.CustomAccessDeniedHandler;
import com.demo.permit.PermitAllSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableResourceServer   //注解来开启资源服务器
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler appLoginInSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler appLoginFailureHandler;

    @Autowired
    private PermitAllSecurityConfig permitAllSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.formLogin()
                .successHandler(appLoginInSuccessHandler)//登录成功处理器
                .failureHandler(appLoginFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .apply(permitAllSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/forbidden").hasRole("ADMIN")
                .antMatchers("/permitAll").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        // @formatter:ON
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

}
