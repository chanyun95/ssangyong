<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	String user_id = (String)session.getAttribute("user_id");
	//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int snum = Integer.parseInt(request.getParameter("snum"));
		String id = request.getParameter("id");
		
		StoryDAO dao = StoryDAO.getInstance();
		StoryVO vo = dao.checkStory(id);
		
		boolean check = false;
		
		if(vo!=null && user_id.equals(id)){
			
			dao.delete(snum);
%>
	<script>
		alert('글 삭제를 완료했습니다.');
		location.replace('list.jsp');
	</script>
<%
		}
%>

