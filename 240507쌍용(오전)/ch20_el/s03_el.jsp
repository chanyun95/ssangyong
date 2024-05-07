<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어 - 파라미터 값 처리</title>
</head>
<body>
	<h3>표현언어의 - 파라미터 값 처리</h3>
	<form action="s03_el.jsp" method="post">
		좋아하는 계절
		<input type="checkbox" name="season" value="봄">봄
		<input type="checkbox" name="season" value="여름">여름
		<input type="checkbox" name="season" value="가을">가을
		<input type="checkbox" name="season" value="겨울">겨울
		<input type="submit" value="확인">
	</form>
	<br/>
	${paramValues.season[0]}
	${paramValues.season[1]}
	${paramValues.season[2]}
	${paramValues.season[3]}<br>
	--------------------------<br>
	${paramValues["season"][0]}
	${paramValues["season"][1]}
	${paramValues["season"][2]}
	${paramValues["season"][3]}<br>
</body>
</html>