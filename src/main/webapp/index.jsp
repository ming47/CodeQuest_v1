<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <title>Responsive Game Portal</title>
    <style>
        /* ê¸°ë³¸ ì¤íì¼ */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        body {
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* ì»¨íì´ë */
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
            background: #007bff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            color: white;
            font-weight: bold;
        }

        /* ë¤ë¹ê²ì´ì */
        .navi {
            display: flex;
            align-items: center;
            width: 100%;
            justify-content: space-between;
        }
        .logo {
            font-size: 24px;
        }
        .navi ul {
            list-style: none;
            display: flex;
            gap: 20px;
        }
        .navi ul li {
            padding: 10px 15px;
            background: white;
            color: #007bff;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s ease-in-out;
        }
        .navi ul li:hover {
            background: #0056b3;
            color: white;
        }

        /* ë¡ê·¸ì¸ ì ë³´ */
        .logbox {
            display: none;
            padding: 10px;
            font-weight: bold;
            color: white;
        }

        /* ë³¸ë¬¸ */
        .body {
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            gap: 5%;
        }

        /* ê²ì ë¦¬ì¤í¸ (ê°ë¡ ì¤í¬ë¡¤ ë³µêµ¬) */
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
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        .game button:hover {
            background: #0056b3;
        }

        /* ë¡ê·¸ì¸ ë°ì¤ */
        .loginbox {
            width: 15%;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            min-height: 320px;
        }

        /* ê²ìí */
        .boardlist {
            width: 60%;
            background: white;
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
        <!-- í¤ë -->
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
            <div class="logbox">Welcome, <span id="username"></span>!</div>
        </div>

        <!-- ë³¸ë¬¸ -->
        <div class="body">
            <div class="gameList">
                <div class="game"><img src="game1.jpg"><h3>Game 1</h3><p>RPG</p><button>Play</button></div>
                <div class="game"><img src="game2.jpg"><h3>Game 2</h3><p>FPS</p><button>Play</button></div>
                <div class="game"><img src="game3.jpg"><h3>Game 3</h3><p>Action</p><button>Play</button></div>
                <div class="game"><img src="game4.jpg"><h3>Game 4</h3><p>Adventure</p><button>Play</button></div>
                <div class="game"><img src="game5.jpg"><h3>Game 5</h3><p>Strategy</p><button>Play</button></div>
                <div class="game"><img src="game6.jpg"><h3>Game 6</h3><p>Sports</p><button>Play</button></div>
            </div>

            <div class="loginbox">
                <h2>ë¡ê·¸ì¸</h2>
                <input type="text" id="userId" placeholder="ìì´ë">
                <input type="password" id="userPw" placeholder="ë¹ë°ë²í¸">
                <button id="loginBtn">ë¡ê·¸ì¸</button>
                <div id="loginResult"></div>
            </div>
        </div>

        <div class="boardlist">
            <h3>ð¢ ìµê·¼ ê²ìë¬¼</h3>
            <ul>
                <li>ê²ìê¸ 1</li>
                <li>ê²ìê¸ 2</li>
                <li>ê²ìê¸ 3</li>
            </ul>
        </div>

        <!-- í¸í° -->
        <div class="footer">Â© 2025 Team CodeQuest. All rights reserved.</div>
    </div>

    <script>
        $(document).ready(function () {
            $("#loginBtn").click(function () {
                let userId = $("#userId").val().trim();
                let userPw = $("#userPw").val().trim();

                if (userId === "" || userPw === "") {
                    alert("아이디와 비밀번호를 입력하세요!");
                    return false;
                }

                $.ajax({
                    url: "/member/login.do",
                    method: "POST", 
                    data: { id: userId, pw: userPw },
                    dataType: "text"
                })
                .done(function(resp) {
                    if (resp.trim() === "success") {
                        $(".logbox").fadeIn().find("#username").text(userId);
                        $(".loginbox").fadeOut();
                    } else {
                        $("#loginResult").text("로그인 실패. 아이디/비밀번호를 확인하세요.");
                    }
                })
                .fail(function(xhr, status, error) {
                    console.log("로그인 AJAX 실패:", error);
                });
            });
        });
    </script>
</body>
</html>
