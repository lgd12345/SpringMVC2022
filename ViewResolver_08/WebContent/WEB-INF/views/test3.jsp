<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test3</h1>
	<!-- 리퀘스트랑 같다고 보면됨 requestScope 생략가능-->
	<h3>data1 : ${data1 }</h3>
	<h3>data2 : ${requestScope.data2 }</h3>
</body>
</html>