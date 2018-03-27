/**
 * 
 */
package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 有时候有这样子的情景，我们想把配置文件的信息，读取并自动封装成实体类，
 * 这样子，我们在代码里面使用就轻松方便多了，这时候，
 * 我们就可以使用@ConfigurationProperties，它可以把同类的配置信息自动封装成实体类
 *
 *  我们还可以把@ConfigurationProperties还可以直接定义在@bean的注解上，
 *  这是bean实体类就不用@Component和@ConfigurationProperties了
 *
 *
 *
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    @Setter@Getter private BrowserProperties browser = new BrowserProperties();

    @Setter@Getter private ValidateCodeProperties code = new ValidateCodeProperties();

    @Setter@Getter private SocialProperties social = new SocialProperties();

    @Setter@Getter private OAuth2Properties oAuth2Properties=new OAuth2Properties();


}

