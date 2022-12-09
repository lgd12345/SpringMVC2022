<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<!-- 이메일 : input type='text' name='email'
	이런 방식이면 틀린값이 없어져버림 틀린값을 받아오려면  -->
	<!-- 이메일 : form:input path="email"이렇게 적어야함 -->

	<h1>상품 입력</h1>
	<form:form action='input_product_proc' modelAttribute="product"
		method='post'>
		상품명 : <form:input path="name" />
		<br />
		<form:errors path='name' />
		<br />
		이메일 : <form:input path="email" />
		<br />
		<form:errors path='email' />
		<br />
		가격 : <form:input path="price" />
		<br />
		<form:errors path='price' />
		<br />

		<form:button type='submit'>확인</form:button>
	</form:form>
</body>
</html>
