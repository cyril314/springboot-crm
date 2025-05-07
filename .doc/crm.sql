/*
SQLyog v10.2 
MySQL - 5.7.9 : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `crm`;

/*Table structure for table `bas_dict` */

DROP TABLE IF EXISTS `bas_dict`;

CREATE TABLE `bas_dict` (
  `dict_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '字典编号',
  `dict_type` varchar(50) NOT NULL COMMENT '字典类别',
  `dict_item` varchar(50) NOT NULL COMMENT '字典条目',
  `dict_value` varchar(50) NOT NULL COMMENT '字典数据',
  `dict_is_editable` varchar(2) NOT NULL COMMENT '是否可以编辑',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `bas_dict` */

insert  into `bas_dict`(`dict_id`,`dict_type`,`dict_item`,`dict_value`,`dict_is_editable`) values (1,'地区','北京','北京','是');

/*Table structure for table `cst_customer` */

DROP TABLE IF EXISTS `cst_customer`;

CREATE TABLE `cst_customer` (
  `cust_no` varchar(17) NOT NULL COMMENT '客户编号',
  `cust_name` varchar(100) NOT NULL COMMENT '客户名称',
  `cust_region` varchar(50) DEFAULT NULL COMMENT '所在地区',
  `cust_manager_id` int(20) NOT NULL COMMENT '客户经理编号',
  `cust_level` int(20) DEFAULT NULL COMMENT '客户等级',
  `cust_satisfy` int(20) DEFAULT NULL COMMENT '客户满意度',
  `cust_credit` int(20) DEFAULT NULL COMMENT '客户信任度',
  `cust_addr` varchar(300) DEFAULT NULL COMMENT '客户住址',
  `cust_zip` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `cust_tel` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `cust_fax` varchar(50) DEFAULT NULL COMMENT '传真号码',
  `cust_website` varchar(50) DEFAULT NULL COMMENT '网站地址',
  `cust_licence_no` varchar(50) DEFAULT NULL COMMENT '营业执照号码',
  `cust_chieftain` varchar(50) DEFAULT NULL COMMENT '法人代表',
  `cust_bankroll` int(20) DEFAULT NULL COMMENT '注册资金',
  `cust_turnover` int(20) DEFAULT NULL COMMENT '年营业额',
  `cust_bank` varchar(200) DEFAULT NULL COMMENT '客户银行',
  `cust_bank_account` varchar(50) DEFAULT NULL COMMENT '银行号码',
  `cust_local_tax_no` varchar(50) DEFAULT NULL COMMENT '地税登记号',
  `cust_national_tax_no` varchar(50) DEFAULT NULL COMMENT '国税登记号',
  `cust_status` varchar(1) DEFAULT NULL COMMENT '客户状态',
  PRIMARY KEY (`cust_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cst_customer` */

/*Table structure for table `cst_linkman` */

DROP TABLE IF EXISTS `cst_linkman`;

CREATE TABLE `cst_linkman` (
  `lkm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人编号',
  `lkm_cust_no` varchar(17) NOT NULL COMMENT '客户编号',
  `lkm_name` varchar(50) NOT NULL COMMENT '联系人姓名',
  `lkm_sex` varchar(5) DEFAULT NULL COMMENT '联系人性别',
  `lkm_postion` varchar(50) DEFAULT NULL COMMENT '联系人职位',
  `lkm_tel` varchar(50) NOT NULL COMMENT '办公电话',
  `lkm_mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `lkm_memo` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`lkm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cst_linkman` */

/*Table structure for table `cst_manager` */

DROP TABLE IF EXISTS `cst_manager`;

CREATE TABLE `cst_manager` (
  `man_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户经理编号',
  `man_name` varchar(20) NOT NULL DEFAULT '' COMMENT '客户经理姓名',
  PRIMARY KEY (`man_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `cst_manager` */

insert  into `cst_manager`(`man_id`,`man_name`) values (1,'庞小庆'),(2,'江波'),(3,'胡波'),(4,'李庆华'),(5,'刘大伟'),(6,'张佩军'),(7,'李大维'),(8,'胡勇'),(9,'张达'),(10,'杨晓波');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `odr_id` bigint(20) NOT NULL DEFAULT '0',
  `odr_customer` varchar(100) NOT NULL DEFAULT '',
  `odr_date` datetime NOT NULL,
  `odr_addr` varchar(200) DEFAULT '',
  `odr_status` char(1) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`odr_id`,`odr_customer`,`odr_date`,`odr_addr`,`odr_status`) values (1,'珠海拱北万家百货','2008-01-19 00:00:00','广东省珠海市拱北','6'),(2,'珠海拱北万家百货','2008-02-19 00:00:00','广东省珠海市拱北','1'),(3,'珠海拱北万家百货','2008-03-19 00:00:00','广东省珠海市拱北','1'),(4,'珠海拱北万家百货','2008-04-19 00:00:00','广东省珠海市拱北','5'),(5,'珠海拱北万家百货','2008-05-19 00:00:00','广东省珠海市拱北','1'),(6,'珠海新一佳百货公司','2008-01-12 00:00:00','广东省珠海市香洲区南坑市场对面','1'),(7,'珠海新一佳百货公司','2008-02-12 00:00:00','广东省珠海市香洲区南坑市场对面','6'),(8,'珠海新一佳百货公司','2008-03-12 00:00:00','广东省珠海市香洲区南坑市场对面','1'),(9,'珠海新一佳百货公司','2008-04-12 00:00:00','广东省珠海市香洲区南坑市场对面','5'),(10,'珠海新一佳百货公司','2008-05-12 00:00:00','广东省珠海市香洲区南坑市场对面','1');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `prod_name` varchar(200) NOT NULL COMMENT '产品名称',
  `prod_type` varchar(100) NOT NULL COMMENT '产品型号',
  `prod_batch` varchar(100) DEFAULT NULL COMMENT '产品批次',
  `prod_unit` varchar(10) DEFAULT NULL COMMENT '产品单位',
  `prod_price` double DEFAULT NULL COMMENT '产品单价',
  `prod_memo` varchar(200) DEFAULT NULL COMMENT '产品备注',
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

/*Table structure for table `sal_chance` */

DROP TABLE IF EXISTS `sal_chance`;

CREATE TABLE `sal_chance` (
  `chc_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机会编号',
  `chc_source` varchar(50) DEFAULT '' COMMENT '机会来源',
  `chc_cust_name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户名称',
  `chc_title` varchar(200) NOT NULL DEFAULT '' COMMENT '概要信息',
  `chc_rate` int(11) NOT NULL DEFAULT '0' COMMENT '成功机率',
  `chc_linkman` varchar(50) DEFAULT '' COMMENT '联系人',
  `chc_tel` varchar(50) DEFAULT '' COMMENT '电话号码',
  `chc_desc` text NOT NULL COMMENT '机会描述',
  `chc_create_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `chc_create_date` varchar(20) NOT NULL DEFAULT '(getdate())' COMMENT '创建时间',
  `chc_due_to` varchar(50) DEFAULT '' COMMENT '实施人',
  `chc_due_date` varchar(20) DEFAULT '' COMMENT '实施时间',
  `chc_status` char(10) NOT NULL DEFAULT '(1)' COMMENT '机会状态',
  PRIMARY KEY (`chc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sal_chance` */

insert  into `sal_chance`(`chc_id`,`chc_source`,`chc_cust_name`,`chc_title`,`chc_rate`,`chc_linkman`,`chc_tel`,`chc_desc`,`chc_create_by`,`chc_create_date`,`chc_due_to`,`chc_due_date`,`chc_status`) values (18,'朋友介绍','珠海房产网','想买笔记本电脑',90,'刘小明','123456789','希望采购50台笔记本 <br>','admin','2009年12月13日',NULL,NULL,'1'),(19,'老乡介绍','中国人才资源中心','希望采购100台台式机',99,'莫小妹','789456123','&nbsp;希望采购100台台式机','admin','2009年12月13日',NULL,NULL,'1'),(20,'业务来往','珠海旅游局','采购10台笔记本',99,'小王','123456789','','admin','2009年12月13日',NULL,NULL,'1');

/*Table structure for table `sal_plan` */

DROP TABLE IF EXISTS `sal_plan`;

CREATE TABLE `sal_plan` (
  `pla_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计划编号',
  `pla_chc_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '销售机会编号',
  `pla_date` varchar(20) NOT NULL DEFAULT '' COMMENT '计划时间',
  `pla_todo` text NOT NULL COMMENT '计划实施说明',
  `pla_result` text COMMENT '计划结果',
  PRIMARY KEY (`pla_id`),
  KEY `FK_sal_plan_sal_chance` (`pla_chc_id`),
  CONSTRAINT `FK_sal_plan_sal_chance` FOREIGN KEY (`pla_chc_id`) REFERENCES `sal_chance` (`chc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sal_plan` */

/*Table structure for table `storage` */

DROP TABLE IF EXISTS `storage`;

CREATE TABLE `storage` (
  `stk_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存编号',
  `stk_prod_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品编号',
  `stk_warehouse` varchar(50) NOT NULL DEFAULT '' COMMENT '库存名称',
  `stk_ware` varchar(50) NOT NULL DEFAULT '' COMMENT '货物位置',
  `stk_count` int(11) NOT NULL DEFAULT '0' COMMENT '库存数量',
  `stk_memo` varchar(200) DEFAULT '' COMMENT '备注信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `storage` */

insert  into `storage`(`stk_id`,`stk_prod_id`,`stk_warehouse`,`stk_ware`,`stk_count`,`stk_memo`) values (5,1,'北京-西直门库','EC-D2',20,''),(6,2,'北京-西直门库','EC-D3',25,''),(7,4,'北京-西直门库','EC-D5',15,''),(8,7,'北京-大钟寺库','EA-B8',5,''),(9,10,'北京-大钟寺库','EA-B5',8,''),(10,11,'北京-大钟寺库','EA-B4',9,''),(11,12,'北京-马甸库','EA-A2',11,''),(12,13,'北京-马甸库','EA-A3',11,'好棒哦'),(13,14,'北京-马甸库','EA-A4',12,''),(14,15,'北京-马甸库','EA-A5',11,'');

/*Table structure for table `sys_right` */

DROP TABLE IF EXISTS `sys_right`;

CREATE TABLE `sys_right` (
  `right_code` bigint(20) NOT NULL AUTO_INCREMENT,
  `right_text` varchar(50) DEFAULT '',
  `right_url` varchar(100) DEFAULT '',
  PRIMARY KEY (`right_code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `sys_right` */

insert  into `sys_right`(`right_code`,`right_text`,`right_url`) values (1,'销售机会管理','/sale.do'),(2,'客户开发计划','/plan.do'),(3,'客户信息管理','/customer.do'),(4,'客户流失管理','/cstLost.do'),(5,'客户服务管理','/cstService.do'),(6,'统计报表','/report.do'),(7,'数据字典管理','/basdict.do'),(8,'查询产品信息','/product.do'),(9,'查询库存','/storage.do'),(10,'权限管理','/right.do'),(11,'用户管理','/userinfo.do'),(12,'角色管理','/role.do');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL DEFAULT '',
  `role_desc` varchar(50) DEFAULT '',
  `role_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_desc`,`role_flag`) values (1,'系统管理员','最高权限',1),(2,'销售主管','客户管理、营销管理以及统计报表',1),(3,'客户经理','客户管理、基础数据以及统计报表',1),(4,'董事会董事','查看统计报表',1),(6,'销售员','营销管理',NULL);

/*Table structure for table `sys_role_right` */

DROP TABLE IF EXISTS `sys_role_right`;

CREATE TABLE `sys_role_right` (
  `r_r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `right_id` bigint(20) NOT NULL DEFAULT '0',
  `roles_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`r_r_id`),
  KEY `FK_Sys_role_right_sys_role` (`roles_id`),
  KEY `FK_sys_role_right_sys_right` (`right_id`),
  CONSTRAINT `FK_Sys_role_right_sys_role` FOREIGN KEY (`roles_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sys_role_right_sys_right` FOREIGN KEY (`right_id`) REFERENCES `sys_right` (`right_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_right` */

insert  into `sys_role_right`(`r_r_id`,`right_id`,`roles_id`) values (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `usr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usr_name` varchar(50) NOT NULL DEFAULT '',
  `usr_password` varchar(50) NOT NULL DEFAULT '',
  `usr_role_id` bigint(20) DEFAULT '0',
  `usr_flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`usr_id`),
  KEY `FK_sys_user_sys_role` (`usr_role_id`),
  CONSTRAINT `FK_sys_user_sys_role` FOREIGN KEY (`usr_role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`usr_id`,`usr_name`,`usr_password`,`usr_role_id`,`usr_flag`) values (1,'admin','123456',1,1),(2,'laven','123',1,1),(3,'wangqinghai','pwd',2,1),(4,'huangcaiyi','s',3,1),(5,'huanglonghui','pwd',4,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
