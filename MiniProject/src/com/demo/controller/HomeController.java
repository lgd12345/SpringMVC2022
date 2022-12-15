package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 객체지향 프로그래밍(역할별 분리)의 서버 처리과정 3단계의 컨트롤러부분
// 클라이언트의 요청을 받는다, 요청에 대한 처리는 서비스에게 전담시킴, 클라이언트에게 응답해줌 
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		// System.out.println("home");
		// 실제 서버가 구동되는 위치(주소) 확인해서 파일 담을 위치 정하기 위함
		System.out.println("실제 서버 구동 위치 확인용" + request.getServletContext().getRealPath("/"));
		return "index";
	}

}
