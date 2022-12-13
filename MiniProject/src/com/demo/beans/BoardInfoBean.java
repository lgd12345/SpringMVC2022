package com.demo.beans;

public class BoardInfoBean {

	// 테이블의 열이름과 클래스 변수명이 똑같게 만든다.
	// 게시판 정보 테이블 (게시판: 번호(pk,uq), 이름)
	private int board_info_idx;
	private String board_info_name;

	// 겟 셋
	public int getBoard_info_idx() {
		return board_info_idx;
	}

	public void setBoard_info_idx(int board_info_idx) {
		this.board_info_idx = board_info_idx;
	}

	public String getBoard_info_name() {
		return board_info_name;
	}

	public void setBoard_info_name(String board_info_name) {
		this.board_info_name = board_info_name;
	}

}
