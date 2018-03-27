package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by FangYan on 2017/10/30 0030.
 */
public class OAuth2Properties {

    @Setter@Getter private String jwtSigningKey = "imooc";  //秘钥 签发令牌

    @Setter@Getter private OAuth2ClientProperties clients[]=null;

}
