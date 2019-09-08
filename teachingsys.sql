/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : teachingsys
Target Host     : localhost:3306
Target Database : teachingsys
Date: 2019-09-09 01:41:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for course_books
-- ----------------------------
DROP TABLE IF EXISTS `course_books`;
CREATE TABLE `course_books` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_books
-- ----------------------------
INSERT INTO `course_books` VALUES ('0000000001', '4', 'f', 'f', 'f', '2019-09-23', 'f', 'f', '/upload/courseBooks/3344bf28-fd9e-4246-a046-d7dcbd5b6e61构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt', '构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt');

-- ----------------------------
-- Table structure for edu_papers
-- ----------------------------
DROP TABLE IF EXISTS `edu_papers`;
CREATE TABLE `edu_papers` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edu_papers
-- ----------------------------
INSERT INTO `edu_papers` VALUES ('00000000001', '4', '1', '2', '2', '2019-09-24', '2', '2', '/upload/eduPapers/89843bb3-2eab-40c2-8480-d52f6a745a79新建文本文档 (2).txt', '新建文本文档 (2).txt');

-- ----------------------------
-- Table structure for edu_ref
-- ----------------------------
DROP TABLE IF EXISTS `edu_ref`;
CREATE TABLE `edu_ref` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `support` varchar(100) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `filepath` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edu_ref
-- ----------------------------
INSERT INTO `edu_ref` VALUES ('00000000001', '4', '2', '2', '2', '2019-08-29', '2', '2', '/upload/educationalReform/eduRef/d64cda3f-b1e8-45c1-b485-83ecba4f085a新建文本文档.txt', '新建文本文档.txt');

-- ----------------------------
-- Table structure for inschool_expre
-- ----------------------------
DROP TABLE IF EXISTS `inschool_expre`;
CREATE TABLE `inschool_expre` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `support` varchar(100) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `filepath` varchar(300) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inschool_expre
-- ----------------------------
INSERT INTO `inschool_expre` VALUES ('00000000007', '4', '123', '123', '123', '2019-08-13', '123', '123', '/upload/experiments/in/98878afb-dd4f-4ceb-bd8c-5d65c732c82bnginx.conf', 'nginx.conf');

-- ----------------------------
-- Table structure for lab_contrust
-- ----------------------------
DROP TABLE IF EXISTS `lab_contrust`;
CREATE TABLE `lab_contrust` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lab_contrust
-- ----------------------------
INSERT INTO `lab_contrust` VALUES ('0000000001', '4', '2', '2', '2', '2019-09-17', '2', '2', '/upload/labContrust/0e6c68b0-e172-4c39-b255-840690b8f78e新建文本文档.txt', '新建文本文档.txt');

-- ----------------------------
-- Table structure for outschool_expre
-- ----------------------------
DROP TABLE IF EXISTS `outschool_expre`;
CREATE TABLE `outschool_expre` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `support` varchar(100) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `filepath` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outschool_expre
-- ----------------------------
INSERT INTO `outschool_expre` VALUES ('00000000001', '4', '1', '1', '1', '2019-08-29', '1', '1', '/upload/experiments/out/7df0f1a8-c815-4adb-b276-ce0946405821新建文本文档.txt', '新建文本文档.txt');

-- ----------------------------
-- Table structure for personal_teacher
-- ----------------------------
DROP TABLE IF EXISTS `personal_teacher`;
CREATE TABLE `personal_teacher` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '0',
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `support` varchar(100) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `filepath` varchar(300) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `personal_teacher_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_teacher
-- ----------------------------
INSERT INTO `personal_teacher` VALUES ('00000000047', '4', '1', '1', '1', '2019-08-30', '1', '1', '/upload/teachers/personal/bb3a99c9-24ba-4092-a2e2-0ce7e5c68c8e1564930974(1).jpg', '1564930974(1).jpg');

-- ----------------------------
-- Table structure for personal_team
-- ----------------------------
DROP TABLE IF EXISTS `personal_team`;
CREATE TABLE `personal_team` (
  `p_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `support` varchar(100) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `filepath` varchar(300) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_team
-- ----------------------------
INSERT INTO `personal_team` VALUES ('00000000008', '4', '2', '2', '2', '2019-08-27', '2', '2', '/upload/teachers/teams/dc0aadee-7629-4865-9fcd-0c23df7b7e1e智慧城市数据挖掘.txt', '智慧城市数据挖掘.txt');

