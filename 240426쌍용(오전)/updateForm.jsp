<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 수정</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<div class="page-main">
		<h2>상품 정보 수정</h2>
<%
		int num = Integer.parseInt(request.getParameter("num"));
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try{
			//Connection 객체 반환
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM product WHERE num=?";
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//JDBC 수행 4단계 : SQL문을 테이블에 반영하고 결과행을 ResultSet에 담아서 반환
			rs = pstmt.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String origin = rs.getString("origin");
%>
				<form id="myForm" action="update.jsp" method="post">
					<input type="hidden" name="num" value="<%= num %>">
					<ul>
						<li>
							<label for="name">상품명</label>
							<input type="text" name="name" id="name" size="20" value="<%= name %>">
						</li>
						<li>
							<label for="price">가격</label>
							<input type="number" name="price" id="price" size="10" value="<%= price %>">
						</li>
						<li>
							<label for="stock">재고</label>
							<input type="number" name="stock" id="stock" size="10" value="<%= stock %>">
						</li>
						<li>
							<label for="origin">원산지</label>
							<input type="text" name="origin" id="origin" size="20" value="<%= origin %>">
						</li>
					</ul>
					<div class="align-center">
						<input type="submit" value="수정">
						<input type="button" value="목록" onclick="location.href='select.jsp'">
					</div>
				</form>
<%
			}else{
%>
				<div class="result-display">
					<div class="align-center">
						오류 발생! 수정할 상품 정보 호출 실패!<p>
						<input type="button" value="목록" onclick="location.href='select.jsp'">
					</div>
				</div>
<%
			}
		}catch(Exception e){
%>
			<div class="result-display">
				<div class="align-center">
					오류 발생! 수정할 상품 정보 호출 실패!<p>
					<input type="button" value="목록" onclick="location.href='select.jsp'">
				</div>
			</div>
<%
			e.printStackTrace();
		}finally{
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
%>	
	</div>
</body>
</html>