class gameover extends Phaser.Scene {
    constructor() {
        super({ key: "gameover" });
    }

    create() {
        // 배경 색상 설정
        this.cameras.main.setBackgroundColor("#000");
        
        // 세션 스토리지에서 점수 가져오기
        let finalScore = sessionStorage.getItem("finalScore") || 0;
        $.ajax({
            url: '/score/add.do',
            type: 'POST',
            data: {
                gameId: 800001,
                score: finalScore
            }
        }).done(function(data) {
           console.log(data);
        });


        // 게임 오버 텍스트
        this.add.text(512, 200, "Game Over", { fontSize: "48px", fill: "#fff" }).setOrigin(0.5);
        
        // 최종 점수 표시
        this.add.text(512, 300, `Final Score: ${finalScore}`, { fontSize: "32px", fill: "#fff" }).setOrigin(0.5);
        
        // 처음으로 버튼
        let restartButton = this.add.text(512, 400, "Return to Start", {
            fontSize: "28px",
            fill: "#fff",
            backgroundColor: "#444"
        }).setOrigin(0.5).setPadding(10).setInteractive();

        // 버튼 클릭 시 Start 씬으로 이동
        restartButton.on("pointerdown", () => {
            this.scene.start("Start");
        });
    }
}
