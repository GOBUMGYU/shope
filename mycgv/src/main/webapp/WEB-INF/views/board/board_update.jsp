<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
<script src="http://localhost:9000/mycgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/mycgv/resources/js/mycgv_jquery.js"></script>
<style>
	#upload_file {
		/*border:1px solid red;*/
		position:relative;
		left:265px; top:-30px;
		width:200px;  height:20px;
		display:inline-block;	
		background-color:white;	
		font-size:12px;
	}
</style>
<script>
	$(document).ready(function(){
		//새로운 파일선택하는 이벤트 
		$("input[type=file]").change(function(){
			if(window.FileReader){
				let fname = $(this)[0].files[0].name;
				$("#upload_file").text(fname);
			}
		});
	});
</script>
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>게시판-수정하기</h1>
		<form name="boardUpdateForm" action="board_update_check.do" 
				method="post" enctype="multipart/form-data">
			<input type="hidden" name="bid"  value="${vo.bid}" >
			<input type="hidden" name="bfile"  value="${vo.bfile}" >
			<input type="hidden" name="bsfile"  value="${vo.bsfile}" >
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="btitle" id="btitle" value="${vo.btitle }">
				</li>
				<li>
					<label>내용</label>
					<textarea name="bcontent">${vo.bcontent }</textarea>
				</li>
				<li>
					<label>파일첨부</label>
					<input type="file" name="file1" >
					<span id="upload_file">${vo.bfile}</span>
					
				</li>
				<li>
					<button type="button" class="btn_style" id="btnBoardUpdate">수정완료</button>
					<button type="reset" class="btn_style">다시쓰기</button>
					<a href="board_content.do?bid=${vo.bid }"><button type="button" class="btn_style">이전페이지</button></a>
					<a href="board_list.do"><button type="button" class="btn_style">리스트</button></a>
				</li>
			</ul>
		</form>
			
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







