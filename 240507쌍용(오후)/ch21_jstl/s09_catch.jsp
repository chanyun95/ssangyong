<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catch 태그</title>
</head>
<body>
	<h1>catch 태그 사용하기</h1>
	<c:catch var="ex">
		name 파라미터의 값 = <%= request.getParameter("name") %><br>
		<%
			if(request.getParameter("name").equals("test")){
		%>
				${param.name}은 test 입니다.
		<%		
			}
		%>
	</c:catch>
	<c:if test="${ex!=null}">
		예외가 발생했습니다.<br>
		${ex}
	</c:if>
</body>
</html>