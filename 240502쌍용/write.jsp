<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.board.dao.BoardDAO" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="board" class="kr.board.vo.BoardVO"/>
<jsp:setProperty property="*" name="board"/>
<%
	//클라이언트의 ip 주소 저장
	board.setIp(request.getRemoteAddr());

	BoardDAO dao = BoardDAO.getInstance();
	dao.insert(board);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<h1>글 쓰기 완료</h1>
		<div class="result-display">
			<div class="align-center">
				게시판에 글을 등록했습니다.<p>
				<button onclick="location.href='list.jsp'">목록</button>
			</div>
		</div>
	</div>
</body>
</html>