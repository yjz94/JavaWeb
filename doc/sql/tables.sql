USE
    javaweb;
/*用户表*/
CREATE TABLE `user`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `createDate` TIMESTAMP   NOT NULL,
    `updateDate` TIMESTAMP,
    `name`       VARCHAR(20) NOT NULL UNIQUE,
    `email`      VARCHAR(50) NOT NULL UNIQUE,
    `password`   VARCHAR(50) NOT NULL,
    `status`     INT         NOT NULL,
    `photo`      LONGBLOB,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (`name`, `email`, `password`, `status`)
VALUES ('admin', 'admin@gmail.com', 'admin123', 1);

/*附件表*/
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
    `master`      varchar(50) COMMENT '关联的主要内容',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*文章表*/
CREATE TABLE `article`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `articleId`  varchar(50)  NOT NULL UNIQUE,
    `title`      VARCHAR(100) NOT NULL,
    `content`    TEXT         NOT NULL,
    `text`       TEXT         NOT NULL,
    `createDate` TIMESTAMP    NOT NULL,
    `updateDate` TIMESTAMP,
    `tags`       VARCHAR(200) NOT NULL,
    `status`     INT          NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*文章交互数据表*/
CREATE TABLE `praise`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `createDate` TIMESTAMP   NOT NULL,
    `updateDate` TIMESTAMP,
    `thumbsUp`   INT         NOT NULL,
    `read`       INT         NOT NULL,
    `thumbsDown` INT         NOT NULL,
    `message`    INT         NOT NULL,
    `master`     varchar(50) NOT NULL,
    `status`     INT         NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*文章标签表*/
CREATE TABLE `tag`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `createDate` TIMESTAMP   NOT NULL,
    `updateDate` TIMESTAMP,
    `name`       varchar(50) NOT NULL UNIQUE,
    `master`     varchar(50) NOT NULL,
    `status`     INT         NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;