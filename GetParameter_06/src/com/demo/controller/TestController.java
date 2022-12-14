package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {

		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		// 같은 이름의 파라메터들은 배열로 받는다.
		String[] data3 = request.getParameterValues("data3");

		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);

		for (String str1 : data3) {
			System.out.println("data3 : " + str1);
		}

		return "result";
	}

	@PostMapping("/test2")
	public String test2(HttpServletRequest request) {

		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");

		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);

		if (data3 != null) {
			for (String str1 : data3) {
				System.out.println("data3 : " + str1);
			}
		}

		return "result";
	}

	@GetMapping("/test3/{data1}/{data2}/{data3}")
	public String test3(@PathVariable int data1, @PathVariable int data2, @PathVariable int data3) {
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		System.out.println("data3 : " + data3);

		int add = data1 + data2 + data3;
		System.out.println("add : " + add);

		return "result";
	}
// (required = false) 값이 안들어오면 널값
// 이름이 있을 경우에는 이름 값을 넣어주고 이름이 없을 경우에는 변수이름을 (data1을 넣어줘야한다.)
	@GetMapping("/test4")
	public String test4(@RequestParam(required = false) String data11, @RequestParam int data2, @RequestParam int[] data3) {
		System.out.println("data1 : " + data11);
		System.out.println("data2 : " + data2);
		for (int a1 : data3) {
			System.out.println("data3 : " + a1);
		}

		return "result";
	}

}
