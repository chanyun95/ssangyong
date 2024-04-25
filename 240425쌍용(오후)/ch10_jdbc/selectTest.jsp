<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.util.DBUtil" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body class="page-main">
	<h2>게시판 목록</h2>
	<div class="align-right">
		<input type="button" value="글 쓰기" onclick="location.href='insertTestForm.jsp'">
	</div>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	try{
		//Connection 객체 반환
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "SELECT * FROM tboard ORDER BY num DESC";
		//JDBC 수행 3단계 : PrepareStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//JDBC 수행 4단계 : SQL문을 실행해서 테이블에 반영하고 결과행을 ResultSet에 담아서 반환
		rs = pstmt.executeQuery();
%>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
<%
		while(rs.next()){
			int num = rs.getInt("num");
			String name = rs.getString("name");
			String title = rs.getString("title");
			Date reg_date = rs.getDate("reg_date");
%>
			<tr>
				<td><%= num %></td>
				<td><a href="detailTest.jsp?num=<%= num %>"><%= title %></a></td> <!-- 프라이머리키를 이용해서 선택한 번호의 정보를 불러옴 -->
				<td><%= name %></td>
				<td><%= reg_date %></td>
			</tr>
<%
		}
%>
		</table>
<%
	}catch(Exception e){
%>
		<div class="result-display">
			<span>오류 발생!</span>
		</div>
<%
		e.printStackTrace();
	}finally{
		//자원정리
		DBUtil.executeClose(rs, pstmt, conn);
	}
%>
</body>
</html>