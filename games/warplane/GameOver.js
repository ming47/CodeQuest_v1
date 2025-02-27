class GameOver extends Phaser.Scene {


    constructor() {
        super({ key: "GameOver" });
    }
    preload() {
        // 이미지 경로 확인
        this.load.image("background", "background.png");
   
    }

    create() {

        this.background = this.add.tileSprite(
            0, 0, 
            this.cameras.main.width, 
            this.cameras.main.height, 
            "background"
        ).setOrigin(0, 0);
        this.add.text(this.cameras.main.width / 2, this.cameras.main.height / 2 - 40, "Game Over", {
            fontSize: "50px",
            fill: "white"
        }).setOrigin(0.5);
   
        let restartBtn = this.add.text(this.cameras.main.width / 2,
            this.cameras.main.height / 2 + 10 , "Restart ", {
            fontSize: '25px'
        }).setOrigin(0.5).setInteractive();

        // 버튼 인터랙션
        restartBtn.on("pointerover", () => {
            restartBtn.setBackgroundColor("#000080");
            this.game.canvas.style.cursor = "pointer";
        });

        restartBtn.on("pointerout", () => {
            restartBtn.setBackgroundColor("#000000");
            this.game.canvas.style.cursor = "default";
        });

        restartBtn.on("pointerdown", () => {
            this.scene.start("Exam03");
        });
        
        let homeBtn = this.add.text(this.cameras.main.width / 2,
            this.cameras.main.height / 2 + 50, "Home ", {
            fontSize: '25px'
        }).setOrigin(0.5).setInteractive();

        // 버튼 인터랙션
        homeBtn.on("pointerover", () => {
            homeBtn.setBackgroundColor("#000090");
            this.game.canvas.style.cursor = "pointer";
        });

        restartBtn.on("pointerout", () => {
            homeBtn.setBackgroundColor("#000000");
            this.game.canvas.style.cursor = "default";
        });

        homeBtn.on("pointerdown", () => {
            this.scene.start("Start");
        });
        

    }
    update() { 
        // 배경 애니메이션
        this.background.tilePositionY -= this.tileSpeed;
    }
}