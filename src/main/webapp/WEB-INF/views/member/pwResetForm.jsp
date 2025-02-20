<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    </style>
</head>
<body>
    <div class="pw-container">
        <h2>비밀번호 재설정(개발중)</h2>
        <p>비밀번호를 재설정할 이메일을 입력해주세요.</p>
        <form action="/member/sendResetEmail.do" method="post">
            <input type="text" name="email" placeholder="이메일">
            <button type="submit">인증 메일 전송</button>
        </form>
    </div>
</body>
</html>
