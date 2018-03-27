
package com.imooc.security.app;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.app.jwt.imoocJwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created by FangYan on 2017/10/30 0030.
 */


@Configuration
public class TokenStoreConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    //配置项中有redis，下面配置生效，没有下面不生效
    @Bean
    @ConditionalOnProperty(prefix = "imooc.security.oauth2",name = "storeType",havingValue = "redis" )
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    //配置项中有jwt，下面代码生效，没有也生效
    @Configuration
    @ConditionalOnProperty(prefix = "imooc.security.oauth2",name = "storeType",havingValue = "jwt",matchIfMissing = true)
    public static class JwtTokenConfig{

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean //生成token时的相关处理
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //把秘钥转成UTF-8的格式，spring提供的
            accessTokenConverter.setSigningKey(securityProperties.getOAuth2Properties().getJwtSigningKey());
            return accessTokenConverter;
        }

        @Bean //token内容补充
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
                return  new imoocJwtTokenEnhancer();
        }
    }
}

