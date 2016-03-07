/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : trainingmanager

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-03-07 23:53:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminuser
-- ----------------------------
DROP TABLE IF EXISTS `adminuser`;
CREATE TABLE `adminuser` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminuser
-- ----------------------------
INSERT INTO `adminuser` VALUES ('1', 'admin', 'admin');
INSERT INTO `adminuser` VALUES ('5', 'admin2', 'admin2');
INSERT INTO `adminuser` VALUES ('6', 'admin2', 'admin2');

-- ----------------------------
-- Table structure for base_exist
-- ----------------------------
DROP TABLE IF EXISTS `base_exist`;
CREATE TABLE `base_exist` (
  `id` int(11) NOT NULL auto_increment,
  `tid` int(11) default NULL,
  `linkman_name` varchar(20) default NULL,
  `linkman_phone` varchar(20) default NULL,
  `num` int(11) default NULL,
  `existyear` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `base_tid_fk` (`tid`),
  CONSTRAINT `base_tid_fk` FOREIGN KEY (`tid`) REFERENCES `training_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_exist
-- ----------------------------
INSERT INTO `base_exist` VALUES ('1', '1', '冯林', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('2', '1', 'aa', '18683341827', '2', '2014');
INSERT INTO `base_exist` VALUES ('3', '1', 'bb', '18683341827', '3', '2013');
INSERT INTO `base_exist` VALUES ('4', '2', '时光守护者', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('5', '2', '德玛西亚', '18683341827', '5', '2014');
INSERT INTO `base_exist` VALUES ('6', '3', '维恩', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('7', '3', '赵信', '18683341827', '5', '2014');
INSERT INTO `base_exist` VALUES ('8', '4', '雷霆咆哮大狗熊', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('9', '4', '炼金术士', '18683341827', '5', '2014');
INSERT INTO `base_exist` VALUES ('10', '5', '龙女', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('11', '5', '刀妹', '18683341827', '5', '2014');
INSERT INTO `base_exist` VALUES ('12', '6', '男刀', '18683341827', '5', '2015');
INSERT INTO `base_exist` VALUES ('13', '7', '阿狸', '18683341827', '5', '2014');
INSERT INTO `base_exist` VALUES ('14', '8', 'd', 'e', '0', '2014');
INSERT INTO `base_exist` VALUES ('15', '9', 'å¾·çè¥¿äº', '132452134123', '0', '2015');
INSERT INTO `base_exist` VALUES ('16', '10', '444444444444', '444444444', '0', '2015');
INSERT INTO `base_exist` VALUES ('17', '11', 'asdf', '34123412', '0', '2015');
INSERT INTO `base_exist` VALUES ('18', '12', 'å¾·çè¥¿äº', '12341234123', '0', '2015');
INSERT INTO `base_exist` VALUES ('19', '13', '1', '111111111', '0', '2015');
INSERT INTO `base_exist` VALUES ('20', '14', '德玛西亚', '134123441', '0', '2015');
INSERT INTO `base_exist` VALUES ('21', '15', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('22', '16', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('23', '17', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('24', '18', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('25', '19', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('26', '20', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('27', '21', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('28', '22', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('29', '23', '', '', '0', '2015');
INSERT INTO `base_exist` VALUES ('30', '24', '萨科类', '123412341', '0', '2016');
INSERT INTO `base_exist` VALUES ('31', '25', '阿克苏的肌肤', '155209002341', '0', '2016');
INSERT INTO `base_exist` VALUES ('32', '26', null, null, '0', '2016');
INSERT INTO `base_exist` VALUES ('33', '27', '阿斯蒂芬', '', '0', '2016');
INSERT INTO `base_exist` VALUES ('34', '28', '', '', '0', '2016');
INSERT INTO `base_exist` VALUES ('35', '29', null, null, '0', '2016');
INSERT INTO `base_exist` VALUES ('36', '30', null, null, null, null);

-- ----------------------------
-- Table structure for basic_settings
-- ----------------------------
DROP TABLE IF EXISTS `basic_settings`;
CREATE TABLE `basic_settings` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(50) NOT NULL,
  `skey` varchar(50) NOT NULL,
  `svalue` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_settings
-- ----------------------------
INSERT INTO `basic_settings` VALUES ('1', '实训时间', 'startDate', '2016-01-01', '实习实训开始日期');
INSERT INTO `basic_settings` VALUES ('2', '实训时间', 'endDate', '2016-05-07', '实习实训结束日期');

-- ----------------------------
-- Table structure for free_training_base
-- ----------------------------
DROP TABLE IF EXISTS `free_training_base`;
CREATE TABLE `free_training_base` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `linkerName` varchar(50) default NULL,
  `linkerPhone` varchar(50) default NULL,
  `teacherName` varchar(50) default NULL,
  `teacherPhone` varchar(50) default NULL,
  `addTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of free_training_base
-- ----------------------------
INSERT INTO `free_training_base` VALUES ('1', 'aa', 'bb', 'cc', 'dd', 'ee', 'ff', null);
INSERT INTO `free_training_base` VALUES ('2', null, null, null, null, null, null, '2016-03-07 22:52:39');
INSERT INTO `free_training_base` VALUES ('3', '1', '2', '3', '4', '5', '6', '2016-03-07 23:07:15');
INSERT INTO `free_training_base` VALUES ('4', '2', '3', '4', '5', '6', '7', '2016-03-07 23:12:51');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL auto_increment,
  `pid` int(11) default NULL,
  `name` varchar(50) default NULL,
  `url` varchar(255) default NULL,
  `cssClass` varchar(50) default NULL,
  `theOrder` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '学生管理', null, 'fa fa-user', '1');
