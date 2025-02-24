<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입장 페이지</title>
<style>
/* 기존 스타일 유지 */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.starter {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	background: url('/images/메인.gif') no-repeat center center;
	background-size: cover;
	text-align: center;
	flex-direction: column;
}

h1 {
	font-size: 2rem;
	text-shadow: 2px 2px 8px rgba(0, 0, 0, 1), 0 0 12px rgba(0, 0, 0, 0.9),
		0 0 16px rgba(0, 0, 0, 0.8);
	font-family: "Press Start 2P", serif; 
	font-weight : 400; 
	font-style :normal;
	color: #eceec4;
}
.btn {
	width: 220px;
	height: 120px;
	padding: 15px 30px;
	color: white;
	background: url('/images/스타트.png') no-repeat center center;
	background-size: cover;
	border: none;
	border-radius: 10px;
	cursor: pointer;
	transition: transform 0.5s ease, box-shadow 0.5s ease;
}

.btn:hover {
	transform: scale(1.2);
}
</style>
</head>

<body>
		<div class="starter">
			<h1>Enter the World of Code Quest</h1>
			<button class="btn" onclick="enterGame()"></button>
		</div>

	<script>
		function enterGame() {
			sessionStorage.setItem("enteredGame", "true"); // 세션 저장
			$(".starter").fadeOut(500, function() {
				$(".container").fadeIn(500);
			});
		}
	</script>

</body>
</html>
