<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.CgvMemberVO" %>
<%@ page import="com.mycgv.dao.CgvMemberDAO" %>

<jsp:useBean id="vo" class="com.mycgv.vo.CgvMemberVO" />
<jsp:setProperty property="*" name="vo"/>

<%
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.select(vo);
	if(result == 1){
		//로그인 성공 --> session객체에 key,value 추가 후 index 페이지로 이동
		session.setAttribute("sid", vo.getId());  //sid=test
		response.sendRedirect("../index.jsp?login=ok");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
%>