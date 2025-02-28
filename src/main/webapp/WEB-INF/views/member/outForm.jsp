<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<html>

<head>
	<meta charset="UTF-8">
	<title>회원탈퇴</title>
	<style>
		@font-face {
		    font-family: 'DungGeunMo';
		    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
		body {
    		font-family: 'DungGeunMo';
			background: #f8f9fa;
			margin: 0;
			padding: 0;
			display: flex;
			align-items: center;
			justify-content: center;
			height: 100vh;
		}

		.pw-container {
			background: #ffffff;
			width: 360px;
			border-radius: 10px;
			box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
			padding: 30px;
			text-align: center;
		}

		.pw-container h2 {
			font-size: 24px;
			margin-bottom: 10px;
		}

		.pw-container p {
			font-size: 14px;
			color: #555;
			margin-bottom: 20px;
		}

		.pw-container input[type="text"] {
			width: 80%;
			padding: 12px;
			margin-bottom: 20px;
			font-size: 14px;
			border: 1px solid #ddd;
			border-radius: 6px;
			outline: none;
		}

		.pw-container input[type="text"]:focus {
			border-color: #b4c28a;
			box-shadow: 0 0 5px rgba(180, 194, 138, 0.3);
		}

		.pw-container button {
			width: 80%;
			background: #b4c28a;
			color: #ffffff;
			font-size: 16px;
			border: none;
			padding: 12px;
			border-radius: 6px;
			cursor: pointer;
			transition: background 0.3s;
		}

		.pw-container button:hover {
			background: #346e30;
		}

		.pw-container input[type="password"] {
			width: 80%;
			padding: 12px;
			margin-bottom: 20px;
			font-size: 14px;
			border: 1px solid #ddd;
			border-radius: 6px;
			outline: none;
		}

		.pw-container input[type="password"]:focus {
			border-color: #b4c28a;
			box-shadow: 0 0 5px rgba(180, 194, 138, 0.3);
		}
		#loading {
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background: #ffffff;
		    z-index: 9999;
		    display: none;
		}
		#loading img {
		    width: 100%;
		    height: 100%;
		    object-fit: cover;
		    opacity : 0.5;
		}
	</style>
</head>

<body>
	<c:choose>
		<c:when test="${authEmail == null}">
			<div class="pw-container">
				<h2>회원탈퇴</h2>
				<p>회원탈퇴할 이메일을 입력해주세요</p>
				<form action="/member/sendResetEmail.do" method="post" id="frm">
					<input type="hidden" name="csrfToken" value="${csrfToken}"/>
					<input type="text" name="email" id="email" placeholder="탈퇴를 진행할 이메일을 작성해주세요."> <br>
					<span id="result_email"></span>
					<button id="mailsend" type="button">인증 메일 전송</button>
					<input type="hidden" name="emailDupli" id="emailDupli" value="${member.email}">
					<input type="hidden" name="member_out" value="true">
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="pw-container">
				<h2>회원탈퇴</h2>
				<p>전송된 인증 암호를 입력해주세요</p>
				<input type="text" name="auth" id="auth" placeholder="인증 코드를 입력해주세요.">
				<input type="hidden" name="authCode" id="authCode" value="${authCode}">
				<br>
				<span id="result_auth"></span>
				<button id="pwReset">탈퇴진행</button>
			</div>
			<script>
                $(window).on("load", function(){
                    $("#loading").fadeOut();
                });
            </script>
		</c:otherwise>
	</c:choose>
	<div id="loading"> <img src="/images/loading.gif" alt="Loading..."> </div>

	<script>
		let email_val = false;
		let auth_val = false;
		let dupl_email = false;

		$("#email").on("keyup", function () {
            if ($("#email").val() !== $("#emailDupli").val()) {
                $("#result_email").css({
                    "color": "red",
                    "font-size": "12px"
                }).html("입력한 이메일이 현재 로그인한 계정과 일치하지 않습니다.");
                email_val = false;
                return;
            }
			$.ajax({
				url: "/member/valueCheck.do",
				data: {
					field: "email",
					value: $("#email").val()
				},
				method: "GET",
				dataType: "text"
			}).done(function (resp) {
				if (resp == "exist") {
					$("#result_email").html("");
					email_val = true;
				} else {
					$("#result_email").css({
						"color": "red",
						"font-size": "12px"
					}).html("일치하는 이메일이 없습니다.");
					email_val = false;
				}
				
			}).fail(function (xhr, status, error) {
				console.error("AJAX 요청 실패:", error);
				email_val = false;
			});
		});

		$("#mailsend").on("click", function () {
			if (email_val == false) {
				alert("일치하는 이메일이 없어 인증메일을 전송할 수 없습니다.");
				return false;
			}
			$.ajax({
				url: "/member/emailDuplCheck.do",
				data: {
					email: $("#email").val()
				},
				method: "GET",
				dataType: "text"
			}).done(function (resp) {
				$("#loading").show(); 
				$('#frm').submit();
			}).fail(function (xhr, status, error) {
				console.error("AJAX 요청 실패:", error);
			});
		});

		$("#auth").on("focusout", function () {
			if ($("#auth").val() == $("#authCode").val()) {
				$("#result_auth").css({
					"color": "green",
					"font-size": "14px"
				}).html("인증코드가 일치합니다.");
				auth_val = true;
			} else {
				$("#result_auth").html("");
				auth_val = false;
			}
		});

		$("#pwReset").on("click", function () {
			if (auth_val == false) {
				alert("인증코드가 일치하지 않습니다.");
				return false;
			}
			if(confirm("정말 탈퇴를 진행하시겠습니까?") == true) {
				window.location.href = "/member/out.do?id=" + ${member.memberId};
			}
		});
	</script>
</body>

</html>