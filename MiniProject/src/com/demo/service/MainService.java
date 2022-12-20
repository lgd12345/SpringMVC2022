package com.demo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.beans.ContentBean;
import com.demo.mapper.BoardMapper;

@Service
public class MainService {
	
	@Autowired
	private BoardMapper boardMapper;

	// 이미 만들어져있는 보드매퍼로 테이블을 가져옴
	// 최신글로 5개를 가져오는 메서드 만들기
	public List<ContentBean> getMainList(int board_info_idx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		return boardMapper.getContentList(board_info_idx, rowBounds);
	}
}
