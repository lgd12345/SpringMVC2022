package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptor.TestInterceptor1;

//Spring MVC 관련된 설정을 하는 클래스
@Configuration
//Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
//스캔할 패키지를 지정한다.
// exceptions를 써서 예외처리컨트롤러도 스캔한다.
@ComponentScan("com.demo.controller")
@ComponentScan("com.demo.exceptions")
public class ServletAppContext implements WebMvcConfigurer {

	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	// Properties 파일을 Message로 등록한다.
	// 빈으로 등록했다.
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// res.setBasename("/WEB-INF/properties/data1");
		messageSource.setBasenames("/WEB-INF/properties/data1", "/WEB-INF/properties/data2");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	// 인터셉터를 등록한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		WebMvcConfigurer.super.addInterceptors(registry);

		InterceptorRegistration reg1 = registry.addInterceptor(new TestInterceptor1());
		// 인터셉터가 가로채갈 주소 addPathPatterns
		// /* 하위 /** 하위하위 /? 하위
		reg1.addPathPatterns("/test1");
		reg1.addPathPatterns("/?");
		reg1.addPathPatterns("/**");
		// 인터셉터가 가로채지 않을 주소 excludePathPatterns
		reg1.excludePathPatterns("/");
	}
}
