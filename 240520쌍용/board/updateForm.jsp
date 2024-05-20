<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('update_form');
		myForm.onsubmit=function(){
			const title = document.getElementById('title');
			if(title.value.trim()==''){
				alert('제목을 입력하세요');
				title.value='';
				title.focus();
				return false;
			}
			const content = document.getElementById('content');
			if(content.value.trim()==''){
				alert('내용을 입력하세요');
				content.value='';
				content.focus();
				return false;
			}
		};
	};
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
			<h2>글 수정</h2>
			<form id="update_form" action="update.do" method="post" enctype="multipart/form-data">
				<ul>
					<li>
						<label for="title">제목</label>
						<input type="text" name="title" id="title" value="${board.title}" maxlength="50">
					</li>
					<li>
						<label for="content">내용</label>
						<textarea rows="5" cols="40" name="content" id="content">${board.content}</textarea>
					</li>
					<li>
						<label for="filename">이미지</label>
						<input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg">
						<c:if test="${!empty board.filename}">
							<div id="file_detail">
								<img src="${pageContext.request.contextPath}/upload/${board.filename}" width="100">
								<br>
								<input type="button" value="파일 삭제" id="file_del">
							</div>
							<script type="text/javascript">
								
							</script>
						</c:if>
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="수정">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>