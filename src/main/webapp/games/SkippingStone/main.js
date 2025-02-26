class main extends Phaser.Scene {
    constructor() {
        super({ key: "main" });
    }

    preload() {
        // 이미지 리소스 로딩
        this.load.image("background", "assets/background.png");
        this.load.image("obstacle", "assets/obstacle2.png");
        this.load.image("jumper", "assets/jumper2.png");
        this.load.image("redstone", "assets/red2.png");
        this.load.image("bluestone", "assets/blue2.png");

        // 파워업 이미지 로딩
        this.load.image("powerup0", "assets/powerup1-2.png");
        this.load.image("powerup1", "assets/powerup2-2.png");
        this.load.image("powerup2", "assets/powerup3-2.png");

        this.load.spritesheet("stone", "assets/realstone.png", { frameWidth: 167, frameHeight: 250 });

        // 사운드 리소스 로딩
        this.load.audio("bounce", "assets/sounds/bounce.mp3");
        this.load.audio("hit", "assets/sounds/hit.mp3");
        this.load.audio("powerup", "assets/sounds/powerup.mp3");
        this.load.audio("score", "assets/sounds/score.mp3");
        this.load.audio("shield", "assets/sounds/shield.mp3");
        this.load.audio("success", "assets/sounds/success.mp3");
        this.load.audio("boost", "assets/sounds/boost.mp3");
    }

    create() {
        // 배경 설정
        this.bg = this.add.tileSprite(512, 250, 1024, 500, "background");
        this.bgSpeed = 2;

        this.shieldBar = this.add.rectangle(512, 50, 200, 20, 0x00ffff).setOrigin(0.5);
        this.shieldBar.setVisible(false);

        // 점수 변수
        this.score = 0;
        this.scoreText = this.add.text(20, 20, "Score: 0", { fontSize: "24px", fill: "#fff" });

        // 애니메이션 설정
        this.anims.create({
            key: "spin",
            frames: this.anims.generateFrameNumbers("stone", { start: 0, end: 5 }), // 6프레임 애니메이션
            frameRate: 3,
            repeat: -1 // 무한 반복
        });

        // 주인공 오브젝트 - 애니메이션 제거하여 오류 방지
        this.stone = this.physics.add.sprite(200, 300, "stone").setScale(0.2);
        this.stone.body.setGravityY(500);
        this.stone.setCollideWorldBounds(true);
        this.stone.setBounce(0.99);  // 정확한 값으로 설정
        this.stone.setVelocityY(-300);
        this.stone.setImmovable(false);

        // 애니메이션 코드 제거 (텍스처 문제 해결을 위해)
        // 나중에 스프라이트시트가 준비되면 다시 추가할 수 있습니다
        this.stone.play("spin");

        // 키보드 입력 설정
        this.cursors = this.input.keyboard.createCursorKeys();

        // 장애물 그룹 생성
        this.obstacles = this.physics.add.group();

        // 장애물 생성 타이머
        this.time.addEvent({
            delay: 2000,
            callback: this.createObstacle,
            callbackScope: this,
            loop: true
        });

        // 파워업 그룹 생성
        this.powerups = this.physics.add.group();

        // 파워업 생성 타이머
        this.time.addEvent({
            delay: 5000,
            callback: this.createPowerup,
            callbackScope: this,
            loop: true
        });

        // 랜덤 오브젝트 그룹 생성
        this.objects = this.physics.add.group();
        this.spawnObjects();

        // 충돌 설정
        this.physics.add.collider(this.stone, this.obstacles, this.hitObstacle, null, this);
        this.physics.add.overlap(this.stone, this.powerups, this.collectPowerup, null, this);

        // 월드 경계 충돌 이벤트
        this.physics.world.on('worldbounds', (body) => {
            if (body.gameObject === this.stone) {
                if (body.blocked.up) {
                    this.scene.start('gameover');
                }
                // 돌이 바닥이나 벽과 충돌할 때 효과 추가
                this.cameras.main.shake(100, 0.01);
                if (this.sound.get('bounce')) { // 사운드가 로드되었는지 확인
                    this.sound.play('bounce');
                }
            }
        });

        // 탄성 감소 설정
        this.elasticity = 0.9;
        this.lastY = this.stone.y;
        this.groundTimer = null;

        this.time.addEvent({
            delay: 1000,
            callback: () => {
                if (this.stone.y === this.lastY) {
                    if (!this.groundTimer) {
                        this.groundTimer = this.time.delayedCall(2000, () => {
                            this.scene.start("gameover");
                        });
                    }
                } else {
                    this.lastY = this.stone.y;
                    if (this.groundTimer) {
                        this.groundTimer.remove();
                        this.groundTimer = null;
                    }
                }
            },
            loop: true
        });

        // 중력 증가
        this.time.addEvent({
            delay: 10000,
            callback: () => {
                this.stone.setGravityY(this.stone.body.gravity.y + 30);
            },
            loop: true
        });

        // Space 입력 감지
        this.input.keyboard.on("keydown-SPACE", () => {
            let hitJumper = false;
            let correctTiming = false;

            this.objects.children.iterate(obj => {
                if (this.physics.overlap(this.stone, obj)) {
                    hitJumper = obj.type === "jumper";

                    if (obj.type === "jumper") {
                        this.elasticity = Math.min(1, this.elasticity + 0.05);
                        this.stone.setVelocityY(-500);
                        this.stone.setVelocityX(0); // 수평 이동 제거
                        this.elasticity = Math.min(1, this.elasticity + 0.1); // 점프 시 탄성 증가
                        this.cameras.main.flash(100, 0, 255, 0);
                        this.sound.play("success");
                        correctTiming = true;
                        // 점프 성공 효과
                        this.cameras.main.flash(100, 0, 255, 0);
                        if (this.sound.get('success')) {
                            this.sound.play('success');
                        }
                    } else {
                        this.elasticity = Math.max(0, this.elasticity - 0.2); // Jumper를 놓치면 탄성 감소
                        if (this.elasticity <= 0) {
                            this.scene.start("gameover");
                        }
                    }
                }
            });

            // Jumper가 없어도 스페이스를 누르면 작은 부스트
            if (!hitJumper) {
                this.stone.setVelocityY(this.stone.body.velocity.y - 50);
                if (this.sound.get('boost')) {
                    this.sound.play('boost');
                }
            }
        });
    }

    update() {
        // 배경 스크롤
        this.bg.tilePositionX += this.bgSpeed;

        // 점수 계산 - 탄성과 배경 속도에 비례
        this.score += (this.bgSpeed * this.elasticity * 600) / 100;
        this.scoreText.setText("Score: " + Math.floor(this.score));

        // 키보드 입력에 따른 돌 이동
        if (this.cursors.left.isDown) {
            this.stone.setVelocityX(-200);
        } else if (this.cursors.right.isDown) {
            this.stone.setVelocityX(200);
        } else {
            // 수평 속도 감소
            this.stone.setVelocityX(this.stone.body.velocity.x * 0.95);
        }

        // 장애물, 파워업, 오브젝트 화면 밖으로 나가면 제거
        this.obstacles.getChildren().forEach(obstacle => {
            if (obstacle.x < -obstacle.width) {
                obstacle.destroy();
            }
        });

        this.powerups.getChildren().forEach(powerup => {
            if (powerup.x < -powerup.width) {
                powerup.destroy();
            }
        });

        this.objects.getChildren().forEach(obj => {
            if (obj.x < -obj.width) {
                obj.destroy();
            }
        });
    }

    createObstacle() {
        // 장애물 생성 위치 랜덤화
        const y = Phaser.Math.Between(100, 400);
        const obstacle = this.obstacles.create(1024, y, 'obstacle');

        // 장애물 속성 설정
        obstacle.setVelocityX(-200);
        obstacle.setImmovable(true);
        obstacle.body.allowGravity = false;
    }

    createPowerup() {
        // 파워업 생성 위치 랜덤화
        const y = Phaser.Math.Between(100, 400);
        const powerupType = Phaser.Math.Between(0, 2);
        const powerup = this.powerups.create(1024, y, 'powerup' + powerupType);

        // 파워업 속성 설정
        powerup.setVelocityX(-150);
        powerup.body.allowGravity = false;
        powerup.powerupType = powerupType;
    }

    spawnObjects() {
        this.time.addEvent({
            delay: 1500,
            callback: () => {
                let randX = Phaser.Math.Between(800, 1024);

                let objTypeOptions = ["jumper", "redstone", "bluestone"];
                let objType = objTypeOptions[Phaser.Math.Between(0, 2)];

                let objY = objType === "jumper" ? 350 : Phaser.Math.Between(250, 350);
                let obj = this.objects.create(randX, objY, objType).setScale(0.125);
                obj.setVelocityX(-200);
                obj.type = objType;

                if (objType === "redstone") {
                    this.bgSpeed += 1;
                    this.elasticity = Math.min(1, this.elasticity + 0.01);
                    obj.setTint(0xff0000);
                    if (this.sound.get("boost")) {
                        this.sound.play("boost"); // 속도 증가 효과음
                    }
                } else if (objType === "bluestone") {
                    this.stone.setGravityY(this.stone.body.gravity.y - 50);
                    obj.setTint(0x0000ff);
                    if (this.sound.get("powerup")) {
                        this.sound.play("powerup"); // 중력 감소 효과음
                    }
                } else if (objType === "jumper") {
                    obj.setTint(0x00ff00);
                }
            },
            loop: true
        });
    }

    hitObstacle(stone, obstacle) {
        // 무적 상태가 아닐 때만 데미지 처리
        if (!stone.isInvincible) {
            // 충돌 효과
            this.cameras.main.shake(150, 0.03);
            if (this.sound.get('hit')) {
                this.sound.play('hit');
            }

            // 장애물 제거
            obstacle.destroy();

            // 게임 오버 처리
            sessionStorage.setItem("finalScore", Math.floor(this.score));
            this.scene.start("gameover");
        } else {
            // 무적 상태면 장애물만 제거
            obstacle.destroy();
        }
    }

    collectPowerup(stone, powerup) {
        // 파워업 효과 적용

        switch (powerup.powerupType) {
            case 0: // 중력 감소
                stone.body.setGravityY(stone.body.gravity.y * 0.7);
                if (this.sound.get('powerup')) {
                    this.sound.play('powerup');
                }
                // 10초 후 중력 복원
                this.time.delayedCall(10000, () => {
                    stone.body.setGravityY(stone.body.gravity.y / 0.7);
                });
                break;
            case 1: // 점수 증가
                this.score += 100;
                this.scoreText.setText("Score: " + Math.floor(this.score));
                if (this.sound.get('score')) {
                    this.sound.play('score');
                }
                break;
            case 2: // 일시적 무적
                stone.setTint(0x00ffff);
                stone.isInvincible = true;
                if (this.sound.get('shield')) {
                    this.sound.play('shield');
                }
                // 5초 후 무적 해제
                this.time.delayedCall(5000, () => {
                    stone.clearTint();
                    stone.isInvincible = false;
                });
                if (this.shieldBar) {
                    this.shieldBar.setVisible(true);
                    this.shieldBar.width = 200;
                }
                // 무적 게이지 설정

                this.time.delayedCall(5000, () => {
                    stone.isInvincible = false;
                    this.shieldBar.setVisible(false);
                    stone.clearTint();
                });
                break;

        }

        // 파워업 제거
        powerup.destroy();
    }
}