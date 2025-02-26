<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<title>logbox finished</title>
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
	color: #ecfacf;
	text-decoration: none;
	background: #717171;
	padding: 5px 10px;
	font-size: 17px;
	border-radius: 5px;
}

.logbox-buttons a:hover {
	background: #3c3b39;
	color: white;
}

.logbox-time {
	margin-left: auto;
	padding-left: 20px;
}
</style>
</head>
<body>

	<c:if test="${not empty member}">
		<script>
			if (!sessionStorage.getItem("login-start-time")) {
				sessionStorage
						.setItem("login-start-time", new Date().getTime());
			}
		</script>
	</c:if>


	<c:if test="${not empty member}">
		<div class="logbox">
			<span>${member.nickName} 님 환영합니다</span>
			<div class="logbox-buttons">
				<a href="/member/mypage.do">마이페이지</a> <a href="/member/logout.do"
					id="logoutBtn">로그아웃</a>
			</div>
			<div class="logbox-time">
				총 접속시간: <span id="sessionTime">0초</span>
			</div>
		</div>
	</c:if>

	<script>
		$(document)
				.ready(
						function() {
							function updateSessionTime() {
								let startTime = sessionStorage
										.getItem("login-start-time");

								if (!startTime) {
									$("#sessionTime").text("0초");
									return;
								}

								let elapsedTime = Math.floor((new Date()
										.getTime() - startTime) / 1000);
								let hours = Math.floor(elapsedTime / 3600);
								let minutes = Math
										.floor((elapsedTime % 3600) / 60);
								let seconds = elapsedTime % 60;

								let timeString = hours + '시간 ' + minutes + '분 '
										+ seconds + '초';
								$("#sessionTime").text(timeString);
							}

							if (sessionStorage.getItem("login-start-time")) {
								setInterval(updateSessionTime, 1000);
								updateSessionTime();
							}

							$("#logoutBtn").click(function() {
								sessionStorage.removeItem("login-start-time");
								location.reload();
							});
						});
	</script>

</body>
</html>
