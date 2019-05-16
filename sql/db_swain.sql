/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : db_swain

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2019-05-16 21:24:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `machine`
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES ('1', '一号机', '1', '2', '0', '2019-05-09 19:57:57', '2019-05-16 10:35:12');
INSERT INTO `machine` VALUES ('2', '二号机', '1', '2', '0', '2019-05-09 19:57:57', '2019-05-12 00:23:33');
INSERT INTO `machine` VALUES ('3', '三号机', '1', '2', '1', '2019-05-12 21:35:24', '2019-05-16 21:16:32');
INSERT INTO `machine` VALUES ('4', '三号机', '1', '2', '0', '2019-05-12 21:37:20', '2019-05-16 21:16:38');

-- ----------------------------
-- Table structure for `material`
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('1', '小麦', '2.00', '0', '2019-05-09 20:12:51', '2019-05-12 19:48:53', '1012.00');
INSERT INTO `material` VALUES ('2', '橡胶', '100.00', '0', '2019-05-09 20:14:22', '2019-05-16 13:56:30', '1001.00');
INSERT INTO `material` VALUES ('6', '玉米', '2.00', '0', '2019-05-12 21:44:07', '2019-05-13 00:08:31', '111.00');

-- ----------------------------
-- Table structure for `product`
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '2', '1', '1', '50', '面粉', '25', '0', '2019-05-16 09:05:24', '2019-05-16 19:38:12');

-- ----------------------------
-- Table structure for `repair`
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `repair_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，维修日志id',
  `repair_machine_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '维修机器id',
  `repair_machine_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '机器类型，0-损坏，1-正常',
  `repair_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '维修价格',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `repair_comment` varchar(255) NOT NULL DEFAULT '' COMMENT '维修备注',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('1', '1', '1', '50.00', '0', '2019-05-16 16:12:21', '2019-05-16 19:34:39', '开关坏了');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长，用户id',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '用户密码',
  `permission` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户权限，0-管理员，1-员工',
  `is_delete` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-存在，1-已被删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '0', '0', '2019-05-09 19:29:01', '2019-05-11 11:16:13');
INSERT INTO `user` VALUES ('2', '张三', '123456', '1', '0', '2019-05-09 19:29:01', '2019-05-11 11:16:14');
INSERT INTO `user` VALUES ('3', '李四', '123123', '1', '0', '2019-05-16 19:36:22', '2019-05-16 19:36:50');
INSERT INTO `user` VALUES ('4', '王五', '123321', '1', '0', '2019-05-16 19:36:35', '2019-05-16 19:37:02');
