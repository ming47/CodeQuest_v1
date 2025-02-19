<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="EUC-KR">
    <title>마이페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap" rel="stylesheet">

    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            background: #f4f7f8;
            overflow-y: auto;
        }

        .header {
            width: 100%;
            height: 80px;
            background: #1e201d;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            color: #b4c28a;
            font-family: "Press Start 2P", serif;
            font-size: 15px;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .navi {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
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
            height: 30px;
            padding: 10px 15px;
            background: white;
            color: #b4c28a;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s ease-in-out;
            margin: 10px;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .navi ul li a {
            color: #b4c28a;
            text-decoration: none;
            width: 100%;
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .navi ul li:hover {
            background: #81a5bf;
            color: white;
        }

        .container {
            width: 100%;
            max-width: 1500px;
            height: 5500px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            margin: 100px auto;
            padding: 20px;
            display: flex;
            margin-left: 350px;

        }

        .main-content {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin-left: 20px;
        }

        .sidebar {
            width: 250px;
            height: 330px;
            background: #f4f4f4;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            left: 35px;
            position: fixed;
        }

        .sidebar h2 {
            font-size: 33px;
            color: black;
            font-weight: bold;
            margin-bottom: 15px;
            border-bottom: 2px solid black;
            padding-bottom: 10px;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            padding: 10px;
            font-size: 16px;
            border-bottom: 1px solid #ddd;
            cursor: pointer;
            transition: background 0.3s;
        }

        .sidebar ul li:hover {
            background: #ddd;
        }

        .content {
            flex-grow: 1;
            margin-left: 20px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            height: 600px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .content h2 {
            font-size: 40px;
            color: #333;
            margin-bottom: 10px;
        }

        fieldset {
            width: 65%;
            border: 3px solid #b4c28a;
            border-radius: 10px;
            padding: 15px;
            margin: auto;
            text-align: left;
            background: #fafafa;
        }

        legend {
            font-size: 35px;
            font-weight: bold;
            color: #b4c28a;
            padding: 0 10px;
        }

        .input-group {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
            padding: 10px 10px;
            gap: 10px;
        }


        .input-group label {
            font-size: 18px;
            font-weight: bold;
            color: #444;
            width: 100px;
            text-align: left;
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
            border: 1px solid #b4c28a;
            outline: none;
            box-shadow: 0 0 5px rgba(30, 144, 255, 0.5);
        }

        input[disabled] {
            background: #e9ecef;
        }

        .input-group input {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 20px;
            transition: 0.3s;
            width: 100%;
            text-align: left;
        }

        .input-group input:focus {
            border: 3px solid #b4c28a;
            outline: none;
            box-shadow: 0 0 5px rgba(180, 194, 138, 0.5);
        }

        .input-group input[disabled] {
            background: #e9ecef;
        }

        .buttons {
            display: flex;
            justify-content: space-between;
            width: 51%;
            margin: auto;
            margin-top: 15px;
        }

        .buttons button,
        .postcode-btn,
        .id-check-btn {
            flex: 1;
            padding: 12px;
            margin: 5px;
            border: none;
            border-radius: 6px;
            font-size: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .buttons button:first-child,
        .id-check-btn {
            background: #b4c28a;
            color: white;
        }

        .buttons button:first-child:hover,
        .id-check-btn:hover {
            background: #346e30;
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
        
    </style>
</head>

<body>
    <div class="header">
        <div class="navi">
            <div class="logo">Team CodeQuest</div>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/game">Game</a></li>
                <li><a href="#">Board</a></li>
                <li><a href="#">Service</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="sidebar">
            <h2>MyPage</h2>
            <ul>
                <li data-target="edit">내 정보</li>
                <li data-target="game_records">내 게임기록</li>
                <li data-target="my_posts">내 게시글</li>
                <li data-target="my_qna">내 문의내역</li>
                <li>회원탈퇴</li>
            </ul>
        </div>

        <div class="main-content">
            <div class="content" id="edit">
                <h2 style="font-size: 50px;">내 정보</h2><br>
                <fieldset>
                    <legend>기본정보</legend>
                    <div class="input-group">
                        <label for="login_id">아이디</label>
                        <input type="text" name="login_id" id="login_id" value=${member.loginId} readonly>
                    </div>
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" name="name" id="name" value=${member.name} readonly>
                    </div>
                    <div class="input-group">
                        <label for="nickname">닉네임</label>
                        <input type="text" name="nickname" id="nickname" value=${member.nickName} readonly>
                    </div>
                    <div class="input-group">
                        <label for="ssn">주민번호</label>
                        <input type="text" name="ssnFront" id="ssnFront" value="970311"style="width: 30%;"> <span> - </span> 
                        <input type="text" name="ssnBack" id="ssnBack" value="1******" style="width: 30%;">
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="text" name="email" id="email" value=${member.email} readonly>
                    </div>
                    <div class="input-group">
                        <label for="phone">전화번호</label>
                        <input type="text" name="phone" id="phone" value=${member.phone} readonly>
                    </div>
                    <div class="input-group">
                        <label for="zipcode">우편번호</label>
                        <input type="text" name="zipcode" id="zipcode" 
    					value="<c:choose><c:when test='${member.zipCode == 0}'>값이 없습니다</c:when><c:otherwise>${member.zipCode}</c:otherwise></c:choose>" readonly>

                    </div>
                    <div class="input-group">
                        <label for="address">주소</label>
                        <input type="text" name="address" id="address" 
    					value="<c:choose><c:when test='${member.address == null}'>값이 없습니다</c:when><c:otherwise>${member.address}</c:otherwise></c:choose>" readonly>

                    </div>
                    <div class="input-group">
                        <label for="detail_address">상세주소</label>
                        <input type="text" name="detailAddress" id="detailAddress" 
                        	value="<c:choose><c:when test='${member.detailAddress == null}'>값이 없습니다</c:when>
						              	<c:otherwise>${member.detailAddress}</c:otherwise></c:choose>" readonly>
                    </div>
                    <div class="input-group">
                        <label for="join_date">가입날짜</label>
                        <input type="text" name="join_date" id="join_date" value=${member.regDate} readonly>
                    </div>
                    <div class="buttons">
                        <button type="button">수정하기</button>
                        <button type="button">회원탈퇴</button>
                    </div>

                </fieldset>

            </div>
            <div class="content" id="game_records">
                <h2 style="font-size: 50px;">내 게임기록</h2>
            </div>
            <div class="content" id="my_posts">
                <h2 style="font-size: 50px;">내 게시글</h2>
            </div>
            <div class="content" id="my_qna">
                <h2 style="font-size: 50px;">내 문의내역</h2>
            </div>
        </div>
    </div>
    <script>
        $(".sidebar ul li ").click(function () {
            let targetId = $(this).attr("data-target");
            let targetElement = $("#" + targetId);
            if (targetElement.length) {
                $("html, body").animate(
                    { scrollTop: targetElement.offset().top - 100 }, 600
                );
            }
        });
    </script>
</body>

</html>