<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<title>회원가입</title>
<style>
* {
	box-sizing: border-box;
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
}

body {
	background: #f4f7f8;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 460px;
	background: white;
	padding: 25px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	text-align: center;
}

.headline h2 {
	font-size: 22px;
	font-weight: bold;
	color: dodgerblue;
	margin-bottom: 15px;
}

fieldset {
	border: 2px solid dodgerblue;
	border-radius: 6px;
	padding: 15px;
	margin-bottom: 15px;
	text-align: left;
}

legend {
	font-size: 16px;
	font-weight: bold;
	color: dodgerblue;
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
}

input:focus {
	border: 1px solid dodgerblue;
	outline: none;
	box-shadow: 0 0 5px rgba(30, 144, 255, 0.5);
}

input[disabled] {
	background: #e9ecef;
}

.buttons {
	display: flex;
	justify-content: space-between;
	margin-top: 15px;
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
}

.buttons button:first-child, .id-check-btn {
	background: dodgerblue;
	color: white;
}

.buttons button:first-child:hover, .id-check-btn:hover {
	background: #0056b3;
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
	background: dodgerblue;
	color: white;
	padding: 12px;
	font-size: 14px;
	border-radius: 6px;
	border: none;
	cursor: pointer;
	white-space: nowrap;
	transition: 0.3s;
}

.postcode-btn:hover {
	background: #0056b3;
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

#idi {
	flex: 3;
}

.id-check-btn {
	flex: 1;
	padding: 10px;
	font-size: 14px;
	border-radius: 6px;
	white-space: nowrap;
}

.input-group {
	display: flex;
	gap: 10px;
	align-items: center;
	justify-content: space-between;
	width: 100%;
}

#idi {
	flex: 3;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
}

.id-check-btn {
	flex: 1;
	padding: 12px;
	font-size: 14px;
	background: dodgerblue;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background 0.3s, transform 0.1s;
	white-space: nowrap;
}

.id-check-btn:hover {
	background: #0056b3;
	transform: scale(1.05);
}

@media ( max-width : 480px) {
	.input-group {
		flex-direction: column;
		gap: 5px;
	}
	.id-check-btn {
		width: 100%;
		padding: 10px;
	}
}
</style>
</head>

<body>
	<div class="container">
		<div class="headline">
			<h2>회원가입</h2>
		</div>
		<form action="/member/add.do" id="signupForm" method="post">
			<fieldset>
				<legend>아이디 / 패스워드</legend>
				<div class="input-group">
					<input type="text" name="id" id="id" placeholder="아이디를 입력하세요">
					<button type="button" class="id-check-btn" id="idchecker">ID 중복 체크</button>
				</div>
				<span id="idfinder"></span>
				<input type="password" name="pw" id="pw" placeholder="패스워드를 입력하세요">
				<input type="password" name="pwr" id="pwr" placeholder="패스워드를 다시 입력하세요">
			</fieldset>

			<fieldset>
				<legend>이름 / 전화번호 / 이메일</legend>
				<input type="text" name="name" id="name" placeholder="이름을 입력하세요">
				<div class="input-group">
				    <input type="text" name="ssnFront" id="ssnFront" placeholder="주민등록번호 앞자리" maxlength="6">
				    <span>-</span>
				    <input type="text" name="ssnBack" id="ssnBack" placeholder="주민등록번호 뒷자리" maxlength="1">
				</div>
				<input type="text" name="phone" id="phone" placeholder="전화번호를 입력하세요"> 
				<input type="text" name="email" id="email" placeholder="이메일을 입력하세요">
			</fieldset>

			<fieldset>
				<legend>주소</legend>
				<div class="input-group">
					<input type="text" name="postcode" id="postcode" placeholder="우편번호" readonly>
					<button type="button" class="postcode-btn" id="searchbnt">우편번호 검색</button>
				</div>
				<input type="text" name="address1" id="address1" placeholder="주소를 입력하세요" readonly> 
				<input type="text" name="address2" id="address2" placeholder="상세주소를 입력하세요">
			</fieldset>

			<div class="buttons">
				<button type="submit">가입하기</button>
				<button type="reset">초기화</button>
			</div>
		</form>
	</div>

	<script>
		// Enter 키 입력 방지
		document.getElementById("signupForm").addEventListener("keydown",
				function(event) {
					if (event.key === "Enter") {
						event.preventDefault();
					}
				});

		// ID 중복 체크 (AJAX 활용)
		$(document).ready(function() {
			$("#idchecker").on("click", function() {
				var userId = $("#id").val().trim();

				if (userId === "") {
					alert("아이디를 입력하세요!");
					return;
				}
				$.ajax({
				    url: "/member/idCheck.do",
				    data: { id: $("#id").val() },
				    method:"GET",
				    dataType:"text"
				}).done(function(resp) {
				    console.log("서버 응답:", resp);
				    if (resp.trim() === "exist") {
				        $("#idfinder").html("이미 사용중인 ID입니다").css("color", "red");
				    } else {
				        $("#idfinder").html("사용 가능한 ID입니다").css("color", "green");
				    }
				}).fail(function(xhr, status, error) {
				    console.error("AJAX 요청 실패:", error);
				});
			});
		    $("#ssnFront").on("input", function() {
		        let val = $(this).val().replace(/\D/g, "");
		        $(this).val(val);
		        if (val.length === 6) {
		            $("#ssnBack").focus();
		        }
		    });
			
			
		});
		document.getElementById("searchbnt").onclick = function() {
			new daum.Postcode({
				oncomplete : function(data) {
					document.getElementById("postcode").value = data.zonecode;
					document.getElementById("address1").value = data.roadAddress;
					document.getElementById("address2").focus();
				}
			}).open();
		};
	</script>
</body>

</html>