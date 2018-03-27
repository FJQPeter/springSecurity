package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by FangYan on 2017/10/30 0030.
 */
public class OAuth2ClientProperties {
    @Setter@Getter private String clientId;
    @Setter@Getter private String clientSecret;
    @Setter@Getter private int accessTokenValiditySeconds;

}
