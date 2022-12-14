package com.demo.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.beans.LoginUserBean;
import com.demo.beans.UserBean;
import com.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// 빈에 등록된 세션정보를 주입하기 위해서..
	@Resource(name = "loginUserBean")
	private LoginUserBean loginUserBean;

	// user 아이디 중복 체크
	public boolean checkuserIdExist(String user_id) {
		String user_name = userMapper.checkUserIdExist(user_id);

		if (user_name == null) {
			return true; // 가입 가능
		} else {
			return false; // 가입 불가능
		}
	}

	public void addUserInfo(UserBean joinUserBean) {
		userMapper.addUserInfo(joinUserBean);
	}

	// user login
	// 로그인이 되었을 때 유저객체에 정보를 저장한다.
	public void getLoginUserInfo(LoginUserBean loginBean) {

		LoginUserBean tempLoginBean = userMapper.getLoginUserInfo(loginBean);
		// 로그인 유저객체에 현재 로그인된 유저의 정보를 입력한다. 로그인 상태는 true로 입력
		if (tempLoginBean != null) {
			loginUserBean.setUser_idx(tempLoginBean.getUser_idx());
			loginUserBean.setUser_name(tempLoginBean.getUser_name());
			loginUserBean.setUserLogin(true); // 로그인 상태 true
		} else {
			loginUserBean.setUserLogin(false); // 로그인 상태 false
		}
	}

	// 유저 정보 수정 들어가기
	// 현재 로그인중인 유저의 인덱스번호로 아이디와 이름을 얻어서 modifyUserBean 객체에 저장
	public void getModifyUserInfo(UserBean modifyUserBean) {

		UserBean temp = userMapper.getModifyUserInfo(loginUserBean.getUser_idx());

		modifyUserBean.setUser_id(temp.getUser_id());
		modifyUserBean.setUser_name(temp.getUser_name());
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
	}

	// 유저 정보 수정하기
	public void modifyUserInfo(UserBean modifyUserBean) {

		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());

		userMapper.modifyUserInfo(modifyUserBean);
	}

}
