package com.imooc.security;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by FangYan on 2017/10/29 0029.
 */
@Component
@Order(Integer.MAX_VALUE)  //因为他是最大值，在集合最后，会最后读取
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/oauth/token")
                .hasRole("ADMIN");
//        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");

    }
}
