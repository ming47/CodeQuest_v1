<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<title>추가 정보입력</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.container {
	width: 100%;
	max-width: 1980px;
	height: 100%;
	max-height: 1200px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	text-align: center;
	background: url('/allback.jpg') no-repeat center;
	background-size: cover;
}

.header, .footer {
	background: #1e201d;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 20px;
	color: #b4c28a;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	width: 100%;
}

.header {
	height: 80px;
	padding: 20px;
	position: relative;
}

.footer {
	height: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
	margin-top: 40px;
}

.navi {
	display: flex;
	align-items: center;
	width: 100%;
	justify-content: space-between;
}

.logo {
	font-size: 20px;
}

.navi ul {
	list-style: none;
	display: flex;
	gap: 20px;
}

.navi ul li a {
	padding: 10px 15px;
	background: #717171;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 3px 0
		black;
	text-decoration: none;
}

.navi ul li:hover a {
	background: #3c3b39;
	color: white;
}

.headline h2 {
	font-size: 30px;
	font-weight: bold;
	color: white;
	margin-bottom: 20px;
	margin-top: 30px;
	font-family: "Jua", serif;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 9px 0 black, 0 3px 0
		black;
}

fieldset {
	width: 50%;
	border: 10px solid black;
	border-radius: 10px;
	margin: auto;
	padding: 15px;
	margin-bottom: 15px;
	text-align: left;
	font-family: "Jua", serif;
	background: linear-gradient(to right, #abaaaa, #212020);
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 5px 0 black, 0 3px 0
		black;
}

legend {
	font-size: 17px;
	font-weight: bold;
	color: #dfedb6;
	padding: 5px;
}

.input-group {
	display: flex;
	gap: 10px;
	align-items: center;
}

input {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
	transition: 0.3s;
	font-family: "Jua", serif;
}

input:focus {
	border: 4px solid black;
	outline: none;
	box-shadow: 0 0 5px rgba(30, 144, 255, 0.5);
}

input[disabled] {
	background: #e9ecef;
}

.buttons {
	display: flex;
	justify-content: space-between;
	width: 51%;
	margin: auto;
	margin-top: 20px;
}

.buttons button, .postcode-btn, .id-check-btn {
	flex: 1;
	padding: 12px;
	margin: 5px;
	border: none;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
	transition: 0.3s;
	font-family: "Jua", serif;
}

.buttons button:first-child, .id-check-btn {
	background: #b4c28a;
	color: black;
}

.buttons button:first-child:hover, .id-check-btn:hover {
	background: #6a6f5a;
	color: white;
}

.buttons button:last-child {
	background: lightgray;
	color: black;
}

.buttons button:last-child:hover {
	background: gray;
	color: white;
}

.postcode-btn {
	background: #d4e0af;
	color: black;
	padding: 12px;
	font-size: 14px;
	border-radius: 6px;
	border: none;
	cursor: pointer;
	white-space: nowrap;
	transition: 0.3s;
}

.postcode-btn:hover {
	background: #6a6f5a;
	color: white;
}

.error {
	border: 2px solid red;
	background-color: #ffecec;
}

.success {
	border: 2px solid green;
	background-color: #eaffea;
}

.id-check-container {
	display: flex;
	gap: 5px;
}

.input-group {
	display: flex;
	gap: 10px;
	align-items: center;
	justify-content: space-between;
	width: 100%;
}
</style>
</head>

<body>
	<div class="container">
		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul>
					<li align="center"><a href="/">Home</a></li>
					<li align="center"><a href="/">Game</a></li>
					<li align="center"><a href="/board/list.do">Board</a></li>
					<li align="center"><a href="/service/list.do">Service</a></li>
				</ul>
			</div>
		</div>

		<div class="headline">
			<h2>추가 정보입력</h2>
		</div>
		<form action="/member/easySignup.do" id="signupForm" method="post">
			<fieldset>
				<legend> * 이름 / 닉네임 / 주민번호 / 전화번호 / 이메일</legend>
				<input type="text" name="name" id="name" placeholder="이름을 입력하세요">
				<span id="result_name"></span> <input type="text" name="nickName"
					id="nickName" value=${nickName} placeholder="닉네임을 입력하세요."> </span>
				<div class="input-group">
					<input type="text" name="ssnFront" id="ssnFront"
						placeholder="주민등록번호 앞자리" maxlength="6"> <span>-</span> <input
						type="text" name="ssnBack" id="ssnBack" placeholder="주민등록번호 뒷자리"
						maxlength="1">
				</div>
				<input type="text" name="phone" id="phone" placeholder="전화번호를 입력하세요">
				<span id="result_phone"></span> <input type="text" name="email"
					id="email" value=${email} readonly></span>
			</fieldset>

			<fieldset>
				<legend>주소</legend>
				<div class="input-group">
					<input type="text" name="postcode" id="postcode" placeholder="우편번호"
						readonly>
					<button type="button" class="postcode-btn" id="searchbnt">우편번호
						검색</button>
				</div>
				<input type="text" name="address1" id="address1"
					placeholder="주소를 입력하세요" readonly> <input type="text"
					name="address2" id="address2" placeholder="상세주소를 입력하세요">
			</fieldset>
			<input type="text" placeholder="       * 는 필수입력사항 입니다." readonly
				style="background: #cdcdcd; width: 50%; border: 5px solid white;">

			<div class="buttons">
				<button type="submit">가입하기</button>
				<button type="reset">초기화</button>
			</div>
		</form>
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>


	<script>
		//Enter 키 입력 방지
		$("#signupForm").on(
				"keydown",
				function(event) {
					if (event.key === "Enter" || event.keyCode === 13
							|| event.which === 13) {
						event.preventDefault();
					}
				});
		$("#ssnFront").on("input", function() {
			let val = $(this).val().replace(/\D/g, "");
			$(this).val(val);
			if (val.length === 6) {
				$("#ssnBack").focus();
			}
		});
		//다음POST API
		$("#searchbnt").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$("#postcode").val(data.zonecode);
					$("#address1").val(data.roadAddress);
					$("#address2").focus();
				}
			}).open();
		});
		//회원가입 정규식 유효성 검사

		$("#name").on("keyup", function() {
			let regex = /^[가-힣]{2,5}$/;
			let vali = regex.exec($(this).val());
			if (vali == null) {
				$("#result_name").css({
					"color" : "red",
					"font-size" : "12px"
				}).html("사용 불가능한 이름입니다.");
				name_val = false;
			} else {
				$("#result_name").css({
					"color" : "green",
					"font-size" : "12px"
				}).html("사용 가능한 이름 입니다.");
				name_val = true;
			}
		});

		$("#nickName").on("keyup", function() {
			if ($("#nickName") == "") {
				$("#result_nickName").html("");
				return;
			}
			$.ajax({
				url : "/member/valueCheck.do",
				data : {
					field : "nickname",
					value : $("#nickName").val()
				},
				method : "GET",
				dataType : "text"
			}).done(function(resp) {
				if (resp.trim() === "exist") {
					$("#result_nickName").css({
						"color" : "red",
						"font-size" : "12px"
					}).html("이미 사용중인 닉네임입니다.");
					nickName_val = false;
				} else {
					$("#result_nickName").css({
						"color" : "green",
						"font-size" : "12px"
					}).html("사용가능한 닉네임입니다.");
					nickName_val = true;
				}
			}).fail(function(xhr, status, error) {
				console.error("AJAX 요청 실패:", error);
			});
		});

		$("#phone").on("keyup", function() {
			let regex = /^010[ -]?\d{4}[ -]?\d{4}$/;
			let vali = regex.exec($(this).val());
			if (vali == null) {
				$("#result_phone").css({
					"color" : "red",
					"font-size" : "12px"
				}).html("유효하지 않는 전화번호입니다.");
				tel_val = false;
			} else {
				$("#result_phone").css({
					"color" : "green",
					"font-size" : "12px"
				}).html("유효한 전화번호입니다.");
				$.ajax({
					url : "/member/valueCheck.do",
					data : {
						field : "phone",
						value : $("#phone").val()
					},
					method : "GET",
					dataType : "text"
				}).done(function(resp) {
					if (resp.trim() === "exist") {
						$("#result_phone").css({
							"color" : "red",
							"font-size" : "12px"
						}).html("이미 사용중인 전화번호입니다.");
						tel_val = false;
					} else {
						$("#result_phone").css({
							"color" : "green",
							"font-size" : "12px"
						}).html("사용가능한 전화번호입니다");
						tel_val = true;
					}
				}).fail(function(xhr, status, error) {
					console.error("AJAX 요청 실패:", error);
				});
			}
		});

		$("#email").on("keyup", function() {
			let regex = /^[A-Za-z0-9_]+@[A-Za-z0-9]+\.[a-zA-Z]{3,4}$/;
			let vali = regex.exec($(this).val());
			if (vali == null) {
				$("#result_email").css({
					"color" : "red",
					"font-size" : "12px"
				}).html("유효하지 않는 이메일입니다.");
				email_val = false;
			} else {
				$("#result_email").css({
					"color" : "green",
					"font-size" : "12px"
				}).html("유효한 이메일 입니다.");
				$.ajax({
					url : "/member/valueCheck.do",
					data : {
						field : "email",
						value : $("#email").val()
					},
					method : "GET",
					dataType : "text"
				}).done(function(resp) {
					if (resp.trim() === "exist") {
						$("#result_email").css({
							"color" : "red",
							"font-size" : "12px"
						}).html("이미 사용중인 이메일입니다.");
						email_val = false;
					} else {
						$("#result_email").css({
							"color" : "green",
							"font-size" : "12px"
						}).html("사용가능한 이메일입니다.");
						email_val = true;
					}
				}).fail(function(xhr, status, error) {
					console.error("AJAX 요청 실패:", error);
				});
			}
		});

		//회원가입 submit 전 유효성 검사
		$("#signupForm").on("submit",
						function(event) {
							if (!$("#name").val()) {
								alert("이름은 필수 입력사항입니다.");
								$("#name").focus();
								return false;
							} else if (!$("#ssnFront").val()) {
								alert("주민등록번호는 필수 입력사항입니다.");
								$("#ssnFront").focus();
								return false;
							} else if (!$("#ssnBack").val()) {
								alert("주민등록번호는 필수 입력사항입니다.");
								$("#ssnBack").focus();
								return false;
							} else if (!$("#phone").val()) {
								alert("전화번호는 필수 입력사항입니다.");
								$("#phone").focus();
								return false;
							} else if (!$("#email").val()) {
								alert("이메일은 필수 입력사항입니다.");
								$("#email").focus();
								return false;
							}

							if (!(name_val && tel_val && email_val && nickName_val)) {
								alert("입력한 값 중 유효하지 않은 항목이 있습니다. 다시 확인해주세요.");
								return false;
							}

						});
	</script>
</body>

</html>