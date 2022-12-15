package com.demo.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

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

	private int content_board_idx;
	private String content_date;

	private String content_file; // 파일이름이 들어감 문자열 변수

	private MultipartFile upload_file; // 업로드 한 파일을 담을 객체 추가하기

	private int content_writer_idx;

	private String content_writer_name; // 글쓴이 이름 추가

	// 겟셋
	public String getContent_writer_name() {
		return content_writer_name;
	}

	public void setContent_writer_name(String content_writer_name) {
		this.content_writer_name = content_writer_name;
	}

	public MultipartFile getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}

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

	public String getContent_date() {
		return content_date;
	}

	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}

}
