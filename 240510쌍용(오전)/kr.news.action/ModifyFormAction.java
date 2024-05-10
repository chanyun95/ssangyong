package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;

public class ModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		NewsDAO dao = NewsDAO.getInstance();
		NewsVO vo = dao.getNews(num);
		
		request.setAttribute("vo", vo);
		
		return "/WEB-INF/views/modifyForm.jsp";
	}

}
