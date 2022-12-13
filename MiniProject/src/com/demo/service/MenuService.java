package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.beans.BoardInfoBean;
import com.demo.mapper.MenuMapper;

// 객체지향 프로그래밍(역할별 분리)의 서버 처리과정 3단계의 서비스부분
// 사용자의 요구사항 처리(비즈니스로직), DB정보가 필요시 Repository에게 전담
@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public List<BoardInfoBean> getMenuList() {
		List<BoardInfoBean> menuList = menuMapper.getMenuList();
		return menuList;
	}

}
