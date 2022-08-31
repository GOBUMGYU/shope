<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.mycgv.dao.CgvMemberDAO" %>    
<%
	String id = request.getParameter("id");
	CgvMemberDAO dao = new CgvMemberDAO();
	int result = dao.idCheck(id);
	
	//System.out.println("result--->" + result);
	//Ajax에 전송하는 결과는 반드시!!! 문자열(String)형태로 전송해야 한다.
	//자바에서 형변환 : Integer.parseInt(type), String.valueOf(type)
	
	out.write(String.valueOf(result));
%>   
