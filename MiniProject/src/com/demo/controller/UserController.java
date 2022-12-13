package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.beans.LoginUserBean;
import com.demo.beans.UserBean;
import com.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

//  @ModelAttribute("") = 이름이 login.jsp의 loginBean 이름을 같게한다.
	@GetMapping("/login")
	public String login(@ModelAttribute("loginBean") LoginUserBean loginBean) {
		return "user/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("loginBean") LoginUserBean loginBean, BindingResult result) {
		if (result.hasErrors()) {
			return "user/login";
		}
		return "user/login_success";
	}

//  @ModelAttribute("") = 이름이 join.jsp의 modelAttribute 이름을 같게한다.
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result,
			Model model) {
		// 유효성 합격 뒤
		if (result.hasErrors()) {
			return "user/join";
		} // 비밀번호 일치 여부 확인
		if (!joinUserBean.getUser_pw().equals(joinUserBean.getUser_pw2())) {
			model.addAttribute("msg", "비밀번호가 같지 않습니다!");
			return "user/join";
		}
		// Service로
		userService.addUserInfo(joinUserBean);
		return "user/join_success";
	}

	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}

	@GetMapping("/logout")
	public String logout() {
		return "user/logout";
	}

}
