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
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (`name`, `email`, `password`, `status`)
VALUES ('admin', 'fishland@gmail.com', 'admin123', 1),
       ('小鱼', 'xiaoyu@gmail.com', 'admin123', 1);

CREATE TABLE `attachment`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50)  NOT NULL UNIQUE,
    `createDate`  TIMESTAMP    NOT NULL,
    `updateDate`  TIMESTAMP,
    `status`      INT          NOT NULL,
    `type`        INT          NOT NULL,
    `file`        LONGBLOB     NOT NULL COMMENT '存放文件二进制内容',
    `contentType` VARCHAR(100) NOT NULL COMMENT '文件的类型',
    `master`      INT COMMENT '关联的主要内容',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `article`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `title`      VARCHAR(100) NOT NULL,
    `content`    TEXT         NOT NULL,
    `createDate` TIMESTAMP    NOT NULL,
    `updateDate` TIMESTAMP,
    `status`     INT          NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;