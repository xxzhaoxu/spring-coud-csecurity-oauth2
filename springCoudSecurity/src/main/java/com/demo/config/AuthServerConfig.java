package com.demo.config;

import com.demo.service.MyClientDetailsService;
import com.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 35086
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MyClientDetailsService myClientDetailsService;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Bean
    public RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Bean
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("clientId")
//                .resourceIds()
//                .secret(passwordEncoder().encode("secret"))
//                .authorizedGrantTypes("authorization_code","password","refresh_token")
//                .scopes("all")
//                .autoApprove(true)/**自动批准获取权限*/
//                .accessTokenValiditySeconds(3600)//设置access_token的有效时间(秒),默认(12小时)
//                .refreshTokenValiditySeconds(7200)//设置refresh_token有效期(秒)，默认(30天)
//                .redirectUris("http://localhost:8015/");
        clients.withClientDetails(myClientDetailsService);
    }

    /**
     * 配置token管理, token管理有4种方式
     * 1.InMemoryTokenStore 默认 内存管理
     * 2.JwtTokenStore 把令牌相关的数据进行编码 不存储
     * 3.JdbcTokenStore 基于JDBC的实现
     * 4.RedisTokenStore 基于Redis的实现
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

            endpoints
//                .tokenStore(new InMemoryTokenStore())
//                .tokenStore(redisTokenStore())
                    .tokenStore(jdbcTokenStore())
                    /**
                     * 配置令牌生成
                     */
                    .accessTokenConverter(accessTokenConverter())
                    /**
                     * 通过注入密码授权被打开AuthenticationManager
                     */
                    .authenticationManager(authenticationManager)
                    /**
                     * 该字段设置设置refresh token是否重复使用,true:reuse;false:no reuse.
                     */
                    .reuseRefreshTokens(false)
                    /**
                     * 刷新令牌授权将包含对用户详细信息的检查，以确保该帐户仍然活动,因此需要配置userDetailsService
                     */
                    .userDetailsService(userLoginService)
                    /**
                     * 认证异常处理器
                     */
                    .exceptionTranslator(webResponseExceptionTranslator)
            ;
        /**
         * 扩展token返回结果
         */
        if (accessTokenConverter() != null && jwtTokenEnhancer() != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer());
            enhancerList.add(accessTokenConverter());
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(accessTokenConverter());
        }

    }

    /** 配置jwt转换器 */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
        return new JwtTokenEnhancer();
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        /**
         * 允许所有人请求令牌
         */
        security.tokenKeyAccess("permitAll()")
                /**
                 * 已验证的客户端才能请求check_token端点
                 */
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
