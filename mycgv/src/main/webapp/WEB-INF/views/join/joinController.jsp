<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.CgvMemberVO" %>
<%@ page import="com.mycgv.dao.CgvMemberDAO" %>

<jsp:useBean id="vo"  class="com.mycgv.vo.CgvMemberVO" />
<jsp:setProperty name="vo" property="*" />    

<%
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.insert(vo);
	if(result == 1){
		//System.out.println("가입 성공!!");
		//response.sendRedirect("http://localhost:9000/mycgv/login/login.jsp");
		response.sendRedirect("../login/login.jsp?join=ok");
	}else{
		//System.out.println("가입 실패!!");
		response.sendRedirect("../errorPage.jsp");
	}
%>
 


