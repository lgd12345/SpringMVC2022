package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.beans.DataBean;

@Controller
public class TestController {

	// map 으로 주입 받기
	// 클라이언트가 전달하는 모든 파라미터 데이터를 한번에 Map으로 받을 수 있습니다.
	// 동일 명으로 전달되는 2 개 이상의 파라미터는 하나만 남기게 됨
	// 동일명으로 전달되는 파라미터가 2 개이상이면 List로 주입받아야한다.
	// 주소창에는 문자열만 들어간다. 다른 것 변환해서 사용가능
	@GetMapping("/test1")
	public String test1(@RequestParam Map<String, String> map, @RequestParam List<String> data3) {
		String data1 = map.get("data1");
		String data2 = map.get("data2");

		System.out.printf("data1 : %s\n", data1);
		System.out.printf("data2 : %s\n", data2);
		for (String str : data3) {
			System.out.printf("str_data3 : %s\n", str);
		}

		return "result";
	}

	@GetMapping("/test2")
	// public String test2(@ModelAttribute DataBean bean1, @ModelAttribute DataBean2
	// bean2) {
	// ModelAttribute는 생략가능하다.
	public String test2(DataBean bean1) {

		System.out.printf("data1 : %d\n", bean1.getData1());
		System.out.printf("data2 : %d\n", bean1.getData2());

		for (int number1 : bean1.getData3()) {
			System.out.printf("data3 : %d\n", number1);
		}

		return "result";
	}

}
