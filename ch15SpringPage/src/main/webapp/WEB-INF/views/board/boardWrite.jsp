<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 게시판 글 작성 시작 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include ckeditor js -->
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write" id="board_register" enctype="multipart/form-data" modelAttribute="boardVO">
		<ul>
			<li>
				<form:label path="category">분류</form:label>
				<form:select path="category">
					<option disabled="disabled" selected>선택하세요</option>
					<form:option value="1">자바</form:option>
					<form:option value="2">데이터베이스</form:option>
					<form:option value="3">자바스크립트</form:option>
					<form:option value="4">기타</form:option>
				</form:select>
				<form:errors path="category" cssClass="error-color"/>
			</li>
			<li>
				<form:input path="title" placeholder="제목을 입력하세요"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
				<script>
				function MyCustomUploadAdapterPlugin(editor) {
				   editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
				       return new UploadAdapter(loader);
				   }
				}
				ClassicEditor.create( document.querySelector( '#content' ),{
	            	extraPlugins: [MyCustomUploadAdapterPlugin]
	            })
	            .then( editor => {
					window.editor = editor;
				})
	            .catch( error => {
	                console.error( error );
	            });
			    </script>
			</li>
			<li>
				<form:label path="upload">파일업로드</form:label>
				<input type="file" name="upload" id="upload">
			</li>
		</ul>
		<div class="align-center">
			<form:button class="default-btn">전송</form:button>
			<input type="button" value="목록" class="default-btn" onclick="location.href='list'">
		</div>
	</form:form>
</div>
<!-- 게시판 글 작성 끝 -->