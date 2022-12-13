<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 모든 요청을 동일하게 할 수 있도록 root변수를 만들어줌 : localhost:8090/MiniProject/-->
<c:set var='root' value="${pageContext.request.contextPath }/" />

<!-- 상단 메뉴 부분 -->
<nav
	class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
	<a class="navbar-brand" href="${root }">🚀busanIT</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navMenu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navMenu">
		<ul class="navbar-nav">
			<!-- 인터셉터에서 넘겨받은 정보를 가지고 반복문으로 메뉴 구성할 것임 -->
			<!-- 인덱스번호를 파라메터로 넣어줌 -->
			<c:forEach var="menu" items="${topMenuList }">
				<li class="nav-item"><a
					href="${root }board/main?board_info_idx=${menu.board_info_idx }"
					class="nav-link">${menu.board_info_name }</a></li>
			</c:forEach>
		</ul>

		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a href="${root}user/login"
				class="nav-link">로그인</a></li>
			<li class="nav-item"><a href="${root}user/join" class="nav-link">회원가입</a>
			</li>
			<li class="nav-item"><a href="${root}user/modify"
				class="nav-link">정보수정</a></li>
			<li class="nav-item"><a href="${root}user/logout"
				class="nav-link">로그아웃</a></li>
		</ul>
	</div>
</nav>