/**
 * 
 */
package com.imooc.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author zhailiang
 *
 */
public class QQProperties extends SocialProperties {
	
	@Setter@Getter private String providerId = "qq";
}
