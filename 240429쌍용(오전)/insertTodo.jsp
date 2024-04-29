<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%
	//전송된 데이터 인코딩 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String todo = request.getParameter("todo");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	try{
		//Connection 객체 반환
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "INSERT INTO todo (id,todo) VALUES (todo_seq.nextval,?)";
		//JDBC 수행 3단계
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setString(1, todo);
		//JDBC 수행 4단계
		pstmt.executeUpdate();
		%>
		{"result":"success"}
		<%
	}catch(Exception e){
		%>
		{"result":"failure"}
		<%
		e.printStackTrace();
	}finally{
		DBUtil.executeClose(null, pstmt, conn);
	}
%>