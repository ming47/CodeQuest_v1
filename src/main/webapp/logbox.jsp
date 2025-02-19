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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<title>logbox</title>
<style>
.logbox {
	display: flex;
	width: 100%;
	background: #dddddd;
	color: black;
	padding: 5px 15px;
	font-size: 20px;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	border-radius: 10px;
}

.logbox-buttons a {
	margin-left: 15px;
	color: #d2ddaf;
	text-decoration: none;
	background: #717171;
	padding: 5px 10px;
	border-radius: 5px;
	font-size: 17px;
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
		style="display: ${not empty member.loginId ? 'flex' : 'none'};">
		<span>${not empty member.loginId ? member.loginId.concat(' 님 환영합니다') : ''}</span>

		<div class="logbox-buttons">
			<a href="/member/mypage.do">마이페이지</a> <a href="/member/logout.do">로그아웃</a>

		</div>
		<div class="logbox-time">
			접속시간: 00시 00분 <span id="loginTime"></span>
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