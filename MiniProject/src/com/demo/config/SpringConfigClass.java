package com.demo.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//public class SpringConfigClass implements WebApplicationInitializer {
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		// Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다.
//		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//		servletAppContext.register(ServletAppContext.class);
//
//		// 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정해준다.
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
//		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
//		
//		// 부가 설정
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/");
//		
//		// Bean을 정의하는 클래스를 지정한다
//		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
//		rootAppContext.register(RootAppContext.class);
//		
//		// 리스너 설정
//		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
//		servletContext.addListener(listener);
//		
//		// 파라미터 인코딩 설정
//		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//		filter.setInitParameter("encoding", "UTF-8");
//		filter.addMappingForServletNames(null, false, "dispatcher");		
//	}
//
//}

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {

	// DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletAppContext.class };
	}

	// 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootAppContext.class };
	}

	// 파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

	// 50메가 바이트 = 52428800바이트
	@Override
	protected void customizeRegistration(Dynamic registration) {

		super.customizeRegistration(registration);
		// (location :파일 업로드시 임시저장 절대 경로: 파일 경로가 null이면 톰캣이 지정한 경로로 들어감, 
		// maxFileSize :파일당 최대 파일 크기:d:제한없음 이미지나 파일의 용량을 하나는 5메가바이트,
		// maxRequestSize : 파일한개 용량이 아니라 multipart/form-data요청당 최대파일 크기 
		// 여러 파일 업로드 총 크기 : 한번에 업로드 가능 50메가,
		// fileSizeThreshold : 업로드하는 파일이 임시로 저장되지 않고 메모리에서 스트림으로 바로 전달되는 크기의 한계 : 디폴트0)
		MultipartConfigElement config1 = new MultipartConfigElement(null, 5242880, 52428800, 0);
		registration.setMultipartConfig(config1);
	}
}
