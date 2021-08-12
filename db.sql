DROP DATABASE IF EXISTS jsp_example;
CREATE DATABASE jsp_example;
USE jsp_example;

CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    delDate DATETIME DEFAULT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0,
    likeCount INT(10) UNSIGNED NOT NULL DEFAULT 0,
    dislike INT(10) UNSIGNED NOT NULL DEFAULT 0
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 2,
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 2,
title = '제목2',
`body` = '내용2';

CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    delDate DATETIME DEFAULT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    loginId CHAR(50) UNIQUE NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(30) NOT NULL,
    nickname CHAR(30) NOT NULL,
    email CHAR(100) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED DEFAULT 3 NOT NULL COMMENT '(3=일반, 7=관리자)'
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '홍길동',
nickname = '관리자',
email = 'sohyunp96@gmail.com',
cellphoneNo = '01012341234',
authLevel = 7;

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '홍길순',
nickname = '강바람',
email = 'sohyunp96@gmail.com',
cellphoneNo = '01012341234';
