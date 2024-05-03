<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
int snum = Integer.parseInt(request.getParameter("snum"));
    StoryDAO dao = StoryDAO.getInstance();
    StoryVO vo = dao.getStory(snum);
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원이야기 글 상세</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>사원이야기 글 상세</h1>
	<ul>
		<li>글번호 : <%=vo.getSnum() %></li>
		<li>제목 : <%=vo.getTitle() %></li>
		<li>작성자 : <%=vo.getId() %></li>
	</ul>
	<hr size="1" noshade width="100%">
	<p>
		<%=vo.getContent() %>
	</p>
	<div class="align-right">
		작성일 : <%=vo.getReg_date() %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	//로그인 한 사원 번호와 작성자의 사원 번호가 일치하는지 체크
	if(user_num!=null && user_num == vo.getNum()){
%>
		<input type="button" value="수정" 
		    onclick="location.href='updateForm.jsp?snum=<%= snum %>'">
		<input type="button" value="삭제" id="delete_btn">	
				<script type="text/javascript">
					let delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('delete.jsp?snum=<%= snum %>');
						}
					};
				</script>    
<%		
	}
%>
		
		<input type="button" value="목록" 
		    onclick="location.href='list.jsp'">         
	</div>
</div>
</body>
</html>