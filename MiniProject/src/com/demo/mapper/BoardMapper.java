package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.demo.beans.ContentBean;

// Repository (MyBatis의 mapper)
public interface BoardMapper {

// content_idx = 시퀀스로 입력 (content_seq.nextval)
// content_subject,       폼에서 적은 제목 bean에서 가져오기
// content_text              폼에서 적은 내용 bean에서 가져오기
// content_file,        파일을 업로드 하고 파일을 이름 가져오기 
// 이때 NULL 값가능 마이바티스에서 #{변수,  jdbcType=VARCHAR }
// content_writer_idx,    로그인상태의 유저 인덱스 번호를 가져오기
// content_board_idx,    현재 글을 쓴 게시판 인덱스 번호를 가져오기( 폼에서 hidden으로 넘어옴)
// content_date = 현재날짜 sysdate	

// 글 작성하기

	@Insert("insert into content_table(content_idx, content_subject, content_text, "
			+ "content_file, content_writer_idx, content_board_idx, content_date) "
			+ "values (content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, "
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);

// 글 목록 보여주기

	@Select("select board_info_name " + "from board_info_table " + "where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);

	// to_char를 써서 문자열로 변환시켰기 때문에 빈에서 등록시 Date가 아닌 String으로 해줘야한다.

	@Select("select  t1.content_idx, t1.content_subject, t2.user_name as content_writer_name, "
			+ "to_char(t1.content_date, 'YYYY-MM-DD') as content_date " + "from content_table t1 JOIN user_table t2 "
			+ "ON t1.content_writer_idx = t2.user_idx "
			+ "and t1.content_board_idx = #{board_info_idx} order by t1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx);

// 글 상세 목록

	@Select("select t2.user_name as content_writer_name, " + "to_char(t1.content_date, 'YYYY-MM-DD') as content_date,"
			+ "t1.content_subject, t1.content_text, t1.content_file " + "from content_table t1 join user_table t2 "
			+ "on t1.content_writer_idx = t2.user_idx " + "and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);

}