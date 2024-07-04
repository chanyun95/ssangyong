package kr.spring.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardAjaxController {
	@Autowired
	private BoardService boardService;
	/*===================
		부모글 좋아요 읽기
	===================*/
	//부모글 좋아요 읽기
	@GetMapping("/board/getFav")
	@ResponseBody
	public Map<String, Object> getFav(BoardFavVO fav,HttpSession session){
		log.debug("<<게시판 좋아요 - BoardFavVO>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapJson.put("status", "noFav");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user.getMem_num());
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav != null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", boardService.selectFavCount(fav.getBoard_num()));
		
		return mapJson;
	}
	//부모글 좋아요 등록/삭제
	@PostMapping("/board/writeFav")
	@ResponseBody
	public Map<String, Object> writeFav(BoardFavVO fav,HttpSession session){
		log.debug("<<게시판 좋아요 - 등록>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원정보 셋팅
			fav.setMem_num(user.getMem_num());
			
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav != null) {
				//등록이 되어있으면 삭제 처리
				boardService.deleteFav(fav);
				mapJson.put("status", "noFav");
			}else {
				//등록
				boardService.insertFav(fav);
				mapJson.put("status", "yesFav");
			}
			mapJson.put("result", "success");
			mapJson.put("count", boardService.selectFavCount(fav.getBoard_num()));
		}
		return mapJson;
	}
	
	/*===================
 		부모글 데이터 처리
 	===================*/
	//업로드 파일 삭제
	@PostMapping("/board/deleteFile")
	@ResponseBody
	public Map<String, String> processFile(long board_num,HttpSession session,HttpServletRequest request){
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapJson.put("result", "logout");
		}else {
			BoardVO db_board = boardService.selectBoard(board_num);
			//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			if(user.getMem_num() != db_board.getMem_num()) {
				//불일치
				mapJson.put("result", "wrongAccess");
			}else {
				//일치
				boardService.deleteFile(board_num);
				FileUtil.removeFile(request, db_board.getFilename());
				mapJson.put("result", "success");
			}
		}
		return mapJson;
	}
	/*===================
		댓글 등록
	===================*/
	@PostMapping("/board/writeReply")
	@ResponseBody
	public Map<String, String> writeReply(BoardReplyVO boardReplyVO,HttpSession session,
																HttpServletRequest request){
		log.debug("<<댓글 등록>> : " + boardReplyVO);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			//로그인X
			mapJson.put("result", "logout");
		}else {
			//로그인O
			boardReplyVO.setMem_num(user.getMem_num());
			//ip등록
			boardReplyVO.setRe_ip(request.getRemoteAddr());
			//댓글 등록
			boardService.insertReply(boardReplyVO);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	/*===================
		댓글 목록
	===================*/
	@GetMapping("/board/listReply")
	@ResponseBody
	public Map<String, Object> getList(int board_num,int pageNum,int rowCount,HttpSession session){
		
		log.debug("<<댓글 목록 - board_num>> : " + board_num);
		log.debug("<<댓글 목록 - pageNum>> : " + pageNum);
		log.debug("<<댓글 목록 - rowCount>> : " + rowCount);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("board_num", board_num);
		//총글의 개수
		int count = boardService.selectRowCountReply(map);
		//페이지 처리
		PagingUtil page = new PagingUtil(pageNum,count,rowCount);
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user != null) {
			map.put("mem_num", user.getMem_num());
		}else {
			map.put("mem_num", 0);
		}
		
		List<BoardReplyVO> list = null;
		if(count > 0) {
			list = boardService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		mapJson.put("count", count);
		mapJson.put("list", list);
		if(user!=null) {
			mapJson.put("user_num", user.getMem_num());	
		}
		return mapJson;
	}
	/*===================
		댓글 수정
	===================*/
	@PostMapping("/board/updateReply")
	@ResponseBody
	public Map<String, String> modifyReply(BoardReplyVO boardReplyVO,HttpSession session,
																		HttpServletRequest request){
		log.debug("<<댓글 수정>> : " + boardReplyVO);
		Map<String, String> mapJson = new HashMap<String, String>();

		MemberVO user = (MemberVO)session.getAttribute("user");
		
		BoardReplyVO db_reply = boardService.selectReply(boardReplyVO.getRe_num());
		if(user == null) {
			//로그인X
			mapJson.put("result", "logout");
		}else if(user!=null && user.getMem_num()==db_reply.getMem_num()){
			//로그인한 회원번호와 작성자 회원번호 일치
			//ip 저장
			boardReplyVO.setRe_ip(request.getRemoteAddr());
			//댓글 수정
			boardService.updateReply(boardReplyVO);
			mapJson.put("result", "success");
		}else {
			//로그인 회원번호와 작성자 회원번호 불일치
			mapJson.put("result", "wrongAccess");
		}
		return mapJson;
	}
	/*===================
		댓글 삭제
	===================*/
	@PostMapping("/board/deleteReply")
	@ResponseBody
	public Map<String, String> deleteReply(long re_num,HttpSession session){
		log.debug("<<댓글 삭제>> : " + re_num);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		BoardReplyVO db_reply = boardService.selectReply(re_num);
		if(user == null) {
			mapJson.put("result", "logout");
		}else if(user!=null && user.getMem_num() == db_reply.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			boardService.deleteReply(re_num);
			mapJson.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapJson.put("result", "wrongAccess");
		}
		return mapJson;
	}
	/*===================
		댓글 좋아요
	===================*/
	@GetMapping("/board/getReFav")
	@ResponseBody
	public Map<String, Object> getReFav(BoardReFavVO fav,HttpSession session){
		log.debug("<<댓글 좋아요>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapJson.put("result", "logout");
			mapJson.put("status", "noFav");
		}else {
			fav.setMem_num(user.getMem_num());
			BoardReFavVO boardReFav = boardService.selectReFav(fav);
			if(boardReFav != null) {
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", boardService.selectFavCount(fav.getRe_num()));
		
		return mapJson;
	}
	
	
}
