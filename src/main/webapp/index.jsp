<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <title>Responsive Game Portal</title>
    <style>
        /* 기본 스타일 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        body {
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
=======
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel ="preconnect " href ="https: //fonts.googleapis.com "> <link rel
	="preconnect " href ="https: //fonts.gstatic.com " crossorigin> <link href
	="https: //fonts.googleapis.com /css2 ?family =Jua &family =Press+Start+2P
	&display =swap " rel ="stylesheet ">
<title>Responsive Game Portal</title>
<style>
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
>>>>>>> de815ab4a5dc5c729443b5e3c412919bb9fb555e

body {
	background-color: #2b2b27;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

/* 컨테이너 */
.container {
	width: 100%;
	max-width: 1660px;
	height: 100%;
	max-height: 1024px;
	display: flex;
	flex-direction: column;
	align-items: center;
	background: white;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
	border-radius: 10px;
}

/* Header & Footer */
.header, .footer {
	width: 100%;
	height: 80px;
	background: #dedbd1;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 20px;
	color: white;
	font-weight: bold;
}

/* 네비게이션 */
.navi {
	display: flex;
	align-items: center;
	width: 100%;
	justify-content: space-between;
}

.logo {
	font-size: 24px;
	color: #5c594c;
}

.navi ul {
	list-style: none;
	display: flex;
	gap: 20px;
}

.navi ul li {
	padding: 10px 15px;
	background: white;
	color: #af8652;
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s ease-in-out;
}

.navi ul li:hover {
	background: #453724;
	color: white;
}

/* 로그인 정보 */
.logbox {
	display: none;
	padding: 10px;
	font-weight: bold;
	color: white;
}

/* 본문 */
.body {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px;
	gap: 5%;
}

/* 게임 리스트 (가로 스크롤 복구) */
.gameList {
	display: flex;
	overflow-x: auto;
	gap: 20px;
	padding: 10px;
	width: 70%;
	scroll-snap-type: x mandatory;
	white-space: nowrap;
	min-height: 320px;
}

.gameList::-webkit-scrollbar {
	height: 8px;
}

.gameList::-webkit-scrollbar-thumb {
	background: #007bff;
	border-radius: 10px;
}

.gameList::-webkit-scrollbar-track {
	background: #ddd;
	border-radius: 10px;
}

.game {
	min-width: 280px;
	background: white;
	padding: 15px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	scroll-snap-align: start;
	transition: transform 0.3s ease-in-out;
}

.game:hover {
	transform: scale(1.05);
}

.game img {
	width: 100%;
	height: 160px;
	border-radius: 10px;
}

.game button {
	width: 100%;
	padding: 10px;
	background-color: #8a8676;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
}

.game button:hover {
	background: #453724;
}

/* 로그인 박스 */
.loginbox {
	width: 15%;
	background: #dedbd1;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	min-height: 320px;
}

.loginbtn:hover{
background : #919190
}

/* 게시판 */
.boardlist {
	width: 60%;
	background: #f5f3ed;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<!-- 헤더 -->
		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul>
					<li>Home</li>
					<li>Game</li>
					<li>Board</li>
					<li>Service</li>
				</ul>
			</div>
			<%@ include file="logbox.jsp"%>
		</div>

		<!-- 본문 -->
		<div class="body">
			<div class="gameList">
				<div class="game">
					<img src="game1.jpg">
					<h3>Game 1</h3>
					<p>RPG</p>
					<button>Play</button>
				</div>
				<div class="game">
					<img src="game2.jpg">
					<h3>Game 2</h3>
					<p>FPS</p>
					<button>Play</button>
				</div>
				<div class="game">
					<img src="game3.jpg">
					<h3>Game 3</h3>
					<p>Action</p>
					<button>Play</button>
				</div>
				<div class="game">
					<img src="game4.jpg">
					<h3>Game 4</h3>
					<p>Adventure</p>
					<button>Play</button>
				</div>
				<div class="game">
					<img src="game5.jpg">
					<h3>Game 5</h3>
					<p>Strategy</p>
					<button>Play</button>
				</div>
				<div class="game">
					<img src="game6.jpg">
					<h3>Game 6</h3>
					<p>Sports</p>
					<button>Play</button>
				</div>
			</div>

			<div class="loginbox">
				<h2>로그인</h2>
				<input type="text" id="userId" placeholder="아이디"> <input
					type="password" id="userPw" placeholder="비밀번호">
				<button id="loginBtn" style="background:#5e5d5a">로그인</button>
				<div class="login-links">
					<a href="#">회원가입</a> <a href="#">ID/PW 찾기</a>
				</div>
				<div class="social-login">
					<button class="kakao">🟡 Kakao 로그인</button>
				</div>
			</div>
		</div>

		<div class="boardlist">
			<h3>📢 최근 게시물</h3>
			<ul>
				<li>게시글 1</li>
				<li>게시글 2</li>
				<li>게시글 3</li>
			</ul>
		</div>

		<!-- 푸터 -->
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>

	<script>
		$(document).ready(function() {
			$("#loginBtn").click(function() {
				let userId = $("#userId").val();
				let userPw = $("#userPw").val();
				if (userId && userPw) {
					$(".logbox").fadeIn().find("#username").text(userId);
					$(".loginbox").fadeOut();
				} else {
					alert("아이디와 비밀번호를 입력하세요!");
				}
			});
		});
	</script>
</body>
</html>