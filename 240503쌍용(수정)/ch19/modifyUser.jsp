<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){
		response.sendRedirect("loginForm.jsp");
	}else{
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="employee" class="kr.employee.vo.EmployeeVO"/>
<jsp:setProperty property="*" name="employee"/>
<%
	//num이 전송되지 않았기 때문에 session에 저장된 num을 사용
	employee.setNum(user_num);
	
	EmployeeDAO dao = EmployeeDAO.getInstance();
	dao.updateEmployee(employee);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<h1>회원정보 수정 완료</h1>
		<div class="result-display">
			<div class="align-center">
				회원정보 수정 완료!<p>
				<button onclick="location.href='myPage.jsp'">MyPage</button>
			</div>
		</div>
	</div>
</body>
</html>
<%
	}
%>