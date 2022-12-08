package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sub1")
public class Sub1Controller {

	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public String sub1Test3() {
		return "sub1/test3";
	}

	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public String sub1Test4() {
		return "sub1/test4";
	}
}
