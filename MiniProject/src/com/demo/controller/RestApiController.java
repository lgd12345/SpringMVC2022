package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.UserService;

// 뷰 없는 컨트롤러 
@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	// 유저아이디가 계속 바뀌니까 변수(@PathVariable)로 받아서 {user_id}에 넣는다. 
	@GetMapping("/user/check/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		//유저 아이디가 없으면 true 있으면 false
		boolean check = userService.checkuserIdExist(user_id);
		// 서버통신상 문자열만 가능하니깐 문자열로 변환해서 리턴하기 위해서 + : "";를 써서 보낸다.
		// 숫자에 +""적고 불린타입에 +""적으면 문자열로 바뀜 꼼수!!!!
		return check + ""; // true or false 리턴
	}

}