INSERT INTO `menu` VALUES ('2', '0', '实训基地管理', null, 'fa fa-server', '2');
INSERT INTO `menu` VALUES ('3', '1', '学生中心', 'student_list.action', 'fa fa-circle-o', '1');
INSERT INTO `menu` VALUES ('4', '1', '历届学生', null, 'fa fa-circle-o', '2');
INSERT INTO `menu` VALUES ('5', '1', '学生统计', null, 'fa fa-circle-o', '3');
INSERT INTO `menu` VALUES ('6', '1', '实训补助', null, 'fa fa-circle-o', '4');
INSERT INTO `menu` VALUES ('7', '2', '实训基地', 'trainingBase_list.action', 'fa fa-circle-o', '1');
INSERT INTO `menu` VALUES ('8', '2', '增加基地', 'trainingBase_addUI.action', 'fa fa-circle-o', '2');
INSERT INTO `menu` VALUES ('9', '2', '基地统计', null, 'fa fa-circle-o', '3');
INSERT INTO `menu` VALUES ('10', '0', '系统设置', '', 'fa fa-cog', '3');
INSERT INTO `menu` VALUES ('11', '10', '菜单信息', 'menu_list.action', 'fa fa-circle-o', '1');
INSERT INTO `menu` VALUES ('21', '0', '测试一级菜单', '', 'fa fa-cog', '4');
INSERT INTO `menu` VALUES ('23', '10', '角色信息', 'role_list.action', 'fa fa-circle-o', '2');
INSERT INTO `menu` VALUES ('24', '10', '系统配置', ' basicSettings_setUI.action', 'fa fa-circle-o', '3');
INSERT INTO `menu` VALUES ('25', '0', '个人中心', '', 'fa fa-user', '5');
INSERT INTO `menu` VALUES ('27', '25', '选择基地', 'student_chooseTrainingBaseUI.action', 'fa fa-circle-o', '2');
INSERT INTO `menu` VALUES ('28', '25', '我的信息', 'student_infoUI.action', 'fa fa-circle-o', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '教师角色');
INSERT INTO `role` VALUES ('2', '学生角色');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `mId` int(11) NOT NULL,
  `rId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('5', '1');
INSERT INTO `role_menu` VALUES ('8', '1');
INSERT INTO `role_menu` VALUES ('2', '1');
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('10', '1');
INSERT INTO `role_menu` VALUES ('6', '1');
INSERT INTO `role_menu` VALUES ('7', '1');
INSERT INTO `role_menu` VALUES ('23', '1');
INSERT INTO `role_menu` VALUES ('9', '1');
INSERT INTO `role_menu` VALUES ('3', '1');
INSERT INTO `role_menu` VALUES ('24', '1');
INSERT INTO `role_menu` VALUES ('21', '1');
INSERT INTO `role_menu` VALUES ('11', '1');
INSERT INTO `role_menu` VALUES ('4', '1');
INSERT INTO `role_menu` VALUES ('3', '2');
INSERT INTO `role_menu` VALUES ('11', '2');
INSERT INTO `role_menu` VALUES ('7', '2');
INSERT INTO `role_menu` VALUES ('10', '2');
INSERT INTO `role_menu` VALUES ('2', '2');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('28', '2');
INSERT INTO `role_menu` VALUES ('25', '2');
INSERT INTO `role_menu` VALUES ('23', '2');
INSERT INTO `role_menu` VALUES ('27', '2');
INSERT INTO `role_menu` VALUES ('24', '2');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `username` varchar(20) default NULL,
  `password` varchar(32) default NULL,
  `classname` varchar(20) default NULL,
  `email` varchar(30) default NULL,
  `address` varchar(30) default NULL,
  `phone` varchar(11) default NULL,
  `tid` int(11) default NULL,
  `estimate` int(11) default NULL,
  `addtime` datetime NOT NULL,
  `grade` int(11) default NULL,
  `institute` int(11) default NULL,
  `state` int(11) default NULL,
  `trainingType` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `student_tid_fk` (`tid`),
  CONSTRAINT `student_tid_fk` FOREIGN KEY (`tid`) REFERENCES `training_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('12330401', '用户1', '12330401', '12软工班', null, '用户1', null, '1', '0', '2016-02-21 16:44:59', '2015', '1', '0', '1');
