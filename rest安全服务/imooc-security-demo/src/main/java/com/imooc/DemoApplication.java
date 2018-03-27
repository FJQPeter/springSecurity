/**
 * 
 */
package com.imooc;

import com.imooc.dto.User;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhailiang
 *  SpringBoot内嵌的Servlet容器
    内嵌容器，使得我们可以执行运行项目的主程序main函数，是想项目的快速运行；
 */
@SpringBootApplication  //告诉程序这是一个springboot项目
@RestController //@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@EnableSwagger2
public class DemoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/hello")
	public Object hello() {
        QQUserInfo qqUserInfo = new QQUserInfo();
        qqUserInfo.setOpenId("fef");
        qqUserInfo.setCity("dws");
        return qqUserInfo;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8083")
                        .allowCredentials(true)
                        .allowedMethods("GET")
                        .allowedHeaders("mobile")
                        .maxAge(1024);
            }

        };
    }

}
