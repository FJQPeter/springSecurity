package imook.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.implicit.InMemoryImplicitGrantService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by FangYan on 2017/10/29 0029.
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private SsoUserDetailsService ssoUserDetailsService;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("wenwen")
                .secret("love")
                .authorizedGrantTypes("authorization_code")
                .accessTokenValiditySeconds(60)
                .scopes("all")
                .and()
                .withClient("imooc2")
                .secret("imoocsecrect2")
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("all");
    }

    //AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
    //授权是使用 AuthorizationEndpoint 这个端点来进行控制的
    //如果你不进行设置的话，默认是除了资源所有者密码（password）授权类型以外，支持其余所有标准授权类型的（RFC6749）
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象。
        //implicitGrantService：这个属性用于设置隐式授权模式，用来管理隐式授权模式的状态。
//        endpoints.authenticationManager(authenticationManager).userDetailsService(ssoUserDetailsService);
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

    //AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()"); //springSecurity的授权表达式
    }

    /**
     * 我们都在微博上@过某某，对方会优先看到这条信息，并给你反馈，那么在Spring中，
     * 你标识一个@符号，那么Spring就会来看看，并且从这里拿到一个Bean或者给出一个Bean
     * @return
     *
     * @Bean 用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean
     */

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("imooc");
        return  converter;
    }
}
