<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	이름 : <%= request.getParameter("name") %><br>
	ID : <%= request.getParameter("id") %><br>
	비밀번호 : <%= request.getParameter("password") %><br>
	전화번호 : <%=request.getParameter("phone") %><br>
	<%
		String[] values = request.getParameterValues("hobby");
		if(values!=null){
	%>
		취미 :
	<%
			for(int i=0;i<values.length;i++){
				if(i>0) out.print(", ");
	%>
				<%= values[i] %>
	<%
			}
			out.println("<br>");
		}
	%>
	자기소개<br>
	<%=request.getParameter("content") %>
</body>
</html>