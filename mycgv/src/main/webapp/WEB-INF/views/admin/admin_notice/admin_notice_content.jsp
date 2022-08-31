<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.CgvNoticeVO"  %>
<%@ page import="com.mycgv.dao.CgvNoticeDAO"  %>

<%	
	String nid = request.getParameter("nid");
	CgvNoticeDAO dao = new CgvNoticeDAO();
	CgvNoticeVO vo = dao.select(nid);
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>공지사항-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td><%= vo.getNdate() %></td>
				<th>조회수</th>
				<td><%= vo.getNhits() %></td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3"><%= vo.getNtitle() %></td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3"><%= vo.getNcontent() %><br><br><br><br></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="admin_notice_update.do?nid=<%=vo.getNid()%>"><button type="button" class="btn_style">수정하기</button></a>
					<a href="admin_notice_delete.do?nid=<%=vo.getNid()%>"><button type="button" class="btn_style">삭제하기</button></a>
					<a href="admin_notice_list.do">
						<button type="button" class="btn_style">리스트</button></a>
					<a href="http://localhost:9000/mycgv/admin.do"><button type="button" class="btn_style">관리자홈</button></a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>






