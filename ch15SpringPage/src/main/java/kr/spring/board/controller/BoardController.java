package kr.spring.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	/*===================
 		게시판 글 쓰기
 	===================*/
	//등록 폼 호출
	@GetMapping("/board/write")
	public String form() {
		return "boardWrite";
	}
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/board/write")
	public String submit(@Valid BoardVO boardVO,BindingResult result,HttpServletRequest request,HttpSession session,Model model)
															throws IllegalStateException,IOException {
		log.debug("<<게시판 글 저장>> : " + boardVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		//회원번호 셋팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		boardVO.setMem_num(vo.getMem_num());
		//ip 셋팅
		boardVO.setIp(request.getRemoteAddr());
		//파일 업로드
		boardVO.setFilename(FileUtil.createFile(request,boardVO.getUpload()));
		//글쓰기
		boardService.insertBoard(boardVO);
		//View 메시지 처리
		model.addAttribute("message","성공적으로 글이 등록되었습니다.");
		model.addAttribute("url",request.getContextPath()+"/board/list");
		
		return "common/resultAlert";
	}
	
	/*===================
	 	게시판 목록
	 ===================*/
	@GetMapping("/board/list")
	public String getList(@RequestParam(defaultValue="1") int pageNum,
							@RequestParam(defaultValue="1") int order,
							@RequestParam(defaultValue="") String category,
							String keyfield,String keyword,Model model) {
		log.debug("<<게시판 목록 - category>> : " + category);
		log.debug("<<게시판 목록 - order>> : " + order);
		
		return "boardList";
	}
}
