<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String sessionLoginId = (String) session.getAttribute("sessionLoginId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
.logbox {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	background: #007bff;
	color: white;
	padding: 10px 20px;
	font-weight: bold;
}

.logbox-buttons a {
	margin-left: 15px;
	color: white;
	text-decoration: none;
	background: #0056b3;
	padding: 5px 10px;
	border-radius: 5px;
}

.logbox-buttons a:hover {
	background: #003f7f;
}

.logbox-time {
	margin-left: auto;
	padding-left: 20px;
}
</style>
</head>
<body>
	<div class="logbox"
		style="display: ${not empty member.id ? 'flex' : 'none'};">
		<span>${not empty member.id ? member.id.concat(' 님 환영합니다') : ''}</span>
		<div class="logbox-buttons">
			<a href="mypage.jsp">마이페이지</a> <a href="logout.jsp">로그아웃</a>
		</div>
		<div class="logbox-time">
			접속시간: <span id="loginTime"></span>
		</div>
	</div>
	<script>
		// 현재 시간 표시 (JST 시간 기준)
		function updateTime() {
			let now = sessionStorage.getItem("login-time")
			let timeString = now.toLocaleTimeString();
			document.getElementById("loginTime").innerText = timeString;
		}
		setInterval(updateTime, 1000);
		updateTime();
	</script>
</body>
</html>