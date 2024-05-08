package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("delete", "글을 삭제했습니다.");
		
		return "/views/delete.jsp";
	}
	
}
