<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<title>Responsive Game Portal</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	display: flex;
	justify-content: center;
	height: 100vh;
	background: url('/allback.jpg') no-repeat center;
	background-size: cover;
	background-attachment: fixed;
	align-items: stretch;
}

.container {
	width: 100%;
	max-width: 1660px;
	height: 100%;
	max-height: 1024px;
	display: flex;
	flex-direction: column;
	align-items: center;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
	border-radius: 10px;
}

.header, .footer {
	width: 100%;
	height: 100px;
	pading:20px;
	background: #1e201d;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 20px;
	color: #b4c28a;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	font-size: 15px;
}

.navi {
	display: flex;
	align-items: center;
	width: 100%;
	justify-content: space-between;
}

.logo {
	font-size: 24px;
	color: #b4c28a;
}

.navi ul {
	list-style: none;
	display: flex;
}

.navi ul li {
	width: 130px;
	height:30px;
	padding: 10px 15px;
	background: white;
	color: #b4c28a;
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s ease-in-out;
	margin: 20px;
}

.navi ul li:hover {
	background: #81a5bf;
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


.gameList {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 20px;
	padding: 10px;
	width: 70%;
	white-space: nowrap;
	min-height: 320px;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	font-weight: 400;
}

.gameList h3 {
	font-size: 16px;
	margin: 10px;
}

.gameList p {
	font-size: 14px;
	margin: 10px;
}

.game {
	min-width: 280px;
	background: white;
	padding: 15px;
	border-radius: 10px;
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
	width: 170px;
	height: 60px;
	padding: 10px;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	background: url('/gamebtn.png') no-repeat center;
	background-size: cover;
	padding: 10px;
	background-size: cover;
}

.game button:hover {
	transform: scale(1.2);
}

.loginbox {
	width: 15%;
	height: 80%;
	background: url('/login.jpg') no-repeat center;
	background-size: 10 10;
	padding: 20px;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	min-height: 320px;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.loginbox h2 {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	color: #2f2b2b;
}

.loginbox input {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 5px;
}

.loginbox>loginBtn {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	font-size:80px;
	background: #919190;
	color:white;
}

.loginBtn:hover {
	background: #919190
}

.login-links {
	margin: 20px;
}

.boardlist {
	width: 60%;
	height:1200px;
	background: url('/board.jpg') no-repeat center;
	background-size: cover;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
	text-align: center;
	color:white;
	margin-bottom: 60px;
	margin-right: 300px;
}
</style>
</head>
<body>
	<div class="container">

		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul>
					<li align="center">Home</li>
					<li align="center">Game</li>
					<li align="center">Board</li>
					<li align="center">Service</li>
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
				<a href="/member/login.do"><button id="loginBtn">로그인</button> </a>
				<div class="login-links">
					<a href="/member/addForm.do">회원가입</a><br>
					<br> <a href="/member/findMember.do">ID/PW 찾기</a>
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
				let userId = $("#userId").val().trim();
				let userPw = $("#userPw").val().trim();

				if (userId === "" || userPw === "") {
					alert("아이디와 비밀번호를 입력하세요!");
					return false;
				}
				$.ajax({
					url : "/member/login.do",
					method : "POST",
					data : {
						id : userId,
						pw : userPw
					},
					dataType : "text"
				}).done(function(resp) {
					if (resp.trim() === "success") {
						$(".loginbox").fadeOut();
						$(".logbox-container").load("logbox.jsp", function() {
							$(".logbox").fadeIn();
						});
					} else {
						$("#loginResult").text("로그인 실패. 아이디/비밀번호를 확인하세요.");
					}
				}).fail(function(xhr, status, error) {
					console.log("로그인 AJAX 실패:", error);
				});
			});
		});
	</script>
	
	
	
</body>
</html>
