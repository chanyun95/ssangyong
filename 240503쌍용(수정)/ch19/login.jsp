<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	EmployeeDAO dao = EmployeeDAO.getInstance();
	//id 존재 여부 확인
	EmployeeVO employee = dao.checkEmployee(id);
	boolean check = false;
	
	if(employee!=null){
		check = employee.isCheckedPassword(passwd);
	}
	
	if(check){
		session.setAttribute("user_num", employee.getNum());
		session.setAttribute("user_id", id);
		
		response.sendRedirect("main.jsp");
	}else{
%>
	<script type="text/javascript">
		alert('아이디 또는 비밀번호 불일치');
		history.go(-1);
	</script>
<%		
	}
%>	