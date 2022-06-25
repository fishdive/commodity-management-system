/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-05-27 17:21:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ssm_crud_user
-- ----------------------------
DROP TABLE IF EXISTS `ssm_crud_user`;
CREATE TABLE `ssm_crud_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，不为空，唯一',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '密码，为空时给默认值123456',
  `grade` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '02' COMMENT '等级：01管理员，02普通用户(默认为02)',
  `state` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '01' COMMENT '状态：01启用，02弃用(默认为01)',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '自身' COMMENT '创建该账户的角色，分为自己注册和管理员创建',
  `modifier` varchar(20) DEFAULT '未知用户' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of ssm_crud_user
-- ----------------------------
INSERT INTO `ssm_crud_user` VALUES ('1', 'admin', 'padmin', '01', '01', '自己', '未知用户');
INSERT INTO `ssm_crud_user` VALUES ('2', '李四', '123456', '02', '01', 'admin', 'admin');
INSERT INTO `ssm_crud_user` VALUES ('3', 'Tom', '123456', '02', '01', 'admin', 'admin');
INSERT INTO `ssm_crud_user` VALUES ('9', '马武', '123456', '02', '01', 'admin', '未知用户');
INSERT INTO `ssm_crud_user` VALUES ('10', '六儿', '123456', '02', '01', 'admin', 'admin');
INSERT INTO `ssm_crud_user` VALUES ('17', 'qier', '123456', '02', '01', 'admin', 'admin');
INSERT INTO `ssm_crud_user` VALUES ('18', '八九', '123456', '02', '01', 'admin', '未知用户');
INSERT INTO `ssm_crud_user` VALUES ('19', '十', '123456', '02', '01', 'admin', '未知用户');
INSERT INTO `ssm_crud_user` VALUES ('20', '11', '123456', '02', '01', 'admin', '未知用户');
INSERT INTO `ssm_crud_user` VALUES ('21', '12', '123456', '02', '02', 'admin', '未知用户');
