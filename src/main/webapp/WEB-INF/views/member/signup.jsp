<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap" rel="stylesheet">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<title>회원가입</title>
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
			background: url('/images/밤.gif') no-repeat center;
			background-size: cover;
		}

		.header,
		.footer {
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
			text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 3px 0 black;
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
			text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 5px 0 black, 0 3px 0 black;
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
			text-shadow: 1px 1px 2px rgba(230, 230, 230, 0.7);
		}

		legend {
			font-size: 17px;
			font-weight: bold;
			color: #dfedb6;
			padding: 5px;
			text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 3px 0 black;
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

		.buttons button,
		.postcode-btn,
		.id-check-btn {
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

		.buttons button:first-child,
		.id-check-btn {
			background: #b4c28a;
			color: black;
		}

		.buttons button:first-child:hover,
		.id-check-btn:hover {
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
				<ul class="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/game/list.do?id=800001">Game</a></li>
					<li><a href="/board/list.do">Board</a></li>
					<c:choose>
						<c:when test="${member.role == 'admin'}">
							<li><a href="/service/admin/main.do">Service</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/service/qna/addForm.do">Service</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="headline">
			<h2>회원가입</h2>
		</div>
		<form action="/member/add.do" id="signupForm" method="post">
			<input type="hidden" name="csrfToken" value="${csrfToken}"/>
			<fieldset>
				<legend> * 아이디 / 패스워드</legend>
				<div class="input-group">
					<input type="text" name="id" id="id" placeholder="8~20자 이내 영어소문자,숫자를 포함한 ID를 입력해주세요">
				</div>
				<span id="result_id"></span><input type="password" name="pw" id="pw"
					placeholder="8자 이상의 영어소문자,숫자를 포함한 PW를 입력해주세요"> <span id="result_pw"></span> <input type="password"
					name="pwr" id="pwr" placeholder="패스워드를 다시 입력하세요"> <span id="result_pwr"></span>
			</fieldset>

			<fieldset>
				<legend> * 이름 / 닉네임 / 주민번호 / 전화번호 / 이메일</legend>
				<input type="text" name="name" id="name" placeholder="이름을 입력하세요">
				<span id="result_name"></span> <input type="text" name="nickName" id="nickName"
					placeholder="닉네임을 입력하세요"> <span id="result_nickName"></span>
				<div class="input-group">
					<input type="text" name="ssnFront" id="ssnFront" placeholder="주민등록번호 앞자리" maxlength="6">
					<span>-</span> 
					<input type="text" name="ssnBack" id="ssnBack" placeholder="주민등록번호 뒷자리" maxlength="1">
				</div>
				<span id="result_ssn"></span>
				<input type="text" name="phone" id="phone" placeholder="전화번호를 입력하세요">
				<span id="result_phone"></span> <input type="text" name="email" id="email" placeholder="이메일을 입력하세요">
				<span id="result_email"></span>
			</fieldset>

			<fieldset>
				<legend>주소</legend>
				<div class="input-group">
					<input type="text" name="postcode" id="postcode" placeholder="우편번호" readonly>
					<button type="button" class="postcode-btn" id="searchbnt">우편번호
						검색</button>
				</div>
				<input type="text" name="address1" id="address1" placeholder="주소를 입력하세요" readonly> <input type="text"
					name="address2" id="address2" placeholder="상세주소를 입력하세요">
			</fieldset>
			<input type="text" placeholder="       * 는 필수입력사항 입니다." readonly
				style="background:#cdcdcd; width: 50%; border:5px solid white;">

			<div class="buttons">
				<button type="submit">가입하기</button>
				<button type="reset">초기화</button>
			</div>
		</form>
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>


	<script>
		$("#signupForm").on("keydown",function (event) {
				if (event.key === "Enter" || event.keyCode === 13
					|| event.which === 13) {
					return false;
				}
		});
		$("#ssnFront").on("keyup", function () {
			validateInput("#ssnFront", /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])$/, 
						"사용 가능한 주민번호 입니다.", "사용 불가능한 주민번호 입니다.", "#result_ssn", function(valid) {
				ssn_val = valid;
			});
		});	
		$("#ssnBack").on("input", function() {
		    let val = $(this).val().replace(/\D/g, "");
		    $(this).val(val);
		});		

		
		//다음POST API
		$("#searchbnt").on("click", function () {
			new daum.Postcode({
				oncomplete: function (data) {
					$("#postcode").val(data.zonecode);
					$("#address1").val(data.roadAddress);
					$("#address2").focus();
				}
			}).open();
		});
		//회원가입 정규식 유효성 검사v2
		function validateInput(selector, regex, validMsg, invalidMsg, resultSelector, callback) {
			const value = $(selector).val();
			const isValid = regex.test(value);
			if (!isValid) {
				$(resultSelector).css({ "color": "#BB3A48", "font-size": "16px" }).html(invalidMsg);
				if (callback) callback(false);
				return false;
			}
			$(resultSelector).css({ "color": "green", "font-size": "16px" }).html(validMsg);
			if (callback) callback(true);
			return true;
		}
		function checkDupl(field, value, resultSelector, existMsg, availableMsg, callback) {
			$.ajax({
				url: "/member/valueCheck.do",
				data: { field: field, value: value },
				method: "GET",
				dataType: "text"
			}).done(function (resp) {
				if (resp.trim() === "exist") {
					$(resultSelector).css({ "color": "#BB3A48", "font-size": "16px" }).html(existMsg);
					callback(false);
				} else {
					$(resultSelector).css({ "color": "green", "font-size": "16px" }).html(availableMsg);
					callback(true);
				}
			}).fail(function (xhr, status, error) {
				console.error("AJAX 요청 실패:", error);
				callback(false);
			});
		}
		
		$("#id").on("keyup", function () {
			if (validateInput("#id", /^(?=.*[a-z])(?=.*\d)[a-z0-9_]{8,20}$/, "사용가능한 ID입니다.", 
							  "ID는 영어소문자,숫자 8자리이상 20자리이하로 작성해주세요.", "#result_id", function(valid) {
				if (valid) {
					checkDupl("login_id", $("#id").val(), "#result_id", 
									"이미 사용중인 ID입니다", "사용가능한 ID입니다.", function(isAvailable) {
						id_val = isAvailable;
					});
				} else {
					id_val = false;
				}
			}));
		});		
		$("#pw").on("keyup", function () {
			validateInput("#pw", /^(?=.*[a-z])(?=.*\d)[A-Za-z0-9_]{8,}$/, 
						"사용가능한 PW 입니다.", "사용불가능한 PW입니다.", "#result_pw", function(valid) {
				pw_val = valid;
			});
		});

		$("#pwr").on("keyup", function () {
			const pw = $("#pw").val();
			const pwr = $(this).val();
			if (pw === pwr) {
				$("#result_pwr").css({"color": "green", "font-size": "16px"}).html("패스워드 일치!");
				pw_val = true;
			} else {
				$("#result_pwr").css({"color": "#BB3A48", "font-size": "16px"}).html("패스워드 일치하지 않음!");
				pw_val = false;
			}
		});		
		$("#name").on("keyup", function () {
			validateInput("#name", /^[가-힣]{2,5}$/, 
						"사용 가능한 이름 입니다.", "사용 불가능한 이름입니다.", "#result_name", function(valid) {
				name_val = valid;
			});
		});
		$("#nickName").on("keyup", function () {
			const nickNameVal = $(this).val();
			if (nickNameVal === "") {
				$("#result_nickName").html("");
				return;
			}
			checkDupl("nickname", nickNameVal, "#result_nickName", 
							"이미 사용중인 닉네임입니다.", "사용가능한 닉네임입니다.", function(isAvailable) {
				nickName_val = isAvailable;
			});
		});		
		
		$("#phone").on("keyup", function () {
			if (validateInput("#phone", /^010[ -]?\d{4}[ -]?\d{4}$/, 
							"사용가능한 전화번호입니다.", "사용 불가능한 전화번호입니다.", "#result_phone", function(valid) {
				if (valid) {
					checkDupl("phone", $("#phone").val(), "#result_phone", 
									"이미 사용중인 전화번호입니다.", "사용가능한 전화번호입니다.", function(isAvailable) {
						tel_val = isAvailable;
					});
				} else {
					tel_val = false;
				}
			}));
		});	
		$("#email").on("keyup", function () {
			if (validateInput("#email", /^[A-Za-z0-9_]+@[A-Za-z0-9]+\.[a-zA-Z]{3,4}$/, 
							"", "사용 불가능한 이메일입니다.", "#result_email", function(valid) {
				if (valid) {
					checkDupl("email", $("#email").val(), "#result_email", 
									"이미 사용중인 이메일입니다.", "", function(isAvailable) {
						email_val = isAvailable;
						// 이메일이 유효하고 중복이 아니라면 결과 메시지를 비움
						if(isAvailable){
							$("#result_email").html("");
						}
					});
				} else {
					email_val = false;
				}
			}));
		});
		//회원가입 submit 전 유효성 검사
		$("#signupForm").on("submit", function (event) {
			let inputField = [
				{ selector: "#id", message: "ID는 필수 입력사항입니다." },
				{ selector: "#pw", message: "PW는 필수 입력사항입니다." },
				{ selector: "#pwr", message: "PW는 필수 입력사항입니다." },
				{ selector: "#name", message: "이름은 필수 입력사항입니다." },
				{ selector: "#ssnFront", message: "주민등록번호는 필수 입력사항입니다." },
				{ selector: "#ssnBack", message: "주민등록번호는 필수 입력사항입니다." },
				{ selector: "#phone", message: "전화번호는 필수 입력사항입니다." },
				{ selector: "#email", message: "이메일은 필수 입력사항입니다." }
			];
			for (let i = 0; i < inputField.length; i++) {
				let field = $(inputField[i].selector);
				if (!field.val()) {
					alert(inputField[i].message);
					field.focus();
					return false;
				}
			}
			if (!(id_val && pw_val && name_val && tel_val
				&& email_val && nickName_val && ssn_val)) {
				alert("입력한 값 중 유효하지 않은 항목이 있습니다. 다시 확인해주세요.");
				return false;
			}
			for(let i = 0; i < fields.length; i++) {
				if(!fields[i].val) {
					return false;
				}
			}
		});
	</script>
</body>

</html>