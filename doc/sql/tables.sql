USE
    test;

CREATE TABLE `user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(20) NOT NULL,
    `email`    VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `status`   INT         NOT NULL,
    `photo`    LONGBLOB,
    PRIMARY KEY (`id`)
);

INSERT INTO user (`name`, `email`, `password`, `status`)
VALUES ('admin', 'fishland@gmail.com', 'admin123', 1),
       ('admin1', 'fishland@gmail.com', 'admin123', 1);