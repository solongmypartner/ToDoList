<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="./style2.css" rel="stylesheet">
<meta charset="UTF-8">
<title>할 일 등록</title>
</head>
<body>

	<h1>할 일 등록</h1>
	
	<div id="formTodo">
		<form action="/todo/todoadd" method="post">
			<p>
				어떤일인가요?<br>
				<input type="text" name="title" size="24" id="title" placeholder="  swift 공부하기(24자까지)">
			</p>
		
			<p>
				누가 할 일 인가요?<br>
				<input type="text" name="name" id="person_name" placeholder="  홍길동">
			</p>
			
			<p id="sequence-container">
				우선순위를 선택하세요<br>
				<input type="radio" name="sequence" value="1" class="sequence"><span class="sequence-name">1순위</span>
				<input type="radio" name="sequence" value="2" class="sequence"><span class="sequence-name">2순위</span>
				<input type="radio" name="sequence" value="3" class="sequence"><span class="sequence-name">3순위</span>
			</p>
		
		<div id="flex-container">
			<a href="/todo/main"><input type="button" value="<이전" id="before"></a>
			<input type="submit" value="제출" id="submit">
			<input type="button" value="내용지우기" id="removeContent">	
		</div>
		
	</form>
	</div>
<script src="./script2.js"></script>
</body>
</html>