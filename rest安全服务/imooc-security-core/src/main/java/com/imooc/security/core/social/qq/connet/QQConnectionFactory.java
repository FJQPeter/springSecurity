/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.social.qq.api.QQ;

/**
 * 需要适配器QQAdapter 和 QQServiceProvider
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    //通过connectionFactory拿到用户信息
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
