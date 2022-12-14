package com.demo.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ContentBean {

	// 테이블의 열이름과 클래스 변수명이 똑같게 만든다.
	// 게시글 테이블 (게시글: 인덱스(pk,uq),제목,내용,첨부파일(null),
	// 게시글작성자:인덱스,
	// 게시판: 인덱스,작성날짜)
	private int content_idx;

	@NotBlank(message = "제목을 입력해주세요")
	private String content_subject;

	@NotBlank(message = "내용을 입력해주세요")
	private String content_text;

	private String content_file;
	private int content_writer_idx;
	private int content_board_idx;
	private Date content_date;

	// 겟셋
	public int getContent_idx() {
		return content_idx;
	}

	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}

	public String getContent_subject() {
		return content_subject;
	}

	public void setContent_subject(String content_subject) {
		this.content_subject = content_subject;
	}

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getContent_file() {
		return content_file;
	}

	public void setContent_file(String content_file) {
		this.content_file = content_file;
	}

	public int getContent_writer_idx() {
		return content_writer_idx;
	}

	public void setContent_writer_idx(int content_writer_idx) {
		this.content_writer_idx = content_writer_idx;
	}

	public int getContent_board_idx() {
		return content_board_idx;
	}

	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}

	public Date getContent_date() {
		return content_date;
	}

	public void setContent_date(Date content_date) {
		this.content_date = content_date;
	}

}
