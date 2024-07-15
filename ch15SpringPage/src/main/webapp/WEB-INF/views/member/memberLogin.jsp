<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원로그인 시작 -->
<div class="page-main">
	<h2>회원로그인</h2>
	<form:form action="login" id="member_login" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li class="floating-label">
				<form:input path="id" placeholder="아이디" autocomplete="off" cssClass="form-input"/>
				<form:label path="id">아이디</form:label>
				<form:errors path="id" cssClass="error-color"/>
			</li>
			<li class="floating-label">
				<form:password path="passwd" placeholder="비밀번호" cssClass="form-input"/>
				<form:label path="passwd">비밀번호</form:label>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="auto"><input type="checkbox" name="auto" id="auto">로그인 상태 유지</label>
			</li>
		</ul>
		<div>
			<form:button class="login-btn">로그인</form:button>
		</div>
	</form:form>
	<p class="align-center">
		<input type="button" value="홈으로" class="default-btn" onclick="location.href='${pageContext.request.contextPath}/main/main'">
		<input type="button" value="비밀번호 찾기">
	</p>
	<hr size="1" width="90%" noshade="noshade">
	<c:if test="${userId == null}">
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&amp;client_id=c37546ec572f00c776f1b466369745f3&amp;redirect_uri=http://localhost:8000/member/login">
			<img src="../images/kakao_login_medium_narrow.png">
		</a>
	</c:if>
</div>
<!-- 회원로그인 끝 -->