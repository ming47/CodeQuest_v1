<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <title>Document</title>
</head>
<body>
    <a href="http://google.com">페이지 이탈</a>
    <div class="score">0</div>
    <button class="game-over">게임 오버</button>
</body>
</html>
<script>
    const enterTime = new Date().getTime();

    window.addEventListener("beforeunload", function() {
        const endTime = new Date().getTime();
        let play_time = endTime - enterTime;
        console.log(play_time);
        //ajax로 play_time전송
    	
    });
</script>
 