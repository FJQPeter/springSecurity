package com.imooc.security.core;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;

/**
 *   WebSecurity
    全局请求忽略规则配置（比如说静态文件，比如说注册页面）、全局HttpFirewall配置、是否debug配置、
    全局SecurityFilterChain配置、privilegeEvaluator、expressionHandler、securityInterceptor、
 * Created by FangYan on 2017/12/29 0029.
 *
 *       HttpSecurity
        具体的权限控制规则配置。一个这个配置相当于xml配置中的一个标签。
        各种具体的认证机制的相关配置，

        WebSecurityConfigurerAdapter
        spring security为web应用提供了一个WebSecurityConfigurerAdapter适配器，
        应用里spring security相关的配置可以通过继承这个类来编写；具体是提供了上边三个顶级配置项构建器的构建重载回调方法；

        WebMvcConfigurer
 *
 */
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        1httpSecurity.authorizeRequests()返回一个ExpressionInterceptUrlRegistry对象，
         这个对象就一个作用，注册intercept url规则权限匹配信息，
         通过设置URL Matcher，antMatchers，
         mvcMatchers，regexMatchers或者直接设置一个一个或者多个RequestMatcher对象;*/


     /*   2上边设置matchers的方法会返回一个AuthorizedUrl对象，
         用于接着设置符合其规则的URL的权限信息，
         AuthorizedUrl对象提供了access方法用于设置一个权限表达式，
         比如说字符串“hasRole(‘ADMIN’) and hasRole(‘DBA’)”，
         同时提供了多个方便的语义方法，比如说：
        public ExpressionInterceptUrlRegistry hasRole(String role)
        public ExpressionInterceptUrlRegistry hasAuthority(String authority)
        这些方法返回值是ExpressionInterceptUrlRegistry，用于接着设置下一条过滤规则：*/

    /*    protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/signup", "/about").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()
                    .and()
                    // ...
                    .formLogin();
        }*/

 /*       上边1和2结合起来的功能相当于标签的功能；
        UrlAuthorizationConfigurer能实现上边类似的功能；
        protected void configure(HttpSecurity http) throws Exception {
            http.apply(new UrlAuthorizationConfigurer<HttpSecurity>()).getRegistry()
                    .antMatchers("/users**", "/sessions/**").hasRole("USER")
                    .antMatchers("/signup").hasRole("ANONYMOUS").anyRequest().hasRole("USER");
        }*/
//            http.authorizeRequests()
    }
}
