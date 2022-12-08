package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String test1_get() {
		return "test1";
	}

	@RequestMapping(value = "/test2", method = RequestMethod.POST)
	public String test2_post() {
		return "test2";
	}

	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public String test3_get() {
		return "test3_get";
	}

	@RequestMapping(value = "/test3", method = RequestMethod.POST)
	public String test3_post() {
		return "test3_post";
	}
	@RequestMapping(value = "/test7")
	public String test7() {
		return "test7";
	}

}
