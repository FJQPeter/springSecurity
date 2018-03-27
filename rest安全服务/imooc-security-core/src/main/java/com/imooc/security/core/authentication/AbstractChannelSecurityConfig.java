/**
 * 
 */
package com.imooc.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.core.properties.SecurityConstants;

/**
 *  上面的默认配置的作用：
 * 要求访问应用的所有用户都要被验证
 * 允许所有用户可以通过表单进行验证
 * 允许所有请求通过Http Basic 验证(译者注：关于什么是Http Basic验证，读者可以查看维基百科。)
 *
 *  Java配置中的and()方法类似于xml配置中的结束标签，
 *  and()方法返回的对象还是HttpSecurity，方便我们继续对HttpSecurity进行配置。
 *
 *   总的来说：HttpSecurity是SecurityBuilder接口的一个实现类，
 *   从名字上我们就可以看出这是一个HTTP安全相关的构建器。
 *   当然我们在构建的时候可能需要一些配置，
 *   当我们调用HttpSecurity对象的方法时，实际上就是在进行配置。
 *
 *   因此，从总的流程上来说，当我们在进行配置的时候，
 *   需要一个安全构建器SecurityBuilder(例如我们这里的HttpSecurity)，
 *   SecurityBuilder实例的创建需要有若干安全配置器SecurityConfigurer实例的配合。
 *
 *   基本上每个SecurityConfigurer子类都对应一个或多个过滤器。
 *   我们分别查看ExpressionUrlAuthorizationConfigurer、
 *   FormLoginConfigurer、HttpBasicConfigurer三个类的JavaDoc：
 *
 *   而HttpSecuirty内部维护了一个Filter的List集合，
 *   我们添加的各种安全配置器对应的Filter最终都会被加入到这个List集合中。
 *
 * @author zhailiang
 *
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenticationFailureHandler);
	}
	
}
