<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
    <style>
        body {
            font-family: 'Jua', sans-serif;
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
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
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
        
    </style>
</head>
<body>
	<c:choose>
	<c:when test="${authEmail == null}">
    <div class="pw-container">
        <h2>비밀번호 재설정</h2>
        <p>비밀번호를 재설정할 이메일을 입력해주세요.</p>
        <form action="/member/sendResetEmail.do" method="post" id="frm">
            <input type="text" name="email" id="email" placeholder="재설정할 이메일을 작성해주세요."> <br>
            <span id="result_email"></span>
            <button id="mailsend">인증 메일 전송</button>
        </form>
    </div>
    </c:when>
    <c:otherwise>
	<div class="pw-container">
        <h2>비밀번호 재설정</h2>
        <p>전송된 인증 암호를 입력해주세요</p>
            <input type="text" name="auth" id="auth" placeholder="인증 코드를 입력해주세요.">
            <input type="hidden" name="authCode" id="authCode" value="${authCode}">
            <br>
            <span id="result_auth"></span>
        <form action="/member/pwReset.do" method="post" id="frm2">
            <input type="password" name="pw" id="pw" placeholder="8자 이상의 영어소문자,숫자를 포함한 PW를 입력해주세요">
            <input type="password" name="pwr" id="pwr" placeholder="패스워드를 다시 입력해주세요">
            <input type="hidden" name="resetEmail" id="resetEmail" value="${authEmail}">
            <button id="pwReset">완료</button>
        </form>
    </div>
    </c:otherwise>
    </c:choose>
    
	<script>
	let email_val = false;
	let auth_val = false;

	$("#email").on("keyup", function() {
	    $.ajax({
	        url : "/member/valueCheck.do",
	        data : {
	            field : "email",
	            value : $("#email").val()
	        },
	        method : "GET",
	        dataType : "text"
	    }).done(function(resp) {
	        if (resp == "exist") {
	        	$("#result_email").html("");
	            email_val = true;
	            
	        } else {
	            $("#result_email").css({
	                "color" : "red",
	                "font-size" : "12px"
	            }).html("일치하는 이메일이 없습니다.");
	            email_val = false;
	        }
	    }).fail(function(xhr, status, error) {
	        console.error("AJAX 요청 실패:", error);
	        email_val = false;
	    });
	});

	$("#frm").on("submit", function () {
	    if (email_val === false) {
	        alert("일치하는 이메일이 없어 인증메일을 전송할 수 없습니다.");
	        return false;
	    }
	});
	
	$("#auth").on("focusout", function() {
		console.log("인증코드는"+$("#authCode").val());
		if ($("#auth").val() == $("#authCode").val()) {
		    $("#result_auth").css({
		        "color": "green",
		        "font-size": "14px"
		    }).html("인증코드가 일치합니다.");
		    auth_val = true;
		}

	});
	
	$("#pw").on("keyup", function() {
		let regex = /^[A-Za-z0-9_]{8,}$/;
		let vali = regex.exec($(this).val());
		if (vali == null) {
			$("#result_pw").css({
				"color" : "red",
				"font-size" : "12px"
			}).html("유효하지 않는 PW입니다.");
			pw_val = false;
		} else {
			$("#result_pw").css({
				"color" : "green",
				"font-size" : "12px"
			}).html("유효한 PW 입니다.");
			pw_val = true;
		}
	});

	$("#pwr").on("keyup", function(e) {
		if ($("#pw").val() === $(this).val()) {
			$("#result_pwr").css({
				"color" : "green",
				"font-size" : "12px"
			}).html("패스워드 일치!");
			pwr_val = true;
		} else {
			$("#result_pwr").css({
				"color" : "red",
				"font-size" : "12px"
			}).html("패스워드 일치하지 않음!");
			pwr_val = false;
		}
	});
	$("#frm2").on("submit", function () {
	    if (pw_val == false) {
	        alert("패스워드가 유효하지 않습니다");
	        return false;
	    } else if(pwr_val == false) {
	        alert("패스워드가 일치하지 않습니다.");
	        return false;	
	    } else if(auth_val == false) {
	    	alert("인증코드가 일치하지 않습니다.");
	    	return false;
	    }
	    
	});
	
	
	</script>
</body>
</html>
