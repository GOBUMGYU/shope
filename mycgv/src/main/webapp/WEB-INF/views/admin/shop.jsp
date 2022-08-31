<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
 <meta charset="utf-8">
 <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container" style="max-width: 600px">
		 <div class="py-5 text-center">
		 <h2>상품 목록</h2>
		 </div>
	 <div class="row">
	 <div class="col">
	 <button class="btn btn-primary float-end"
	 onclick="location.href='addForm.html'" type="button">상품
	등록</button>
	 </div>
	 </div>
	 <hr class="my-4">
	 <div>
	 <table class="table">
	 <thead>
	 <tr>
	 <th>ID</th>
	 <th>상품명</th>
	 <th>가격</th>
	 <th>수량</th>
	 </tr>
	 </thead>
	 <tbody>
	 <tr>
	 <td><a href="item.html">1</a></td>
	 <td><a href="item.html">테스트 상품1</a></td>
	 <td>10000</td>
	 <td>10</td>
	 </tr>
	 <tr>
	 <td><a href="item.html">2</a></td>
	 <td><a href="item.html">테스트 상품2</a></td>
	 <td>20000</td>
	 <td>20</td>
	 </tr>
	 </tbody>
	 </table>
	 </div>
</div> <!-- /container -->
</body>
</html>