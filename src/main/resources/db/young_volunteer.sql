-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: young_volunteer
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `association_member`
--

DROP TABLE IF EXISTS `association_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `association_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_code` varchar(255) NOT NULL,
  `member_name` varchar(255) NOT NULL,
  `association_id` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28e2gcgsko3erl63bidv8oj44` (`association_id`),
  KEY `FKpyt0d54mrepby8xniarvv7vqi` (`user_user_id`),
  CONSTRAINT `FK28e2gcgsko3erl63bidv8oj44` FOREIGN KEY (`association_id`) REFERENCES `volunteer_association` (`id`),
  CONSTRAINT `FKpyt0d54mrepby8xniarvv7vqi` FOREIGN KEY (`user_user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `association_member`
--

LOCK TABLES `association_member` WRITE;
/*!40000 ALTER TABLE `association_member` DISABLE KEYS */;
INSERT INTO `association_member` (`id`, `member_code`, `member_name`, `association_id`, `user_user_id`) VALUES (1,'MEMBER001','MEMBER001d',NULL,NULL),(2,'MEMBER002','MEMBER002',NULL,NULL),(17,'ASS003','ASS003',NULL,NULL),(18,'ASS004','ASS004',NULL,NULL);
/*!40000 ALTER TABLE `association_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colleges`
--

DROP TABLE IF EXISTS `colleges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colleges` (
  `colleges_id` int(11) NOT NULL AUTO_INCREMENT,
  `colleges_name` varchar(255) DEFAULT NULL,
  `colleges_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`colleges_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colleges`
--

LOCK TABLES `colleges` WRITE;
/*!40000 ALTER TABLE `colleges` DISABLE KEYS */;
INSERT INTO `colleges` (`colleges_id`, `colleges_name`, `colleges_code`) VALUES (1,'大数据与软件工程学院','A001'),(2,'教师教育学院','A002'),(3,'阿姆斯特朗回旋喷气式阿姆斯特朗加速炮','A003');
/*!40000 ALTER TABLE `colleges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin`
--

DROP TABLE IF EXISTS `sys_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL,
  `admin_code` varchar(255) DEFAULT NULL COMMENT '编号',
  `admin_email` varchar(255) DEFAULT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_phone` varchar(255) DEFAULT NULL,
  `admin_sex` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `FK25tl0pfope4w9g5ofei7stf4t` (`user_user_id`),
  CONSTRAINT `FK25tl0pfope4w9g5ofei7stf4t` FOREIGN KEY (`user_user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `href` varchar(255) NOT NULL,
  `spread` tinyint(1) DEFAULT '0',
  `roleType` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `title`, `icon`, `href`, `spread`, `roleType`) VALUES (1,'学院管理','&#xe630;',' ',0,'1'),(2,'活动管理','&#xe630;',' ',0,'1'),(3,'报名管理','&#xe630;',' ',0,'1'),(4,'签到管理','&#xe630;',' ',0,'1'),(5,'打分管理','&#xe630;',' ',0,'1'),(6,'志愿者管理','&#xe630;',' ',0,'1'),(7,'协会成员管理','&#xe630;',' ',0,'1'),(8,'志愿者协会管理','&#xe630;',' ',0,'1'),(9,'签到管理','&#xe630;',' ',0,'2'),(10,'打分管理','&#xe630;',' ',0,'2'),(11,'报名管理','&#xe630;',' ',0,'3');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `href` varchar(255) NOT NULL,
  `spread` tinyint(1) DEFAULT '0',
  `fatherMenu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA8243AED1D1BC4B6` (`fatherMenu_id`),
  CONSTRAINT `FKA8243AED1D1BC4B6` FOREIGN KEY (`fatherMenu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` (`id`, `title`, `icon`, `href`, `spread`, `fatherMenu_id`) VALUES (1,'学院列表','&#xe61c;','/YoungVolunteer_war/page/system/colleges/collegesList.html',0,1),(2,'活动列表','&#xe61c;','/YoungVolunteer_war/page/system/activity/activityList.html',0,2),(3,'已报名活动','&#xe61c;','/YoungVolunteer_war/page/system/signup/signUpactivityList.html',0,3),(4,'未报名活动','&#xe61c;','/YoungVolunteer_war/page/system/signup/noSignUpactivityList.html',0,3),(5,'已签到列表','&#xe61c;','/YoungVolunteer_war/page/system/signin/signInList.html',0,4),(6,'未签到列表','&#xe61c;','/YoungVolunteer_war/page/system/signin/noSignInList.html',0,4),(7,'未打分列表','&#xe61c;','/YoungVolunteer_war/page/system/score/noScoreList.html',0,5),(8,'已打分列表','&#xe61c;','/YoungVolunteer_war/page/system/score/scoreList.html',0,5),(9,'志愿者列表','&#xe61c;','/YoungVolunteer_war/page/system/volunteer/volunteerList.html',0,6),(10,'成员列表','&#xe61c;','/YoungVolunteer_war/page/system/member/memberList.html',0,7),(11,'协会列表','&#xe61c;','/YoungVolunteer_war/page/system/association/associationList.html',0,8),(12,'未签到列表','&#xe61c;','/YoungVolunteer_war/page/system/signin/noSignInList.html',0,9),(13,'已签到列表','&#xe61c;','/YoungVolunteer_war/page/system/signin/signInList.html',0,9),(14,'已打分列表','&#xe61c;','/YoungVolunteer_war/page/system/score/scoreList.html',0,10),(15,'未打分列表','&#xe61c;','/YoungVolunteer_war/page/system/score/noScoreList.html',0,10),(16,'已报名活动','&#xe61c;','/YoungVolunteer_war/page/system/signup/signUpactivityList.html',0,11),(17,'未报名活动','&#xe61c;','/YoungVolunteer_war/page/system/signup/noSignUpactivityList.html',0,11);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `role_type` int(11) DEFAULT NULL,
  `user_available` int(2) NOT NULL DEFAULT '1' COMMENT '1:可用 2：封号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1852687116 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `login_account`, `login_password`, `user_name`, `role_type`, `user_available`) VALUES (1,'admin','admin','麦奇',1,1),(1253036129,'biaogejiushibiao@outlook.com','123456','麦奇',3,1),(1530213922,'123@qq.com','123456','里奥',3,1),(1530642815,'666','123456','666',3,1),(1531136494,'3333','123456','333',3,1),(1532261255,'4444','123456','4444',3,1),(1533084331,'111','123456','111',3,1),(1533578338,'111','123456','111',3,1),(1533818403,'111','123456','111',3,1),(1533880944,'222','123456','222',3,1),(1534070060,'333','123456','333',3,1),(1534337231,'232','123456','2323',3,1),(1534595611,'666','123456','666',3,1),(1534924687,'11111','123456','1111',3,1),(1534955638,'2222','123456','2222',3,1),(1535287456,'121212','123456','121212',3,1),(1852687115,'ASS004','123456','ASS004',2,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_volunteer`
--

DROP TABLE IF EXISTS `sys_volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_volunteer` (
  `volunteer_id` int(11) NOT NULL AUTO_INCREMENT,
  `volunteer_code` varchar(255) DEFAULT NULL COMMENT '编号',
  `volunteer_class` varchar(255) DEFAULT NULL COMMENT '班级',
  `volunteer_college` varchar(255) DEFAULT NULL COMMENT '学院',
  `volunteer_join_time` date DEFAULT NULL COMMENT '加入时间',
  `volunteer_email` varchar(255) DEFAULT NULL,
  `volunteer_name` varchar(255) DEFAULT NULL,
  `volunteer_phone` varchar(255) DEFAULT NULL,
  `volunteer_sex` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`volunteer_id`),
  KEY `FKkvn6xjfb7auiknrvwv04hw63i` (`user_user_id`),
  CONSTRAINT `FKkvn6xjfb7auiknrvwv04hw63i` FOREIGN KEY (`user_user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1535287457 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_volunteer`
--

LOCK TABLES `sys_volunteer` WRITE;
/*!40000 ALTER TABLE `sys_volunteer` DISABLE KEYS */;
INSERT INTO `sys_volunteer` (`volunteer_id`, `volunteer_code`, `volunteer_class`, `volunteer_college`, `volunteer_join_time`, `volunteer_email`, `volunteer_name`, `volunteer_phone`, `volunteer_sex`, `user_user_id`) VALUES (1,'V001','软件五班','大数据与软件工程学院','2019-06-06','biaogejiushibiao@outlook.com','麦奇','18276297824',1,NULL),(1534924687,'111111','11111','11111','2019-06-11','11111','1111','1111',0,NULL),(1534955638,'2222','2222','2222','2019-06-11','2222','2222','2222',1,NULL),(1535287456,'12121','12121','1212212','2019-06-03','121212','121212','1212121',1,NULL);
/*!40000 ALTER TABLE `sys_volunteer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer_activity`
--

DROP TABLE IF EXISTS `volunteer_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunteer_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(255) DEFAULT NULL COMMENT '编号',
  `activity_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `activity_content` varchar(5000) DEFAULT NULL COMMENT '内容',
  `activity_people_num` int(11) DEFAULT NULL COMMENT '人数',
  `activity_leader` varchar(255) DEFAULT NULL COMMENT '负责人',
  `activity_start_time` date DEFAULT NULL COMMENT '开始时间',
  `activity_end_time` date DEFAULT NULL COMMENT '结束时间',
  `activity_sign_start_time` date DEFAULT NULL COMMENT '报名开始时间',
  `activity_sign_end_time` date DEFAULT NULL COMMENT '报名结束时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer_activity`
--

LOCK TABLES `volunteer_activity` WRITE;
/*!40000 ALTER TABLE `volunteer_activity` DISABLE KEYS */;
INSERT INTO `volunteer_activity` (`activity_id`, `activity_code`, `activity_title`, `activity_content`, `activity_people_num`, `activity_leader`, `activity_start_time`, `activity_end_time`, `activity_sign_start_time`, `activity_sign_end_time`) VALUES (3,'A001','活动1','活动1',100,'活动1','2019-05-29','2019-06-06','2019-06-24','2019-06-10'),(4,'A002','A002','A002',2,'A002','2019-06-07','2019-06-18','2019-06-11','2019-06-25');
/*!40000 ALTER TABLE `volunteer_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer_association`
--

DROP TABLE IF EXISTS `volunteer_association`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunteer_association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `association_code` varchar(255) NOT NULL,
  `association_name` varchar(255) NOT NULL,
  `colleges_colleges_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yj8rvwdmrvqiwy50ha4lr1ts` (`colleges_colleges_id`),
  CONSTRAINT `FK7yj8rvwdmrvqiwy50ha4lr1ts` FOREIGN KEY (`colleges_colleges_id`) REFERENCES `colleges` (`colleges_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer_association`
--

LOCK TABLES `volunteer_association` WRITE;
/*!40000 ALTER TABLE `volunteer_association` DISABLE KEYS */;
INSERT INTO `volunteer_association` (`id`, `association_code`, `association_name`, `colleges_colleges_id`) VALUES (1,'ASS001','乒乓球协会',NULL),(2,'ASS002','志愿者青年协会',NULL);
/*!40000 ALTER TABLE `volunteer_association` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer_sign_up`
--

DROP TABLE IF EXISTS `volunteer_sign_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunteer_sign_up` (
  `sign_up_id` int(11) NOT NULL AUTO_INCREMENT,
  `sign_in` int(11) DEFAULT NULL COMMENT '签到',
  `volunteer_score` double DEFAULT NULL COMMENT '评分',
  `volunteer_id` varchar(255) DEFAULT NULL COMMENT '志愿者id',
  `activity_id` varchar(255) DEFAULT NULL COMMENT '活动id',
  `activity_name` varchar(255) DEFAULT NULL,
  `volunteerName` varchar(255) DEFAULT NULL,
  `sign_in_time` datetime DEFAULT NULL,
  `sign_up_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sign_up_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='报名表-志愿者中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer_sign_up`
--

LOCK TABLES `volunteer_sign_up` WRITE;
/*!40000 ALTER TABLE `volunteer_sign_up` DISABLE KEYS */;
INSERT INTO `volunteer_sign_up` (`sign_up_id`, `sign_in`, `volunteer_score`, `volunteer_id`, `activity_id`, `activity_name`, `volunteerName`, `sign_in_time`, `sign_up_time`) VALUES (1,1,66,'1253036129','3','TestActivity','麦奇',NULL,NULL),(11,NULL,NULL,'1253036129','4','A002','麦奇',NULL,NULL);
/*!40000 ALTER TABLE `volunteer_sign_up` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-19 14:36:53
