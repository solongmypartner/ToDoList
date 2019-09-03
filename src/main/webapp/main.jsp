<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="todo.dto.TodoDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<link href="./style.css" rel="stylesheet">
<meta charset="UTF-8">
<title>ToDo List</title>
</head>
<body>

<!--	<c:forEach items="${toDoList }" var="todo">
		${todo }<br>
	</c:forEach>
	
-->

	<div class="flex-end">
		<a href="/todo/todoform">
			<div id="registerTodo">새로운 TODO 등록</div>
		</a>
	</div>
	


<%
	List<TodoDto> toDoList = (List<TodoDto>)request.getAttribute("toDoList");
	List<TodoDto> doingList = (List<TodoDto>)request.getAttribute("doingList");
	List<TodoDto> doneList = (List<TodoDto>)request.getAttribute("doneList");
%>
<div id="container">
<span id="rotated">나의 해야할 일들</span>
<div id="todo">
	<div id="todoTitle"><b>TODO</b></div>
<%	
	for (TodoDto todo : toDoList) {
%>

	<div class="content">
		<div class="contentTitle"><b><%= todo.getTitle() %></b></div>
		<div class="restContent">
			등록날짜: <%= todo.getRegdate() %>, <%= todo.getName() %>, 우선순위<%= todo.getSequence() %>
			<form action="/todo/todotype" method="post" class="formTag">
				<input type="hidden" value=<%= todo.getType() %> name="currentType">
				<input type="hidden" value=<%= todo.getId() %> name="currentId">
				<input type="submit" value="→">
			</form>
		</div>
	</div>
		
<%
	}
%>
</div>
<div id="doing">
	<div id="doingTitle"><b>DOING</b></div>
<%
	for (TodoDto doing : doingList) {
%>

	<div class="content">
		<div class="contentTitle"><b><%= doing.getTitle() %></b></div>
		<div class="restContent">
			등록날짜: <%= doing.getRegdate() %>, <%= doing.getName() %>, 우선순위<%= doing.getSequence() %>
			<form action="/todo/todotype" method="post">
				<input type="hidden" value=<%= doing.getType() %> name="currentType">
				<input type="hidden" value=<%= doing.getId() %> name="currentId">
				<input type="submit" value="→">
			</form>
		</div>
	</div>
		
<%
	}
%>
</div>

<div id="done">
	<div id="doneTitle"><b>DONE</b></div>
<%
	for (TodoDto done : doneList) {
%>

	<div class="content">
		<div class="contentTitle"><b><%= done.getTitle() %></b></div>
		<div class="restContent">
			등록날짜: <%= done.getRegdate() %>, <%= done.getName() %>, 우선순위<%= done.getSequence() %>
		</div>
	</div>
		
<%
	}
%>
</div>
</div>
<script src="./script.js"></script>
</body>
</html>