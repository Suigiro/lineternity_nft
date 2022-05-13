/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : l2wins

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2022-02-27 17:30:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for character_memo_dungeon
-- ----------------------------
DROP TABLE IF EXISTS `character_memo_dungeon`;
CREATE TABLE `character_memo_dungeon` (
  `obj_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '0',
  `value` text NOT NULL,
  `expire_time` bigint(20) NOT NULL DEFAULT '0',
  UNIQUE KEY `prim` (`obj_id`,`name`),
  KEY `obj_id` (`obj_id`),
  KEY `name` (`name`),
  KEY `value` (`value`(333)),
  KEY `expire_time` (`expire_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
