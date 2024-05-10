package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
	
		NewsDAO dao = NewsDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		NewsVO db_news = dao.getNews(num);
		
		boolean check = false;
		if(db_news!=null) {
			//비밀번호 일치 여부 체크
			check = db_news.isCheckedPassword(passwd);
		}
		if(check) {//비밀번호 일치
			NewsVO vo = new NewsVO();
			vo.setNum(Integer.parseInt(request.getParameter("num")));
			vo.setTitle(request.getParameter("title"));
			vo.setWriter(request.getParameter("writer"));
			vo.setPasswd(request.getParameter("passwd"));
			vo.setEmail(request.getParameter("email"));
			vo.setArticle(request.getParameter("article"));
			vo.setFilename(FileUtil.createFile(request, "filename"));
			
			dao.update(vo);
			
			if(vo.getFilename()!=null && !vo.getFilename().isEmpty()) {
				//새 파일로 교체할 때 원래 파일 제거
				FileUtil.removeFile(request, db_news.getFilename());
			}
			//상세페이지로 이동하기 위해 글 번호 저장
			request.setAttribute("num", vo.getNum());
		}
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/modify.jsp";
	}

}
