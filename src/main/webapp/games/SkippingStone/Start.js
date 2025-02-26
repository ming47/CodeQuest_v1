class Start extends Phaser.Scene {
    constructor() {
        super({ key: "Start" });
    }

    preload() {
        this.load.image("bg", "/assets/gameback.png");
    }

    create() {
        // 배경 설정
        this.bg = this.add.tileSprite(512, 250, 1024, 500, "bg");
        this.cameras.main.setBackgroundColor("#87CEEB");

        // 게임 제목 (더 크고 굵은 폰트 + 그림자 효과)
        let titleText = this.add.text(512, 120, "Skipping Stone", {
            fontSize: "64px",
            fontFamily: "Arial",
            fontStyle: "bold",
            color: "#ffffff",
            stroke: "#000",
            strokeThickness: 8
        }).setOrigin(0.5);

        // 텍스트에 그림자 추가
        titleText.setShadow(4, 4, "#000", 5, true, true);

        // Start 버튼 생성
        this.createButton(512, 300, "Start", () => {
            this.scene.start("main");
        });

        // Option 버튼 생성
        this.createButton(512, 400, "Option", () => {
            this.showOptions();
        });
    }

    // 버튼을 세련되게 만드는 함수
    createButton(x, y, text, callback) {
        let buttonWidth = 200;
        let buttonHeight = 60;

        // 버튼 배경 (둥근 사각형)
        let buttonBg = this.add.rectangle(x, y, buttonWidth, buttonHeight, 0x000000, 0.8);
        buttonBg.setStrokeStyle(4, 0xffffff); // 흰색 테두리 추가
        buttonBg.setInteractive();

        // 버튼 텍스트
        let buttonText = this.add.text(x, y, text, {
            fontSize: "32px",
            fontFamily: "Arial",
            color: "#ffffff",
        }).setOrigin(0.5);

        // 버튼 그룹을 컨테이너로 묶기
        let buttonContainer = this.add.container(0, 0, [buttonBg, buttonText]);

        // Hover 효과 추가
        buttonBg.on("pointerover", () => {
            buttonBg.setFillStyle(0xffffff, 1); // 배경을 흰색으로 변경
            buttonText.setColor("#000000"); // 텍스트를 검은색으로 변경
        });

        buttonBg.on("pointerout", () => {
            buttonBg.setFillStyle(0x000000, 0.8); // 원래 색으로 복구
            buttonText.setColor("#ffffff");
        });

        // 버튼 클릭 애니메이션 + 실행
        buttonBg.on("pointerdown", () => {
            this.tweens.add({
                targets: buttonContainer,
                scaleX: 0.9,
                scaleY: 0.9,
                duration: 100,
                yoyo: true,
                onComplete: callback
            });
        });
    }

    // 옵션 메뉴 표시 (버튼과 같은 스타일 적용)
    showOptions() {
        this.createButton(512, 500, "Back", () => {
            this.scene.start("Start");
        });

        this.add.text(512, 450, "옵션 메뉴 (추가 설정 가능)", {
            fontSize: "24px",
            fontFamily: "Arial",
            color: "#ffffff",
            backgroundColor: "#000"
        }).setOrigin(0.5);
    }
}
