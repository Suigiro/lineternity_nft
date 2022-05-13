/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : l2wins

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2022-02-27 17:30:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dungeon
-- ----------------------------
DROP TABLE IF EXISTS `dungeon`;
CREATE TABLE `dungeon` (
  `dungid` tinyint(4) DEFAULT NULL,
  `ipaddr` varchar(45) DEFAULT NULL,
  `lastjoin` bigint(20) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
