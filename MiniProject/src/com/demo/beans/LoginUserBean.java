package com.demo.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// 객체를 loginUserBean라는 이름으로 세션에 등록한다.
@Component("loginUserBean")
@SessionScope
public class LoginUserBean {

	// 유효성 검사할 필요 없는 것
	private int user_idx;
	private String user_name;

	// 유효성 검사 해야하는 것
	@Size(min = 4, max = 10, message = "비밀번호는 4-20자 이어야 합니다.")
	@Pattern(regexp = "[a-zA-z0-9]*", message = "비밀번호는 영문과 숫자만 가능합니다.")
	private String user_id;

	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 이어야 합니다.")
	@Pattern(regexp = "[a-zA-z0-9]*", message = "비밀번호는 영문과 숫자만 가능합니다.")
	private String user_pw;

	private boolean userLogin; // 로그인 상태

	public LoginUserBean() {
		this.userLogin = false; // 처음 상태는 로그인 false
	}

	// 겟 셋
	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

}
