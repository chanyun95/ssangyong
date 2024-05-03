<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인 O
		response.sendRedirect("loginForm.jsp");
	}else{//로그인 X
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#modify_form').submit(function(){
			const items = document.querySelectorAll('.input-check');
			for(let i=0;i<items.length;i++){
				if(items[i].value.trim()==''){
					const label = document.querySelector('label[for="'+items[i].id+'"]');
					alert(label.textContent + '항목은 필수 입력');
					items[i].value='';
					items[i].focus();
					return false;
				}
			}
		});
	});
</script>
</head>
<body>
<%
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO employee = dao.getEmployee(user_num);
%>
	<div class="page-main">
		<h1>회원정보 수정</h1>
		<form action="modifyUser.jsp" method="post" id="modify_form">
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name" id="name" value="<%= employee.getName() %>" maxlength="10" class="input-check">
				</li>
				<li>
					<label for="job">직업</label>
					<input type="text" name="job" id="job" value="<%= employee.getJob() %>" maxlength="12" class="input-check">
				</li>
				<li>
					<label for="salary">연봉</label>
					<input type="text" name="salary" id="salary" value="<%= employee.getSalary() %>" maxlength="50" class="input-check">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="홈으로" onclick="location.href='main.jsp'">
			</div>
		</form>
	</div>
</body>
</html>
<%
	}
%>
