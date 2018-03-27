/**
 * 
 */
package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhailiang
 *
 */
public class BrowserProperties {
	
	@Setter@Getter private SessionProperties session = new SessionProperties();

    @Setter@Getter private String signUpUrl = "/imooc-signUp.html";

    @Setter@Getter private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    @Setter@Getter private LoginResponseType loginType = LoginResponseType.JSON;

    @Setter@Getter private int rememberMeSeconds = 3600;

    @Setter@Getter private String signOutUrl = "/signOut";

}
