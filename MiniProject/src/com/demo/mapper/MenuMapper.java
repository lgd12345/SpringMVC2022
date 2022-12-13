package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.demo.beans.BoardInfoBean;

// 상단 메뉴중 게시판 메뉴를 구성한다. 인터셉터로 처리할 것 임

// 객체지향 프로그래밍(역할별 분리)의 서버 처리과정 3단계의 리파지토리부분
// DB관리(연결, 해제, 자원 관리), DB CRUD 작업처리
// Repository (MyBatis의 mapper)
public interface MenuMapper {
	
	@Select("select board_info_idx, board_info_name " +
			"from board_info_table " +
			"order by board_info_idx")
	List<BoardInfoBean> getMenuList();
	
}
