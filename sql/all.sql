-- edu_ad表
DROP TABLE IF EXISTS `promotion_space`;
CREATE TABLE `promotion_space`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) DEFAULT NULL COMMENT '名称',
    `spaceKey`   varchar(255) DEFAULT NULL COMMENT '广告位key',
    `createTime` datetime     DEFAULT NULL,
    `updateTime` datetime     DEFAULT NULL,
    `isDel`      int(2)       DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `promotion_space_key_isDel` (`spaceKey`, `isDel`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 172
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

DROP TABLE IF EXISTS `promotion_ad`;
CREATE TABLE `promotion_ad`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255)     DEFAULT NULL COMMENT '广告名',
    `spaceId`     int(11)          DEFAULT NULL COMMENT '广告位id',
    `keyword`     varchar(255)     DEFAULT NULL COMMENT '精确搜索关键词',
    `htmlContent` text COMMENT '静态广告的内容',
    `text`        varchar(255)     DEFAULT NULL COMMENT '文字',
    `link`        varchar(255)     DEFAULT NULL COMMENT '链接',
    `startTime`   datetime         DEFAULT NULL COMMENT '开始时间',
    `endTime`     datetime         DEFAULT NULL COMMENT '结束时间',
    `createTime`  datetime         DEFAULT NULL,
    `updateTime`  datetime         DEFAULT NULL,
    `status`      int(2)  NOT NULL DEFAULT '0',
    `priority`    int(4)           DEFAULT '0' COMMENT '优先级',
    `img`         varchar(255)     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY `promotion_ad_SEG` (`spaceId`, `startTime`, `endTime`, `status`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1090
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

-- edu_user表
CREATE TABLE `user`
(
    `id`                      int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name`                    varchar(255) NOT NULL COMMENT '用户昵称',
    `portrait`                varchar(255)          DEFAULT NULL COMMENT '用户头像地址',
    `phone`                   varchar(255) NOT NULL COMMENT '注册手机',
    `password`                varchar(255)          DEFAULT NULL COMMENT '用户密码（可以为空，支持只用验证码注册、登录）',
    `reg_ip`                  varchar(255)          DEFAULT NULL COMMENT '注册ip',
    `account_non_expired`     bit(1)                DEFAULT b'1' COMMENT '是否有效用户',
    `credentials_non_expired` bit(1)                DEFAULT b'1' COMMENT '账号是否未过期',
    `account_non_locked`      bit(1)                DEFAULT b'1' COMMENT '是否未锁定',
    `status`                  varchar(20)  NOT NULL DEFAULT 'ENABLE' COMMENT '用户状态：ENABLE能登录，DISABLE不能登录',
    `is_del`                  bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `create_time`             datetime     NOT NULL COMMENT '注册时间',
    `update_time`             datetime     NOT NULL COMMENT '记录更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_phone_is_del` (`phone`, `is_del`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

CREATE TABLE `user_weixin`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)               DEFAULT NULL COMMENT '用户id',
    `union_id`    varchar(255) NOT NULL DEFAULT '' COMMENT '认证id,微信对应的时unionId',
    `open_id`     varchar(255)          DEFAULT NULL COMMENT 'openId',
    `nick_name`   varchar(255) NOT NULL COMMENT '昵称',
    `portrait`    varchar(512)          DEFAULT NULL COMMENT '头像',
    `city`        varchar(255)          DEFAULT NULL COMMENT '城市',
    `sex`         int(11)               DEFAULT NULL COMMENT '性别, 1-男，2-女',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `is_del`      bit(1)       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `oauthId_and_oauthType_unique` (`union_id`, `open_id`, `is_del`) USING BTREE,
    UNIQUE KEY `userId_and_oauthType_unique_index` (`user_id`, `open_id`, `is_del`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

CREATE TABLE `user_phone_verification_code`
(
    `id`                int(10) NOT NULL AUTO_INCREMENT,
    `phone`             varchar(15) DEFAULT '' COMMENT '手机号',
    `verification_code` varchar(15) DEFAULT '' COMMENT '验证码',
    `create_time`       datetime    DEFAULT NULL COMMENT '创建时间',
    `isCheck`           bit(1)      DEFAULT b'0' COMMENT '验证码是否校验过',
    `check_times`       int(2)      DEFAULT '0' COMMENT '校验次数',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `l_phone_verification_code_ind_01` (`phone`, `create_time`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;