<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){
		response.sendRedirect("loginForm.jsp");
	}else{
	//전송된 데이터에 대한 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");

	int snum = Integer.parseInt(request.getParameter("snum"));
	
	StoryDAO dao = StoryDAO.getInstance();
	StoryVO db_vo = dao.getStory(snum);
	if(user_num!=db_vo.getNum()){//타인의 글에 접속에 한 경우
%>
	<script>
		alert('잘못된 접속입니다.');
		location.href='list.jsp';
	</script>
<%
	}else{
	dao.delete(snum);
%>
	<script>
		alert('글 삭제를 완료했습니다.');
		location.replace('list.jsp');
	</script>
<%	
		}
	}
%>
