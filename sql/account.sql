/*
Navicat MySQL Data Transfer

Source Server         : ccy
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : novel

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-13 21:57:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET ascii NOT NULL COMMENT 'hash后的密码',
  `salt` char(20) CHARACTER SET ascii NOT NULL COMMENT '生成的盐',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
