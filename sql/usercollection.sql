/*
Navicat MySQL Data Transfer

Source Server         : ccy
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : novel

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-13 21:57:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for usercollection
-- ----------------------------
DROP TABLE IF EXISTS `usercollection`;
CREATE TABLE `usercollection` (
  `uid` int(11) NOT NULL,
  `collectionImages` blob NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
