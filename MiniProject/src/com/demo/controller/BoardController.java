package com.demo.controller;

import java.util.List;

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
import com.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx, Model model) {
		model.addAttribute("board_info_idx", board_info_idx);

		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);

		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);

		return "board/main";
	}

	// 상세 글 읽기
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx, 
						@RequestParam("content_idx") int content_idx, Model model) {
		model.addAttribute("board_info_idx", board_info_idx);

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

	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}

	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}

}
