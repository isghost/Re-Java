/*
Navicat MySQL Data Transfer

Source Server         : ccy
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : novel

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-13 21:57:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for userimage
-- ----------------------------
DROP TABLE IF EXISTS `userimage`;
CREATE TABLE `userimage` (
  `imageid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `fileName` char(40) NOT NULL COMMENT '文件名',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`imageid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
