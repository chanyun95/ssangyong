<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원로그인 시작 -->
<div class="page-main">
	<c:if test="${id == null}">
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&amp;client_id=c37546ec572f00c776f1b466369745f3&amp;redirect_uri=http://localhost:8000/kakaologin">
			<img src="${pageContext.request.contextPath}/images/kakao_login_medium_narrow.png">
		</a>
	</c:if>
	<c:if test="${id != null}">
		<form name="Logout" action="http://localhost:8000/logout">
			<div></div>
			<input type="submit" value="로그아웃">
		</form>
	</c:if>
</div>
<!-- 회원로그인 끝 -->