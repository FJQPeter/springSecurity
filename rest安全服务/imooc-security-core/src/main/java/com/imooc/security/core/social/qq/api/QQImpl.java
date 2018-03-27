/**
 * 
 */
package com.imooc.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**. 以get_user_info接口为例：
 * https://graph.qq.com/user/get_user_info?
 * access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
 *
 * @author zhailiang
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	private String appId;
	private String openId;
	private ObjectMapper objectMapper = new ObjectMapper();

	public QQImpl(String accessToken, String appId) {
	    //将access_token放到路径中
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		//格式说明由“％”和格式字符组成 它的作用就是将输出的数据转换成指定的格式输出。
        // 格式说明总是由“％”字符开始的。“s”格式符是用来输出一个字符串的。
		//将  %s替换为 accessToken
		String url = String.format(URL_GET_OPENID, accessToken);
		//发送get请求
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	/* (non-Javadoc)
	 * @see com.imooc.security.core.social.qq.api.QQ#getUserInfo()
	 */
	@Override
	public QQUserInfo getUserInfo() {
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		QQUserInfo userInfo;
		try {
		    //把String变成对象
			userInfo = objectMapper.readValue(result, QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}
}
