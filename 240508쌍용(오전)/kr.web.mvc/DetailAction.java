package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request에 데이터 저장
		request.setAttribute("detail", "글 상세 페이지입니다.");
		//JSP 경로 반환
		return "/views/detail.jsp";
	}
	
}
