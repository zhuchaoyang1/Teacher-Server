/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : teachingsys
Target Host     : localhost:3306
Target Database : teachingsys
Date: 2019-10-30 23:51:38
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_books
-- ----------------------------
INSERT INTO `course_books` VALUES ('0000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/courseBooks/d204e5ad-5604-49b7-8017-983b5bd4656d傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `course_books` VALUES ('0000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/courseBooks/9e6d752b-5aea-48c1-9dc4-edaa16fc0c8a丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edu_papers
-- ----------------------------
INSERT INTO `edu_papers` VALUES ('00000000002', '3', '1', '1', '1', '2019-09-23', '1', '1', '/upload/eduPapers/8e2b6772-c702-472f-b9c7-b67db571c3e7傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `edu_papers` VALUES ('00000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/eduPapers/a4d31f94-b6b2-44f1-9c6a-96277ec5dc7f丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edu_ref
-- ----------------------------
INSERT INTO `edu_ref` VALUES ('00000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/educationalReform/eduRef/42912361-7e0f-4e5d-a54e-13b48becf6c9傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `edu_ref` VALUES ('00000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/educationalReform/eduRef/09a188d9-28e3-4252-9ff7-bb7a3d0f32c6丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inschool_expre
-- ----------------------------
INSERT INTO `inschool_expre` VALUES ('00000000008', '3', '1', '1', '1', '2019-09-09', '1', '1', '/upload/experiments/in/4e272418-3f32-4d7c-996c-6ed300e446d5傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `inschool_expre` VALUES ('00000000009', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/experiments/in/5dd28f0b-7e23-453f-893c-98e14ac75fea丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lab_contrust
-- ----------------------------
INSERT INTO `lab_contrust` VALUES ('0000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/labContrust/7d6e6672-6130-447a-9edc-60768f977988傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `lab_contrust` VALUES ('0000000003', '3', '2', '2', '1', '2019-09-30', '1', '2', '/upload/labContrust/acd9026a-d209-495c-9ad3-c6615261483c傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `lab_contrust` VALUES ('0000000004', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/labContrust/55828851-9e7e-41e3-a664-be2394322fd6丁老师文件测试.docx', '丁老师文件测试.docx');
INSERT INTO `lab_contrust` VALUES ('0000000005', '4', '3', '3', '3', '2019-09-30', '3', '3', '/upload/labContrust/6dbbd9d4-28b0-4f52-838b-9e285e071db4院领导文件测试.xlsx', '院领导文件测试.xlsx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outschool_expre
-- ----------------------------
INSERT INTO `outschool_expre` VALUES ('00000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/experiments/out/e55c0232-fd3f-4081-ab28-bd5ced2009be傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `outschool_expre` VALUES ('00000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/experiments/out/05b8fe93-995f-4206-a5a4-3799383b0379丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_teacher
-- ----------------------------
INSERT INTO `personal_teacher` VALUES ('00000000049', '3', '1', '1', '1', '2019-09-02', '1', '1', '/upload/teachers/personal/0d0cc4ff-09c3-48d0-a7ca-61e8236c74f3傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `personal_teacher` VALUES ('00000000050', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/teachers/personal/a9984619-0c71-4bdf-ab39-61792282b8c9丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personal_team
-- ----------------------------
INSERT INTO `personal_team` VALUES ('00000000009', '3', '1', '1', '1', '2019-09-23', '1', '1', '/upload/teachers/teams/080fe929-63d1-40d5-ac99-9431ee339e2a傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `personal_team` VALUES ('00000000010', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/teachers/teams/03768eb2-5591-4fa3-bcd5-e17be3217629丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profess_con
-- ----------------------------
INSERT INTO `profess_con` VALUES ('0000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/professCon/29032138-583a-47bb-9dca-10f7d25b0958傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `profess_con` VALUES ('0000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/professCon/0bc8094f-1c9b-4725-a0a1-fd53a7b9fc9a丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_compet
-- ----------------------------
INSERT INTO `stu_compet` VALUES ('0000000008', '3', '1', '1', '1', '2019-09-16', '1', '1', '/upload/stuCompet/6226ebd0-385e-4978-b35d-8b6fe69bdea5傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `stu_compet` VALUES ('0000000009', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/stuCompet/8764afcb-d464-4cb3-8a79-cd99bffbdca6丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_outcome
-- ----------------------------
INSERT INTO `stu_outcome` VALUES ('0000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/stuOutComes/29d71989-fe24-4232-8b74-11729de3d37c傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `stu_outcome` VALUES ('0000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/stuOutComes/e1e44290-d9ed-4489-8dc1-096e40007e25丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_service
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3', '16200135231', 'a587bf9631ebebd2270146ccc96d9020', '2', '傅老师', '计算机科学与技术');
INSERT INTO `sys_user` VALUES ('4', '16200', '5e1f128ae6bd71960aa75fdd584b9db5', '1', '某院长', '');
INSERT INTO `sys_user` VALUES ('5', '16200135232', 'd6820ee6c1a1029f685277e96ac98870', '2', '丁老师', '计算机科学与技术');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teaching_achieve
-- ----------------------------
INSERT INTO `teaching_achieve` VALUES ('0000000002', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/teachingAchieve/ca91c4e2-fc6d-40c6-936b-8e5660aa0385傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `teaching_achieve` VALUES ('0000000003', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/teachingAchieve/5da766c0-4302-46eb-9c6a-ccfc32b6558c丁老师文件测试.docx', '丁老师文件测试.docx');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training_pro
-- ----------------------------
INSERT INTO `training_pro` VALUES ('0000000004', '3', '1', '1', '1', '2019-09-30', '1', '1', '/upload/trainingPro/aae11f00-a9f1-4baa-957f-7c16dac7ac55傅老师文件.txt', '傅老师文件.txt');
INSERT INTO `training_pro` VALUES ('0000000005', '5', '2', '2', '2', '2019-09-30', '2', '2', '/upload/trainingPro/8e2ed767-88c6-4f28-9f9b-d79e4ee13303丁老师文件测试.docx', '丁老师文件测试.docx');
