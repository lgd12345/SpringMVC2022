package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.beans.UserBean;
import com.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

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
	

}
