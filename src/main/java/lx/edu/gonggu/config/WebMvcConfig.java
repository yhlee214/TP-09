package lx.edu.gonggu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lx.edu.gonggu.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
		.addPathPatterns("/**") // 모든 url에 적용을 하는데...
		.excludePathPatterns("/login", "/loginform", "/signupform", "/dupCheck", "/error" , "/signup"
				,"/css/**", "/js/**", "/images/**"); // 로그인 없이도 접근 가능한 url
		
	}
	
}
