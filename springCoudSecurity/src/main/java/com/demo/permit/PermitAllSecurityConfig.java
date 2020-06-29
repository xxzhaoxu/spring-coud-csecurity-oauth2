package com.demo.permit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;


@Component("permitAllSecurityConfig")
public class PermitAllSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    @Qualifier("permitAuthenticationFilter")
    private Filter permitAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(permitAuthenticationFilter, OAuth2AuthenticationProcessingFilter.class);
    }
}
