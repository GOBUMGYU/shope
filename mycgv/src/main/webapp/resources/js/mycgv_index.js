$(document).ready(function(){
	/**
	* index 페이지 로딩 시 공지사항 리스트 가져오기
	**/
	initNoticeList(1);
	
	function initNoticeList(rpage){
		//Ajax를 이용한 데이터 가져오기
		$.ajax({
			url : "notice_list_json.do?rpage="+rpage,
			success : function(result){
				//1. 콜백함수의 결과인 result 값은 문자열을 JSON 객체로 변환
				let notice = JSON.parse(result);
				
				//2. Dynamic HTML을 이용하여 JSON 결과를 출력하는 코드 생성
				let output = "<div class='notice_style'><h3>공지사항</h3>";
				output += "<div>&nbsp;&nbsp;<span id='pre'>pre</span>&nbsp;&nbsp;|";
				output += "&nbsp;&nbsp;<span id='next'>next</span>&nbsp;&nbsp;|";
				output += "&nbsp;&nbsp;<span id='more'>more</span></div>";
				output += "<ul class='notice_list'>";
				for(data of notice.list){
					output += "<li>";
					output += "<span>"+ data.rno +"</span>";
					output += "<span>"+ data.ntitle +"</span>";
					output += "<span>"+ data.ndate +"</span>";
					output += "<span>"+ data.nhits +"</span>";				
					output += "</li>";	
				}
						
				output += "</ul></div>";	
				
				
				//3. 생성된 Dynamic HTML 코드를 해당위치에 출력
				$("div.notice_style").remove();	
				$("div.s2_article > div").after(output);
				
				//pre, next 버튼 활성화				
				if(rpage >= 1 && rpage < notice.pageCount ){
					$("#pre").css("visibility","hidden");					
					$("#next").css("color","red");
				}else{
					$("#pre").css("color","red");
					$("#next").css("visibility","hidden");
				}				
				
				
				//4. Dynamic HTML 코드에서 이벤트 처리 진행
				$("#pre").click(function(){					
					initNoticeList(rpage-1);
				});
					
				$("#next").click(function(){					
					initNoticeList(rpage+1);
				});
							
				$("#more").click(function(){
					$(location).attr("href","http://localhost:9000/mycgv/notice_list.do");
				});			
				
			}//success
		});//ajax
				
		
	}//initNoticeList

});//ready





