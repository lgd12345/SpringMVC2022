package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.demo.beans.DataBean1;

@Controller
public class TestController {
	@GetMapping("/test1")
	// public String test1(HttpServletRequest request) {
	public String test1(HttpSession session) {
		// HttpSession session = request.getSession();
		session.setAttribute("data1", "문자열1");

		return "test1";
	}

	@GetMapping("/result1")
	// public String result1(HttpServletRequest request) {
	public String result1(HttpSession session) {
		// HttpSession session = request.getSession();
		String data1 = (String) session.getAttribute("data1");

		System.out.printf("data1 : %s\n", data1);
		return "result1";
	}

// 세션은 리다이렉트를 해도 남아있어서 상관 없음
	@GetMapping("/test2")
	public String test2(HttpSession session) {
		session.setAttribute("data1", "문자열2");
		return "redirect:/result1";
	}

	@GetMapping("/test3")
	public String test3(HttpSession session) {
		session.setAttribute("data1", "문자열3");
		return "forward:/result1";
	}

	@GetMapping("/test4")
	public String test4(HttpSession session) {
		DataBean1 bean1 = new DataBean1();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");

		session.setAttribute("bean1", bean1);
		return "result2";
	}

	@GetMapping("/result2")
	// 콤마찍고 리콰이어드 해서 널 값을 받아도 오류 안나게 설정 할 수 있다.
	// public String result4(HttpSession session) {
	public String result4(@SessionAttribute("bean1") DataBean1 bean1) {

		// DataBean1 bean1 = (DataBean1)session.getAttribute("bean1");

		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());

		return "result2";
	}
}
