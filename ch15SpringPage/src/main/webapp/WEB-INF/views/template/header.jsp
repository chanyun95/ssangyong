<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath}/member/registerUser">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
	</c:if>
</div>
<!-- 상단 끝 -->