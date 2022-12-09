package com.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.demo.beans.DataBean;
import com.demo.beans.DataBean1;
import com.demo.beans.DataBean2;

@Controller
public class TestController {

	@Autowired
	DataBean1 bean1;

	@Resource(name = "bean2")
	DataBean2 bean2;
	
	@GetMapping("/beanTest")
	public String beanTest() {

		bean1.setData1("문자열1");
		bean1.setData2("문자열2");

		bean2.setData1("문자열3");
		bean2.setData2("문자열4");

		return "forward:/beanResult";
	}

	@GetMapping("/beanResult")
	public String beanResult(Model model) {

		System.out.printf("requestBean1.data1 : %s\n", bean1.getData1());
		System.out.printf("requestBean1.data2 : %s\n", bean1.getData2());

		System.out.printf("requestBean2.data3 : %s\n", bean2.getData1());
		System.out.printf("requestBean2.data4 : %s\n", bean2.getData2());

		model.addAttribute("bean1", bean1);
		model.addAttribute("bean2", bean2);

		return "beanResult";
	}


// Request 브라우저에 의해 요청이 발생하고 서버가 브라우저에 요청하면 관련된 데이터들을 보관하기 위해
// HttpServletRequest 객체를 생성해 요청 정보들을 담아둔다.
// HttpServletRequest 객체는 응답결과가 브라우저로 전송될 때 까지 유지되고 사용가능

	// forward : 코드들이 서버에서만 이동함 브라우저는 이동된 걸 모르기 때문에 주소변경 안됨
	// HttpServletRequest, HttpSession모두 유지

	// Redirect : 서버가 클라이언트에게 요청할 주소를 응답결과로 전달하는 것
	// 클라이언트가 브라우저에서 응답결과로 받은 요청주소를 요청한다. 따라서 주소는 변한다.
	// 아예 새로운 요청이므로 HttpServletRequest 객체는 소멸뒤 새로 생성되며 HttpSession객체는 유지된다.
	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		request.setAttribute("data1", "문자열1");
		return "forward:/result1";
	}

	@GetMapping("/result1")
	public String result1(HttpServletRequest request) {

		String data1 = (String) request.getAttribute("data1");
		System.out.printf("data1 : %s\n", data1);

		return "result1";
	}

	@GetMapping("/test2")
	public String test2(Model model) {
		// 모델을 통해 저장한 데이터는 리퀘스트에 저장된다.
		model.addAttribute("data2", "문자열2");

		return "forward:/result2";
	}

	@GetMapping("/result2")
	public String result2(HttpServletRequest request) {

		String data2 = (String) request.getAttribute("data2");
		System.out.printf("data2 : %s\n", data2);

		return "result2";
	}

	@GetMapping("/test3")
	public ModelAndView test3(ModelAndView mv) {

		mv.addObject("data3", "문자열3");
		mv.setViewName("forward:/result3");

		return mv;
	}

	@GetMapping("/result3")
	public String result3(HttpServletRequest request) {
		String data3 = (String) request.getAttribute("data3");
		System.out.printf("data3 : %s\n", data3);

		return "result3";
	}

	@GetMapping("/test4")
	public String test4(Model model) {
		DataBean bean1 = new DataBean();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");

		model.addAttribute("bean1", bean1);

		return "forward:/result4";
	}

	@GetMapping("/result4")
	public String result4(HttpServletRequest request) {

		DataBean bean1 = (DataBean) request.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());

		return "result4";
	}

	@GetMapping("/test5")
	public String test5(@ModelAttribute("bean1") DataBean bean1) {
		bean1.setData1("문자열6");
		bean1.setData2("문자열7");

		return "forward:/result5";
	}

	@GetMapping("/result5")
	// public String result5(@ModelAttribute("bean1") DataBean1 bean1) {
	public String result5(HttpServletRequest request) {
		DataBean bean1 = (DataBean) request.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());

		return "result5";
	}


}
