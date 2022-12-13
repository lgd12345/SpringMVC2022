package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1(Model model) {

		int[] array1 = { 10, 20, 30 };
		model.addAttribute("array1", array1[10]); //오류발생 [10]인덱스 데이터
		return "test1";
	}

	// Controller에서 @ExceptonHandler를 통해 메서드를 정의해 주면 오류발생시 이 메소드가 자동호출됨
	// jsp를 오류페이지용도로 따로 만들어주면된다.
	// 글로벌 예외처리를 했더라도 이게 우선 순위에 있음 이게 보여짐
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public String exception1(Model model) {
		
		model.addAttribute("msg", "test1 접속을 잠시 후 다시 시도해주세요");
		
		return "error/error";
	}
}