-- ----------------------------
-- Table structure for profess_con
-- ----------------------------
DROP TABLE IF EXISTS `profess_con`;
CREATE TABLE `profess_con` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profess_con
-- ----------------------------
INSERT INTO `profess_con` VALUES ('0000000001', '4', 't', 't', 't', '2019-09-15', 't', 't', '/upload/professCon/bed43a38-7347-441d-a1df-6161807d3e6e构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt', '构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt');

-- ----------------------------
-- Table structure for stu_compet
-- ----------------------------
DROP TABLE IF EXISTS `stu_compet`;
CREATE TABLE `stu_compet` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_compet
-- ----------------------------
INSERT INTO `stu_compet` VALUES ('0000000001', '4', '4', '4', '4', '2019-09-26', '4', '4', '/upload/stuCompet/ce916857-568a-4bc8-b4de-9e6c3a4c2de4新建文本文档.txt', '新建文本文档.txt');

-- ----------------------------
-- Table structure for stu_outcome
-- ----------------------------
DROP TABLE IF EXISTS `stu_outcome`;
CREATE TABLE `stu_outcome` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_outcome
-- ----------------------------
INSERT INTO `stu_outcome` VALUES ('0000000001', '4', '1', '1', '2', '2019-09-10', '2', '2', '/upload/stuOutComes/e44faf86-28e1-48d6-8b80-fd6dc2ec87bc新建文本文档 (2).txt', '新建文本文档 (2).txt');

-- ----------------------------
-- Table structure for sys_service
-- ----------------------------
DROP TABLE IF EXISTS `sys_service`;
CREATE TABLE `sys_service` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `uId` int(11) DEFAULT NULL,
  `textarea` varchar(500) DEFAULT NULL,
  `rateCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_service
-- ----------------------------
INSERT INTO `sys_service` VALUES ('00000000001', '3', 'dwq', '0');
INSERT INTO `sys_service` VALUES ('00000000002', '3', 'dwq', '3');
INSERT INTO `sys_service` VALUES ('00000000003', '3', 'few', '4');
INSERT INTO `sys_service` VALUES ('00000000004', '3', 'dwq', '5');
INSERT INTO `sys_service` VALUES ('00000000005', '3', 'dwq', '5');
INSERT INTO `sys_service` VALUES ('00000000006', '3', 'dwq', '2');
INSERT INTO `sys_service` VALUES ('00000000007', null, 'dwq', '4');
INSERT INTO `sys_service` VALUES ('00000000008', null, 'dwq', '1');
INSERT INTO `sys_service` VALUES ('00000000009', null, 'haixing', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '0',
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` int(2) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3', '16200135231', 'a587bf9631ebebd2270146ccc96d9020', '2', '傅老师', '计算机科学与技术');
INSERT INTO `sys_user` VALUES ('4', '16200', '5e1f128ae6bd71960aa75fdd584b9db5', '1', '某院长', '');
INSERT INTO `sys_user` VALUES ('5', '16200135232', 'd6820ee6c1a1029f685277e96ac98870', '2', '丁老师', '智慧建筑专业');
INSERT INTO `sys_user` VALUES ('6', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '管理员', '无');

-- ----------------------------
-- Table structure for teaching_achieve
-- ----------------------------
DROP TABLE IF EXISTS `teaching_achieve`;
CREATE TABLE `teaching_achieve` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teaching_achieve
-- ----------------------------
INSERT INTO `teaching_achieve` VALUES ('0000000001', '4', '8', '8', '8', '2019-10-01', '8', '8', '/upload/teachingAchieve/2215d030-8dee-43ee-aebe-3cc41439e762新建文本文档 (2).txt', '新建文本文档 (2).txt');

-- ----------------------------
-- Table structure for training_pro
-- ----------------------------
DROP TABLE IF EXISTS `training_pro`;
CREATE TABLE `training_pro` (
  `p_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `support` varchar(255) DEFAULT NULL,
  `personal_time` date DEFAULT NULL,
  `personal_level` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training_pro
-- ----------------------------
INSERT INTO `training_pro` VALUES ('0000000001', '4', '2', '2', '2', '2019-09-23', '2', '2', '/upload/trainingPro/71eef6ec-0dab-4e9c-adec-af19f3c6cff7构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt', '构建长效同机制强化校企资源融合提升信息类人才工程应用能力.txt');
INSERT INTO `training_pro` VALUES ('0000000002', '4', '1', '1', '1', '2019-09-04', '1', '1', '/upload/trainingPro/80aad72d-bbbb-49ec-89a7-3da8bf9264fe新建文本文档 (2).txt', '新建文本文档 (2).txt');
