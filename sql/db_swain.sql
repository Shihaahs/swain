/*
 Navicat Premium Data Transfer

 Source Server         : Server
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 106.13.87.50:3306
 Source Schema         : db_swain

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 11/05/2019 11:48:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
  `machine_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，机器id',
  `machine_name` varchar(255) NOT NULL DEFAULT '' COMMENT '机器名称',
  `machine_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '机器类型，0-损坏，1-正常',
  `machine_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '机器员工id',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`machine_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------
BEGIN;
INSERT INTO `machine` VALUES (1, '一号机', 1, 2, 0, '2019-05-09 19:57:57', '2019-05-09 20:06:38');
INSERT INTO `machine` VALUES (2, '二号机', 1, 2, 0, '2019-05-09 19:57:57', '2019-05-09 20:06:39');
COMMIT;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，物料id',
  `material_name` varchar(255) NOT NULL DEFAULT '' COMMENT '物料名称',
  `material_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '物料单价',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `material_total` decimal(20,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '物料库存，单位kg',
  PRIMARY KEY (`material_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
INSERT INTO `material` VALUES (1, '小麦', 1.00, 0, '2019-05-09 20:12:51', '2019-05-09 20:14:25', 1000.00);
INSERT INTO `material` VALUES (2, '橡胶', 105.66, 0, '2019-05-09 20:14:22', '2019-05-09 20:14:22', 1000.00);
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，生产数据id',
  `product_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '生产员工id',
  `product_machine_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '生产机器id',
  `product_material_id` bigint(20) NOT NULL COMMENT '生产原料id',
  `product_material_weight` decimal(2,0) unsigned NOT NULL DEFAULT '0' COMMENT '生产原料重量',
  `product_out_name` varchar(255) NOT NULL COMMENT '产出物名称',
  `product_out_weight` decimal(2,0) unsigned NOT NULL DEFAULT '0' COMMENT '产出物重量',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `repair_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，维修日志id',
  `repair_machine_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '维修机器id',
  `repair_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '维修价格',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `repair_comment` varchar(255) NOT NULL DEFAULT '' COMMENT '维修备注',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，用户id',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码',
  `permission` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户权限，0-管理员，1-员工',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 0, 0, '2019-05-09 19:29:01', '2019-05-11 11:16:13');
INSERT INTO `user` VALUES (2, '张三', '123456', 1, 0, '2019-05-09 19:29:01', '2019-05-11 11:16:14');
INSERT INTO `user` VALUES (3, 'aaa', '222222', 1, 1, '2019-05-11 11:36:24', '2019-05-11 11:45:33');
INSERT INTO `user` VALUES (4, '石傻傻', '222333', 1, 0, '2019-05-11 11:38:29', '2019-05-11 11:45:23');
INSERT INTO `user` VALUES (5, '上星星', '111222', 0, 0, '2019-05-11 11:39:09', '2019-05-11 11:39:09');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
