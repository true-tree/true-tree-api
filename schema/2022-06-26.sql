-- member Table Create SQL
CREATE TABLE member
(
    `id`          INT             NOT NULL    AUTO_INCREMENT COMMENT '회원 고유 아이디',
    `sns_id`      VARCHAR(100)    NOT NULL    COMMENT 'SNS 고유 아이디',
    `nick_name`   VARCHAR(30)     NOT NULL    COMMENT '닉네임',
    `socialType`  ENUM('GOOGLE', 'KAKAO', 'APPLE')            NOT NULL    COMMENT '소셜로그인 타입',
    `money`       INT             NOT NULL    COMMENT '유저의 현재 자금',
    `role`        INT             NOT NULL    COMMENT '권한',
    `create_at`   DATETIME        NOT NULL    COMMENT '회원가입일자',
    `update_at`   DATETIME        NOT NULL    COMMENT '정보변경일자',
    PRIMARY KEY (id)
);

ALTER TABLE member COMMENT '회원 테이블';

CREATE UNIQUE INDEX UQ_member_1
    ON member(nick_name);


-- member_authority Table Create SQL
CREATE TABLE member_authority
(
    `id`         INT     NOT NULL    AUTO_INCREMENT COMMENT '고유번호',
    `member_id`  INT     NOT NULL    COMMENT '유저 아이디',
    `authority`  ENUM('USER', 'ADMIN')    NOT NULL    COMMENT '권한',
    PRIMARY KEY (id)
);

ALTER TABLE member_authority COMMENT '권한';

ALTER TABLE member_authority
    ADD CONSTRAINT FK_member_authority_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- game Table Create SQL
CREATE TABLE game
(
    `id`               INT            NOT NULL    AUTO_INCREMENT COMMENT '게임 id',
    `member_id`        INT            NOT NULL    COMMENT '회원 id',
    `coin_name`        VARCHAR(55)    NOT NULL    COMMENT '게임에 쓰는 코인',
    `start_number`     INT            NOT NULL    COMMENT '코인의 시작 번호',
    `play_game_count`  INT            NOT NULL    COMMENT '현재 요청 횟수',
    PRIMARY KEY (id)
);

ALTER TABLE game COMMENT '플레이중인 게임';

ALTER TABLE game
    ADD CONSTRAINT FK_game_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- game_history Table Create SQL
CREATE TABLE game_history
(
    `id`              INT    NOT NULL    AUTO_INCREMENT COMMENT '고유 아이디',
    `member_id`       INT    NOT NULL    COMMENT '회원 번호',
    `rate_of_return`  INT    NOT NULL    COMMENT '수익률',
    `start_amount`    INT    NOT NULL    COMMENT '시작 금액',
    `end_amount`      INT    NOT NULL    COMMENT '종료 금액',
    PRIMARY KEY (id)
);

ALTER TABLE game_history COMMENT '플레이한 게임 히스토리';


