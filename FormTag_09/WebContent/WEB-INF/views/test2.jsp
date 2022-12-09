<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 폼 커스텀 태그를 활용하면 Model 객체에 들어있는 값을 form요소에 주입할 수 있습니다. (유효성..) -->
<!-- 회원정보 수정.. 등을 구성할 때 잘 사용됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test2</h1>
	<form:form modelAttribute="userDataBean" action='result'>
		이름 : <form:input path='user_name' />
		<br />
		아이디 : <form:input path='user_id' />
		<br />
		비밀번호 : <form:password path='user_pw' showPassword="true" />
		<br />
		우편번호 : <form:input path='user_postcode' />
		<br />
		주소1 : <form:input path='user_address1' />
		<br />
		주소2 : <form:input path='user_address2' />
		<br />
		<button type='submit'>확인</button>
	</form:form>
</body>
</html>