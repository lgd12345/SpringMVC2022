package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

	// 지금처럼 다른 컨트롤러에서 같은 에러 발생시 적용하지 못함 !! 그래서 
	//Global Exception Handler로 모든 예외 처리를 해줄 것이다.
	@GetMapping("/test2")
	public String test2(Model model) {

		int[] array1 = { 10, 20, 30 };
		model.addAttribute("array1", array1[10]);

//		ArrayList<String> list = null;
//		list.add("문자열");

		return "test2";
	}

}
