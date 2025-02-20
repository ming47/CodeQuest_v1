<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- ✅ 세션에서 로그인 정보 확인 --%>

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

	<%-- ✅ 로그인 상태에 따라 로그박스를 표시 --%>
	<c:if test="${not empty sessionLoginId}">
		<div class="logbox">
			<span>${sessionLoginId} 님 환영합니다</span>

			<div class="logbox-buttons">
				<a href="/member/mypage.do">마이페이지</a> 
				<a href="/member/logout.do">로그아웃</a>
			</div>
			<div class="logbox-time">
				총 접속시간: <span id="sessionTime">0초</span>
			</div>
		</div>
	</c:if>

	<script>
    $(document).ready(function() {
        // ✅ 로그인 후 sessionStorage에 시간 저장
        if (!sessionStorage.getItem("login-start-time")) {
            sessionStorage.setItem("login-start-time", new Date().getTime());
        }

        function updateSessionTime() {
            let startTime = sessionStorage.getItem("login-start-time");

            // ✅ 저장된 값이 없을 경우 기본값 설정 (새로운 로그인으로 간주)
            if (!startTime) {
                sessionStorage.setItem("login-start-time", new Date().getTime());
                startTime = sessionStorage.getItem("login-start-time");
            }

            let elapsedTime = Math.floor((new Date().getTime() - startTime) / 1000); // 초 단위 변환

            let hours = Math.floor(elapsedTime / 3600);
            let minutes = Math.floor((elapsedTime % 3600) / 60);
            let seconds = elapsedTime % 60;

            let timeString = `${hours}시간 ${minutes}분 ${seconds}초`;
            $("#sessionTime").text(timeString);
        }

        // ✅ 1초마다 접속 시간 업데이트
        setInterval(updateSessionTime, 1000);
        updateSessionTime();
    });
	</script>

</body>
</html>
