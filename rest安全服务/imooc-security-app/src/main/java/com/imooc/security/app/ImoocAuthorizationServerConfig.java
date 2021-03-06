package com.imooc.security.app;

import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FangYan on 2017/10/30 0030.
 */
@Configuration
@EnableAuthorizationServer //实现了一个认证服务器
@Slf4j
public class ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UserDetailsService userDetailsService;

    @Autowired private SecurityProperties securityProperties;

    @Autowired private TokenStore tokenStore;

    @Autowired(required = false) private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false) private TokenEnhancer jwtTokenEnhancer;

    //配置入口点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //一定要配置authenticationManager，否则下面的clients配置无效。
            endpoints.authenticationManager(authenticationManager)
                     .userDetailsService(userDetailsService);
            if(jwtAccessTokenConverter!=null && jwtTokenEnhancer!=null){
                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
                List<TokenEnhancer> enhancers = new ArrayList<>();
                enhancers.add(jwtTokenEnhancer);
                enhancers.add(jwtAccessTokenConverter);
                tokenEnhancerChain.setTokenEnhancers(enhancers);
                endpoints
                        .tokenEnhancer(tokenEnhancerChain)
                        .accessTokenConverter(jwtAccessTokenConverter);
            }
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients存储位置
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOAuth2Properties().getClients())) {
            for (OAuth2ClientProperties config : securityProperties.getOAuth2Properties().getClients()) {
                 builder.withClient(config.getClientId()).secret(config.getClientSecret())
                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
                        .authorizedGrantTypes("refresh_token", "password").scopes("all", "read", "write");
                 log.info("客户端ID" + config.getClientId());
            }
        }
    }
}
