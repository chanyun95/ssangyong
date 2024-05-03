<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO"%>
<%@ page import="kr.employee.vo.EmployeeVO"%>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세정보</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<%
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO vo = dao.getEmployee(user_num);
%>
	<div class="page-main">
		<h1>회원정보</h1>
		<ul>
			<li>이름 :  <%= vo.getName() %> </li>
			<li>ID :  <%= vo.getId() %> </li>
			<li>연봉 :  <%= vo.getSalary() %> </li>
			<li>직업 :  <%= vo.getJob() %> </li>
		</ul>
		<hr size="1" width="100%" noshade="noshade">
		<div class="align-right">
			<input type="button" value="회원정보수정" onclick="location.href='modifyUserForm.jsp'">
			<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.jsp'">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</div>
</body>
</html>