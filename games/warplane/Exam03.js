class Exam03 extends Phaser.Scene {

    constructor() {
        super({ key: 'Exam03' });
        this.frame = 0;
        this.speed = 300;
        this.gameTime = 0;
        this.tileSpeed = 2;
        this.lastDirection = 1;
        this.isGameOver = false;  // 게임 오버 상태를 추적
    }

    preload() {
        this.load.image("enemy", "enemy.png");
        this.load.image("player", "default.png");
        this.load.image("GameOver", "gameover.png");
        this.load.image("background", "background.png");
        this.load.image("bullet", "bullet.png");
    }

    create() {
        this.background = this.add.tileSprite(0, 0, this.cameras.main.width, this.cameras.main.height, "background").setOrigin(0, 0);
        this.timeText = this.add.text(10, 10, "Time : 0", { fontSize: '15px', fill: 'white' });

        this.me = this.physics.add.sprite(250, 450, "player");
        this.me.setScale(2000 / 2500);
        this.me.setCollideWorldBounds(true);

        this.boxes = this.physics.add.group(); // 적군 그룹
        this.bullets = this.physics.add.group({ // 총알 그룹
            defaultKey: 'bullet',
            maxSize: 10
        });

        // 플레이어와 적군 충돌 시 게임 오버
        this.physics.add.collider(this.me, this.boxes, () => {
            
                this.isGameOver = true;  // 게임 오버 상태로 변경
                this.physics.pause();
                setTimeout(() => {
                    this.scene.start("GameOver");  // 게임 오버 화면으로 이동
                    this.gameTime = 0;  // 시간 초기화
                    this.tileSpeed = 2;  // 배경 속도 초기화
                    this.isGameOver = false;  // 게임 오버 상태 초기화
			        console.log("패배");
			        $.ajax({
			            url: '/score/add.do',
			            type: 'POST',
			            data: {
			                gameId: 800003,
			                score: this.gameTime
			            }
			        }).done(function(data) {
			           console.log(data);
			        });

                }, 2000);  // 2초 뒤에 게임 오버 화면
                this.add.image(this.cameras.main.width / 2, this.cameras.main.height / 2, "GameOver").setOrigin(0.5);
          
        });

        // 총알과 적군 충돌 처리
        this.physics.add.overlap(this.bullets, this.boxes, (bullet, enemy) => {
            bullet.destroy();
            enemy.destroy();
        });

        this.cursor = this.input.keyboard.createCursorKeys();

        // 스페이스바로 총알 발사
        this.input.keyboard.on("keydown-SPACE", () => {
            this.shootBullet();
        });
    }

    update() {
        if (this.isGameOver) return;  // 게임 오버 상태라면 업데이트 하지 않음

        this.frame++;
        this.background.tilePositionY -= this.tileSpeed;

        if (this.frame % 60 == 0) {
            this.gameTime++;
            this.timeText.setText("Time : " + this.gameTime);
        }

   
    if (this.frame % 30 == 0 && this.gameTime > 0) {
        let yPosition = Math.random() > 0.5 ? 0 : this.cameras.main.height;  // 랜덤으로 위나 아래에 적군 생성
           // Math.random()이 0.5보다 큰 경우: yPosition은 0 (화면의 상단)에 설정
        // Math.random()이 0.5보다 작은 경우: yPosition은 화면 하단 설정
      
       
        let box = this.physics.add.sprite(Math.random() * this.cameras.main.width, yPosition, "enemy");
        box.setScale(0.5);
        box.body.setSize(48, 48);
        box.setCollideWorldBounds(true);

        this.boxes.add(box);
        box.setVelocityY(Phaser.Math.Between(150, 400)); // 랜덤 속도
    }

    // 10초 이후 적군 생성
    if (this.gameTime >= 10 && this.frame % 60 == 0) {
        this.tileSpeed = 4;  // 10초 이후 배경 속도 증가
        let yPosition = Math.random() > 0.5 ? 0 : this.cameras.main.height;  
        // Math.random()이 0.5보다 큰 경우: yPosition은 0 (화면의 상단)에 설정
        // Math.random()이 0.5보다 작은 경우: yPosition은 화면 하단 설정
      
        let box = this.physics.add.sprite(Math.random() * this.cameras.main.width, yPosition, "enemy");
        box.setScale(0.5);
        box.body.setSize(48, 48);

        box.setCollideWorldBounds(true);
        this.boxes.add(box);
        box.setVelocityY(Phaser.Math.Between(150, 400)); // 랜덤 속도
    }

    // 10초 이후 추가로 아래에서 위로 생성된 적군
    if (this.gameTime >= 10 && this.frame % 60 == 0) { 
         // 프레임마다 적군 생성(1초마다) (10초 이후)
        let box = this.physics.add.sprite
        (Math.random() * this.cameras.main.width, this.cameras.main.height, "enemy");
        box.setScale(0.5);
        box.body.setSize(48, 48);
        box.setCollideWorldBounds(true);


        box.setGravityY(0);  // 중력 제거 (위로만 이동하게)
    
        this.boxes.add(box);
        box.setVelocityY(-Phaser.Math.Between(150, 400));  // 위로 이동하도록 속도 설정 (음수값)
    }
        // 플레이어 이동
        this.me.setVelocity(0);
        if (this.cursor.left.isDown) {
            this.me.setVelocityX(-this.speed);
            this.lastDirection = -1;
        }
        if (this.cursor.right.isDown) {
            this.me.setVelocityX(this.speed);
            this.lastDirection = 1;
        }
        if (this.cursor.up.isDown) this.me.setVelocityY(-this.speed);
        if (this.cursor.down.isDown) this.me.setVelocityY(this.speed);
    }

    // 총알 발사 함수
    shootBullet() {
        let bullet = this.bullets.get(this.me.x, this.me.y - 20, "bullet");

        if (!bullet) return; // 총알이 없으면 그냥 리턴

        bullet.setScale(0.8);
        bullet.setActive(true).setVisible(true);
        bullet.body.setAllowGravity(false); // 중력 영향 제거
        bullet.setVelocityY(-400); // 위쪽으로 발사

        // 총알이 화면 밖으로 나가면 제거
        bullet.setCollideWorldBounds(true);
        bullet.body.onWorldBounds = true;

        this.physics.world.on("worldbounds", (body) => {
            if (body.gameObject === bullet) {
                bullet.destroy();
            }
        });

      
    }
}
