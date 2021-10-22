USE
    test;

CREATE TABLE `user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(20) NOT NULL UNIQUE,
    `email`    VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `status`   INT         NOT NULL,
    `photo`    LONGBLOB,
    PRIMARY KEY (`id`)
);

INSERT INTO user (`name`, `email`, `password`, `status`)
VALUES ('admin', 'fishland@gmail.com', 'admin123', 1),
       ('admin1', 'fishland@gmail.com', 'admin123', 1);

CREATE TABLE `attachment`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(20)  NOT NULL UNIQUE,
    `createDate`  TIMESTAMP    NOT NULL,
    `updateDate`  TIMESTAMP,
    `status`      INT          NOT NULL,
    `type`        INT          NOT NULL,
    `file`        Longblob     not null comment '存放文件二进制内容',
    `contentType` varchar(100) not null comment '文件的类型',
    PRIMARY KEY (`id`)
);