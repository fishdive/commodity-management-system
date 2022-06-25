/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-05-27 17:47:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ssm_crud_goods
-- ----------------------------
DROP TABLE IF EXISTS `ssm_crud_goods`;
CREATE TABLE `ssm_crud_goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(1000) DEFAULT NULL COMMENT '商品标题',
  `price` varchar(100) DEFAULT NULL COMMENT '商品价格',
  `image` varchar(1000) DEFAULT NULL COMMENT '商品图片',
  `brand` varchar(100) DEFAULT NULL COMMENT '商品品牌',
  `user_id` int NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of ssm_crud_goods
-- ----------------------------
INSERT INTO `ssm_crud_goods` VALUES ('1', '1', '1', '/upload/SSM_CRUD/20220527165547_toxiang.jpg', '1', '17');
INSERT INTO `ssm_crud_goods` VALUES ('2', '2', '2', '/upload/SSM_CRUD/20220527165731_toxiang.jpg', '2', '17');
INSERT INTO `ssm_crud_goods` VALUES ('3', '3', '3', '/upload/SSM_CRUD/20220527165943_toxiang.jpg', '3', '17');
