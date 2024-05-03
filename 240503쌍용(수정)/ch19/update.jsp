<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="kr.story.vo.StoryVO"/>
<jsp:setProperty property="*" name="vo"/>
<%	
	StoryDAO dao = StoryDAO.getInstance();
	StoryVO db_vo = dao.getStory(vo.getSnum());
	
	vo.setIp(request.getRemoteAddr());
	dao.update(vo);
%>
	<script>
		alert('수정을 완료했습니다.');
		location.href='detail.jsp?snum=<%= vo.getSnum() %>';
	</script>