package com.demo.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.beans.Product;
import com.demo.beans.User;

// @PropertySource 다수의 파일을 지정할 수 있다.
@Controller
@PropertySource("/WEB-INF/properties/data1.properties")
public class TestController {
	@GetMapping("/input_data")
	public String input_data() {

		return "input_data";
	}

	// @Valid 유효성 검사를 실시함
	// 유효성 검사결과를 사용하려면 BindingResult객체를 주입받아야함
	@PostMapping("/input_pro")
	public String input_pro(@Valid User user, BindingResult result) {

		System.out.printf("ID : %s\n", user.getId());
		System.out.printf("Pass : %s\n", user.getPass());

		System.out.printf("BindingResult : %s\n", result);

		if (result.hasErrors()) {
			// 유효성 검사에서 에러 발생시 입력데이터 jsp페이지로 다시 이동하라는 이때 에러메세지 포함
			return "input_data";
		}

		return "input_success";
	}
	@GetMapping("/input_product")
	public String input_product(Product product) {	//Product 빈 객체를 request 로 보냄	
		return "input_product";
	}

	@PostMapping("/input_product_proc")
	public String input_product(@Valid Product product, BindingResult result) { // Product 빈 객체를 request 로 보냄

		if (result.hasErrors()) {
			return "input_product";
		}

		System.out.println(product.toString());

		return "input_product_success";

	}

}
