package com.demo.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.demo.beans.BoardInfoBean;
import com.demo.beans.LoginUserBean;
import com.demo.service.MenuService;

// 상단메뉴 처리할 인터셉터
// 컨트롤러로 처리할 것이 아니라 모든 컨트롤러에 다 들어가는 부분이기 때문에 인터셉터로 처리할 것이다.
public class MenuInterceptor implements HandlerInterceptor {

	// 오토와이어드(필드주입)하면 되는데 이번엔 생성자로 주입할 것임.
	private MenuService menuService;

	private LoginUserBean loginUserBean;

	// (생성자 주입) 인터셉터가 생성될 때(주입됨)
	public MenuInterceptor(MenuService menuService, LoginUserBean loginUserBean) {
		this.menuService = menuService;
		this.loginUserBean = loginUserBean;
	}

	// 리턴 ture 일때 요청을 그대로 실행한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 테이블에서 게시판이름을 가져와 메뉴에 전달한다.
		List<BoardInfoBean> topMenuList = menuService.getMenuList();
		// request로 topMenuList에 저장함
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		return true;
	}
}
