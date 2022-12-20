package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

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
// content_idx 값이 나오면 먼저(before) sql문을 실행 결과를 입력한다.
// 글 작성후에 바로 읽으려면 content_idx 게시글 번호를 알아야하는데 글 번호를 자동생성(content_seq.nextval)
// 으로 하고 있어서 바로 알 수 없음, 마이바티스에서 제공하는 @SelectKey를 사용해서 미리 시퀀스를 사용해
// 게시글 번호를 writeContentBean에 입력하면서 lnsert쿼리문실행한다.

	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)

	@Insert("insert into content_table(content_idx, content_subject, content_text, "
			+ "content_file, content_writer_idx, content_board_idx, content_date) "
			+ "values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, "
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);

// 글 목록 보여주기

	@Select("select board_info_name " + "from board_info_table " + "where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);

	// to_char를 써서 문자열로 변환시켰기 때문에 빈에서 등록시 Date가 아닌 String으로 해줘야한다.
	// rowBounds 마이바티스에서 해줘서 편함

	@Select("select  t1.content_idx, t1.content_subject, t2.user_name as content_writer_name, "
			+ "to_char(t1.content_date, 'YYYY-MM-DD') as content_date " + "from content_table t1 JOIN user_table t2 "
			+ "ON t1.content_writer_idx = t2.user_idx "
			+ "and t1.content_board_idx = #{board_info_idx} order by t1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);

	// 전체 글 갯수 읽어오기
	@Select("select count(*) from content_table where content_board_idx = #{content_board_idx}")
	int getContentCnt(int content_board_idx);
	
// 글 상세 목록
// 원래 글쓴이 정보 안 가져왔는데 t1.content_writer_idx정보 추가로 넣어서 정보 가져온다.
// 이걸로 글쓴정보와 세션의 로그인유저빈 정보를 비교해서 나중에 사용할 것임

	@Select("select t2.user_name as content_writer_name, " + "to_char(t1.content_date, 'YYYY-MM-DD') as content_date,"
			+ "t1.content_subject, t1.content_text, t1.content_file, t1.content_writer_idx "
			+ "from content_table t1 join user_table t2 " + "on t1.content_writer_idx = t2.user_idx "
			+ "and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);

	// 글 수정하기
	@Update("update content_table " + "set content_subject = #{content_subject}, content_text = #{content_text}, "
			+ "content_file = #{content_file, jdbcType=VARCHAR} " + "where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);

	// 글 삭제하기
	@Delete("delete from content_table where content_idx=#{content_idx}")
	void deleteContentInfo(int content_idx);

}