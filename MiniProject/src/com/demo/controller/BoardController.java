package com.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.beans.ContentBean;
import com.demo.beans.LoginUserBean;
import com.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 글쓴 사람 확인용 (글쓴사람만 수정삭제가능위해서)
	@Resource(name = "loginUserBean")
	private LoginUserBean loginUserBean;

	// 게시판 제목 가져오기
	// 게시판 페이지 번호 처리하기
	// page 파라미터를 추가한다.(디폴트 값은 1페이지)
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		model.addAttribute("board_info_idx", board_info_idx);

		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);

		// 게시글 리스트를 가져올 때 page를 매개변수로 입력한다.
		List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
		model.addAttribute("contentList", contentList);

		return "board/main";
	}

	// 상세 글 읽기
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx, @RequestParam("content_idx") int content_idx,
			Model model) {
		// 게시판 이름 정보
		model.addAttribute("board_info_idx", board_info_idx);
		// 글 번호와 유저 정보
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("loginUserBean", loginUserBean);

		// 글 번호로 DB에서 게시글 내용 읽어오기
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentBean", readContentBean);

		return "board/read";
	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
			@RequestParam("board_info_idx") int board_info_idx) {

		writeContentBean.setContent_board_idx(board_info_idx);

		return "board/write";
	}

	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
			BindingResult result) {

		if (result.hasErrors()) {
			return "board/write";
		}
		// db에 저장(파일이름포함, 제목, 내용, 글쓴이, 글쓴 날짜)
		boardService.addContentInfo(writeContentBean);

		return "board/write_success";
	}

	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}

// 글 수정하기 들어가기
	// 파라미터로 board_info_idx, content_idx 받아서 modifyContentBean 객체에 추가한 뒤 모델에 추가
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam("content_idx") int content_idx, Model model,
			@ModelAttribute("modifyContentBean") ContentBean modifyContentBean) {

		modifyContentBean.setContent_board_idx(board_info_idx);
		modifyContentBean.setContent_idx(content_idx);

		boardService.getContents(modifyContentBean);
		model.addAttribute("modifyContentBean", modifyContentBean);

		return "board/modify";
	}

// 글 수정하기 유효성검사 실시
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
			BindingResult result) {

		if (result.hasErrors()) {
			return "board/modify";
		}
		// DB에서 업데이트
		boardService.modifyContentInfo(modifyContentBean);
		return "board/modify_success";
	}

// 글 삭제하기
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam("content_idx") int content_idx, Model model) {

		boardService.deleteContentInfo(content_idx);
		model.addAttribute("board_info_idx", board_info_idx);

		return "board/delete";
	}

}