INSERT INTO `student` VALUES ('12330402', '用户2', '12330402', '12软工班', null, null, null, '1', null, '2016-02-21 17:27:05', '2015', '1', '1', '0');
INSERT INTO `student` VALUES ('12330403', '用户3', '12330403', '12软工班', null, null, null, '1', null, '2016-02-21 17:39:59', '2015', '1', '1', '0');
INSERT INTO `student` VALUES ('12330404', '德玛西亚2', '12330404', '12软工班', null, null, null, '1', null, '2016-02-21 18:23:59', '2015', '1', '0', '0');
INSERT INTO `student` VALUES ('12330414', '用户14', '12330414', '12软工班', null, null, null, '1', '0', '2016-02-21 16:54:08', '2015', '1', '0', '0');
INSERT INTO `student` VALUES ('12330415', '德玛西亚2', '12330415', '12软工班', null, null, null, '1', null, '2016-02-21 18:23:25', '2015', '1', '0', '0');
INSERT INTO `student` VALUES ('16330401', '德玛西亚', '16330401', '12软工班', null, null, null, '1', null, '2016-02-21 18:21:12', '2015', '1', '1', '0');
INSERT INTO `student` VALUES ('16330402', '德玛西亚', '16330402', '12软工班', null, null, null, '1', null, '2016-02-21 18:26:30', '2016', '1', '0', '0');
INSERT INTO `student` VALUES ('16330414', '德玛西亚2', '16330414', '12软工班', null, null, null, '1', null, '2016-02-21 18:22:24', '2015', '1', '1', '0');

