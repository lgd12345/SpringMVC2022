package com.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.demo.beans.UserBean;


public interface UserMapper {
	// 유저 아이디로 검색해서 같은 게 있으면 그 유저 이름이 나오게 되어 있다.
	@Select("select user_name " + "from user_table " + "where user_id = #{user_id}")
	String checkUserIdExist(String user_id);
	
	// 유저 저장하기
	@Insert("insert into user_table (user_idx, user_name, user_id, user_pw) " +
			"values (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserBean joinUserBean);
	
}
