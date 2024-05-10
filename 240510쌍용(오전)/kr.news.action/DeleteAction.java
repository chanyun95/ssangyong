package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;

public class DeleteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		NewsDAO dao = NewsDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		NewsVO db_board = dao.getNews(num);
		boolean check = false;
		if(db_board!=null) {
			//비밀번호 일치 여부 체크
			check = db_board.isCheckedPassword(passwd);
		}
		if(check) {//비밀번호 일치
			dao.delete(num);
		}
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/delete.jsp";
	}

}