-- ----------------------------
-- Table structure for subsidy
-- ----------------------------
DROP TABLE IF EXISTS `subsidy`;
CREATE TABLE `subsidy` (
  `id` int(11) NOT NULL auto_increment,
  `sid` int(11) default NULL,
  `amount` float default NULL,
  `issue` tinyint(4) default NULL,
  PRIMARY KEY  (`id`),
  KEY `subsidy_sid_fk` (`sid`),
  CONSTRAINT `subsidy_sid_fk` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subsidy
-- ----------------------------

-- ----------------------------
-- Table structure for training_base
-- ----------------------------
DROP TABLE IF EXISTS `training_base`;
CREATE TABLE `training_base` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `address` varchar(50) default NULL,
  `addtime` datetime default NULL,
  `linkername` varchar(50) default NULL,
  `linkerphone` varchar(50) default NULL,
  `num` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training_base
-- ----------------------------
INSERT INTO `training_base` VALUES ('1', '达内', '国内最好的培训机构', '成都', '2015-07-08 00:41:29', '冯林', '18683341827', '0');
INSERT INTO `training_base` VALUES ('2', '国信安', '国家信息安全总局', '成都市天府软件园', '2015-07-09 01:08:20', '时光守护者', '18683341827', '0');
INSERT INTO `training_base` VALUES ('3', '糖果时代', '中国最小的android培训机构', '成都市', '2015-07-09 00:08:20', '维恩', '18683341827', '0');
INSERT INTO `training_base` VALUES ('4', '睿峰', '技术为王的培训机构', '成都市', '2015-07-09 00:08:20', '雷霆咆哮大狗熊', '18683341827', '0');
INSERT INTO `training_base` VALUES ('5', '华迪', '成都知名网络培训机构', '成都市', '2015-07-09 00:08:20', '龙女', '18683341827', '0');
INSERT INTO `training_base` VALUES ('6', '新华电脑', '中国职业计算机培训机构', '成都市', '2015-07-09 00:08:20', '男刀', '18683341827', '0');
INSERT INTO `training_base` VALUES ('7', '蓝桥杯', '北京javaEE培训机构', '北京市', '2015-07-09 00:08:20', '阿狸', '18683341827', '0');
INSERT INTO `training_base` VALUES ('8', 'a', 'c', 'b', '2015-10-11 17:24:30', 'd', 'e', '0');
INSERT INTO `training_base` VALUES ('9', 'ç­å¾·å°å', 'é¿è¨å¾·åææè¨ççº çº·æè¨çåçå¤§å¹', 'ç­å¾·å°åæ¯çµä¿¡4åº', '2015-10-11 17:33:33', 'å¾·çè¥¿äº', '132452134123', '0');
INSERT INTO `training_base` VALUES ('10', '1', '3', '2', '2015-10-11 19:41:06', '444444444444', '444444444', '0');
INSERT INTO `training_base` VALUES ('11', 'ä½ å¥½', 'sadfasdf', '2341', '2015-10-11 19:41:30', 'asdf', '34123412', '0');
INSERT INTO `training_base` VALUES ('12', 'è¾æ¬§å°¼äº', 'å¤§ç¥çèéå°', 'çµä¿¡ä¸åº', '2015-10-11 19:43:08', 'å¾·çè¥¿äº', '12341234123', '0');
INSERT INTO `training_base` VALUES ('13', '阿萨德发送', '1', '1', '2015-10-11 19:44:13', '1', '111111111', '0');
INSERT INTO `training_base` VALUES ('14', '艾欧尼亚', '大神的聚集地', '电信一区', '2015-10-11 19:44:54', '德玛西亚', '134123441', '0');
INSERT INTO `training_base` VALUES ('15', '', '', '', '2015-10-19 22:07:23', '', '', '0');
INSERT INTO `training_base` VALUES ('16', '', '', '', '2015-10-19 22:07:34', '', '', '0');
INSERT INTO `training_base` VALUES ('17', '', '', '', '2015-12-07 21:12:23', '', '', '0');
INSERT INTO `training_base` VALUES ('18', '', '', '', '2015-12-21 20:50:56', '', '', '0');
INSERT INTO `training_base` VALUES ('19', '', '', '', '2015-12-21 20:51:00', '', '', '0');
INSERT INTO `training_base` VALUES ('20', '', '', '', '2015-12-24 20:55:58', '', '', '0');
INSERT INTO `training_base` VALUES ('21', '', '', '', '2015-12-24 21:08:22', '', '', '0');
INSERT INTO `training_base` VALUES ('22', '', '', '', '2015-12-24 21:08:39', '', '', '0');
INSERT INTO `training_base` VALUES ('23', '', '', '', '2015-12-24 21:16:56', '', '', '0');
INSERT INTO `training_base` VALUES ('24', '操你妈基地', '阿斯蒂芬卡拉圣诞节反抗垃圾的说法', '四川成都', '2016-02-18 21:10:02', '萨科类', '123412341', '0');
INSERT INTO `training_base` VALUES ('25', '阿斯顿开了飞机', '啊快乐圣诞节反抗拉萨地方阿萨德路口附近阿萨德卢卡斯的积分', '喀什地方', '2016-02-21 11:23:12', '阿克苏的肌肤', '155209002341', '0');
INSERT INTO `training_base` VALUES ('26', '', null, '啊短发散发, , ', '2016-02-21 12:42:35', null, null, '0');
INSERT INTO `training_base` VALUES ('27', '', '', '', '2016-02-21 12:42:56', '阿斯蒂芬', '', '0');
INSERT INTO `training_base` VALUES ('28', '', '', '', '2016-02-21 16:02:39', '', '', '0');
INSERT INTO `training_base` VALUES ('29', '12330401', null, '用户1', '2016-02-21 16:44:08', null, null, '0');
INSERT INTO `training_base` VALUES ('30', 'aaaaaaa', 'aaaaaaaaaaaaaaa', 'aaaaaaaaa', '2016-03-06 21:43:22', 'aaaaaa', 'aaaaaaaaaaaa', '0');
