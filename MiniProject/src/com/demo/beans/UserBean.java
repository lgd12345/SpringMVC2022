package com.demo.beans;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {

	// 테이블의 열이름과 클래스 변수명이 똑같게 만든다.
	// 사용자 정보테이블 (사용자: 번호(pk,uq),이름,아이디(uq),비밀번호)
	private int user_idx;

	@Size(min = 2, max = 4, message = "사용자 이름은 2-4자 이어야 합니다.")
	@Pattern(regexp = "[가-힣]*", message = "사용자 이름은 한글로 적어주세요.")
	private String user_name;

	@Size(min = 4, max = 10, message = "아이디는 4-10자 이어야 합니다.")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "아이디는 영문과 숫자만 가능합니다.")
	private String user_id;

	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 이어야 합니다.")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "비밀번호는 영문과 숫자만 가능합니다.")
	private String user_pw;

	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 이어야 합니다.")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "비밀번호는 영문과 숫자만 가능합니다.")
	private String user_pw2; // 비밀번호 확인
	
	//아이디 중복 체크 
	@AssertTrue(message = "아이디 중복체크 해주세요") // id가 중복이면 false 아니면 true
	private boolean userIdChecked;	
	
	public UserBean() {
		this.userIdChecked = false; //처음 값은 id 체크 안됨 false
	}
		
	public boolean isUserIdChecked() {
		return userIdChecked;
	}
	public void setUserIdChecked(boolean userIdChecked) {
		this.userIdChecked = userIdChecked;
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

	public String getUser_pw2() {
		return user_pw2;
	}

	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}

}
