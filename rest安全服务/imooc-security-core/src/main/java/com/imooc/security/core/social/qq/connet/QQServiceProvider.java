/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

/**
 *  处理前5部流程
 *  1，将用户引导至认证服务器
 *  2，用户同意授权
 *  3，返回Client并携带授权码
 *  4，申请令牌
 *  5，发放令牌
 *  6，获取用户信息
 *  7，根据用户信息构建Authentication,放入SecurityContext
 *
 *  这里不能声明成spring的组件，spring组件默认为单例，
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

	//申请令牌的地址
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";


	//重载父类的方法，参数类型和数量可以不一致，方法名必须一致
    //重载构造器
    QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	@Override   //继承抽象方法
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
