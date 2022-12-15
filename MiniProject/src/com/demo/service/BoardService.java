package com.demo.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.beans.ContentBean;
import com.demo.beans.LoginUserBean;
import com.demo.mapper.BoardMapper;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Resource(name = "loginUserBean")
	private LoginUserBean loginUserBean;

	@Value("${path.upload}")
	private String path_upload;

	// 서버로 업로드 된 파일을 업로드 폴더에 저장하고 파일의 이름을 리턴하는 메소드
	private String saveUploadFile(MultipartFile upload_file) {

		// 현재 시간(밀리세컨드)을 이용해서 파일의 이름이 중복되지 않게 설정
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		// 파일 들어가면 생기는 오류 처리 파일을 못 찾는 등 여러 예외들을 처리 해줘야함
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return file_name;
	}

	public void addContentInfo(ContentBean writeContentBean) {
		// 파일담은 객체 확인보다 파일 사이즈로 확인 할 때 가독성 좋게 볼 수 있다.
		System.out.println("글 제목" + writeContentBean.getContent_subject());
		System.out.println("글 내용" + writeContentBean.getContent_text());
		System.out.println("파일담은객체명" + writeContentBean.getUpload_file());
		System.out.println("실제파일사이즈" + writeContentBean.getUpload_file().getSize());

		MultipartFile upload_file = writeContentBean.getUpload_file();

		if (upload_file.getSize() > 0) {
			// 위의 메서드로 파일을 저장하고 그 이름을 가져온다.
			String file_name = saveUploadFile(upload_file);
			// 파일을 저장한다.
			writeContentBean.setContent_file(file_name);
		}
		// 글쓴이가 현재 로그인된 유저
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());

		// 보드Mapper로
		boardMapper.addContentInfo(writeContentBean);
	}

	public String getBoardInfoName(int board_info_idx) {
		return boardMapper.getBoardInfoName(board_info_idx);
	}

	public List<ContentBean> getContentList(int board_info_idx) {
		return boardMapper.getContentList(board_info_idx);
	}

	// 글 상세보기
	public ContentBean getContentInfo(int content_idx) {
		return boardMapper.getContentInfo(content_idx);
	}
}
