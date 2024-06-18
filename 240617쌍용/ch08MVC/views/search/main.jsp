<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 검색 메인</title>
</head>
<body>
<!-- method="get" < 명시하지 않으면 기본값 생략 가능 -->
<form action="game.do" method="get">
	<select name="type">
		<option value="1">전체</option>
		<option value="2">아이템</option>
		<option value="3">캐릭터</option>
	</select>
	<input type="search" name="query">
	<input type="submit" value="검색">
</form>
</body>
</html>