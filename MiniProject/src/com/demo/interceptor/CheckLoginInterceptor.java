package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.beans.LoginUserBean;

public class CheckLoginInterceptor implements HandlerInterceptor {

	private LoginUserBean loginUserBean;

	// 생성자 주입
	public CheckLoginInterceptor(LoginUserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 요청 취소됨
		if (loginUserBean.isUserLogin() == false) {
			String contexPath = request.getContextPath();
			response.sendRedirect(contexPath + "/user/not_login");
			return false;
		}

		return true; // 요청한 페이지 이동
	}

}
