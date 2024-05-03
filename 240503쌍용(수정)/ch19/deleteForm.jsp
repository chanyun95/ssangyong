<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	StoryDAO dao = StoryDAO.getInstance();
	StoryVO vo = dao.getStory(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('delete_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const id = document.getElementById('id');
		if(id.value.trim() == ''){
			alert('ID를 입력하세요.');
			id.value = '';
			id.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<h1>글 삭제</h1>
	<form id="delete_form" action="delete.jsp" method="post">
		<input type="hidden" name="snum" value="<%= vo.getSnum() %>">
		<ul>
			<li>
				<label for="id">ID</label>
				<input type="text" name="id" id="id" size="12" maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="글 삭제">
			<input type="button" value="목록" onclick="location.href='list.jsp'">
		</div>
	</form>
</div>
</body>
</html>