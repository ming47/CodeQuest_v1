class Start extends Phaser.Scene {
    constructor() {
        super({ key: "Start" });
        this.tileSpeed = 2;
        this.playerSpeed = 200; // 플레이어 이동 속도
        this.direction = 1; // 1이면 오른쪽, -1이면 왼쪽
    }

    preload() {
        this.load.image("background", "우주.png");
        this.load.image("title", "pngegg.png");

        this.load.image("player", "default.png"); // 플레이어 이미지 추가
    }

    create() {
        this.background = this.add.tileSprite(0, 0, 4562, 2545, "background").setOrigin(0, 0).setScale(0.5)

        this.title = this.add.tileSprite(10, -60, 974, 975, "title").setOrigin(0, 0).setScale(0.5)

        // 플레이어 생성 (화면 아래 중앙)
        this.player = this.physics.add.sprite(this.cameras.main.width / 2, this.cameras.main.height - 50, "player");
        this.player.setCollideWorldBounds(true); // 화면 밖으로 나가지 않도록 설정

        // 플레이어 자동 이동 시작
        this.player.setVelocityX(this.playerSpeed * this.direction);


        // 게임 시작 버튼
        let restartBtn = this.add.text(this.cameras.main.width / 2+18,
            this.cameras.main.height / 2 + 100, "start", {
            fontSize: '25px'
        }).setOrigin(0.5).setInteractive();

        restartBtn.on("pointerover", () => {
            restartBtn.setBackgroundColor("#2B0090");
            this.game.canvas.style.cursor = "pointer";
        });

        restartBtn.on("pointerout", () => {
            restartBtn.setBackgroundColor("#000000");
            this.game.canvas.style.cursor = "default";
        });

        restartBtn.on("pointerdown", () => {
            this.scene.start("Exam03");
        });
    }

    update() {
        // 배경 애니메이션
        this.background.tilePositionY -= this.tileSpeed;

        // 자동으로 좌우 이동
        if (this.player.x <= this.player.width / 2) {
            this.direction = 1; // 오른쪽으로 이동
            this.player.setVelocityX(this.playerSpeed * this.direction);
        } else if (this.player.x >=  this.cameras.main.width - this.player.width / 2) {
            this.direction = -1; // 왼쪽으로 이동
            this.player.setVelocityX(this.playerSpeed * this.direction);
        }

       
    }
}
