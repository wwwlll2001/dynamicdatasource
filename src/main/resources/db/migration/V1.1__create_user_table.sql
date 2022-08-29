CREATE TABLE `user` (
                        `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT '??ID',
                        `name` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT '姓名',
                        `age` int(11) DEFAULT NULL COMMENT '年龄',
                        `email` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '邮箱',
                        `type` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
                        `deleted` tinyint(1) DEFAULT '0',
                        `version` int(11) DEFAULT '1',
                        PRIMARY KEY (`id`)
);