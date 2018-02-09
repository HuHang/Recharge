/*
Navicat MySQL Data Transfer

Source Server         : reopre1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : recharge

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-02-09 15:31:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `callfare`
-- ----------------------------
DROP TABLE IF EXISTS `callfare`;
CREATE TABLE `callfare` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `businessType` int(3) unsigned NOT NULL COMMENT '业务类型',
  `packageName` varchar(255) NOT NULL COMMENT '名称',
  `validTime` varchar(255) NOT NULL COMMENT '有效期',
  `standardPrice` int(11) NOT NULL COMMENT '标准价',
  `discount` double(8,3) DEFAULT '1.000' COMMENT '折扣',
  `qCellCoreCode` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '归属地',
  `packageType` int(3) unsigned NOT NULL COMMENT '包类型',
  `itemId` varchar(255) NOT NULL COMMENT '商品编码',
  `itemName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `isMobile` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '移动可用否',
  `isTelecom` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '电信可用否',
  `isUnicom` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '联通可用否',
  `gmtCreate` datetime NOT NULL COMMENT '创建时间',
  `gmtModified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of callfare
-- ----------------------------
INSERT INTO `callfare` VALUES ('2', '100', '10元', '', '10', '1.000', '0', '100', '6606', null, '0', '1', '1', '2018-01-22 14:14:02', '2018-01-22 14:14:02');
INSERT INTO `callfare` VALUES ('3', '100', '20元', '', '20', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:16:58', '2018-01-22 14:16:58');
INSERT INTO `callfare` VALUES ('4', '100', '30元', '', '30', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:17:14', '2018-01-22 14:17:14');
INSERT INTO `callfare` VALUES ('5', '100', '40元', '', '40', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:17:24', '2018-01-22 14:17:24');
INSERT INTO `callfare` VALUES ('6', '100', '50元', '', '50', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:17:34', '2018-01-22 14:17:34');
INSERT INTO `callfare` VALUES ('7', '100', '100元', '', '100', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:17:48', '2018-01-22 14:17:48');
INSERT INTO `callfare` VALUES ('8', '100', '200元', '', '200', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:17:57', '2018-01-22 14:17:57');
INSERT INTO `callfare` VALUES ('9', '100', '300元', '', '300', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:18:06', '2018-01-22 14:18:06');
INSERT INTO `callfare` VALUES ('10', '100', '500元', '', '500', '1.000', '0', '100', '6606', null, '1', '1', '1', '2018-01-22 14:18:15', '2018-01-22 14:18:15');
INSERT INTO `callfare` VALUES ('12', '100', '600元', '', '500', '1.000', '0', '200', '6606', null, '0', '1', '0', '2018-01-23 18:04:53', '2018-01-23 18:04:53');

-- ----------------------------
-- Table structure for `dataflow`
-- ----------------------------
DROP TABLE IF EXISTS `dataflow`;
CREATE TABLE `dataflow` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `businessType` int(3) unsigned NOT NULL COMMENT '业务类型',
  `packageName` varchar(255) NOT NULL COMMENT '名称',
  `packageSize` int(11) NOT NULL DEFAULT '0' COMMENT '包体',
  `validTime` varchar(255) NOT NULL COMMENT '有效期',
  `discount` double(8,3) DEFAULT '1.000' COMMENT '折扣',
  `qCellCoreCode` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '归属地',
  `packageType` int(3) unsigned NOT NULL COMMENT '包类型',
  `itemId` varchar(255) NOT NULL COMMENT '商品编码',
  `itemName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `mobilePrice` int(11) DEFAULT NULL COMMENT '移动价格',
  `telecomPrice` int(11) DEFAULT NULL COMMENT '电信价格',
  `unicomPrice` int(11) DEFAULT NULL COMMENT '联通价格',
  `isMobile` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '移动可用否',
  `isTelecom` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '电信可用否',
  `isUnicom` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '联通可用否',
  `gmtCreate` datetime NOT NULL COMMENT '创建时间',
  `gmtModified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dataflow
-- ----------------------------
INSERT INTO `dataflow` VALUES ('6', '200', '5M', '5', '月底失效', null, '0', '100', '6606', null, '0', '1', '0', '0', '1', '0', '2018-01-23 16:07:05', '2018-01-23 16:07:05');
INSERT INTO `dataflow` VALUES ('7', '200', '10M', '10', '月底失效', null, '0', '100', '6606', null, '3', '2', '0', '1', '1', '0', '2018-01-23 16:09:05', '2018-01-23 16:09:05');
INSERT INTO `dataflow` VALUES ('8', '200', '30M', '30', '月底失效', null, '0', '100', '6606', null, '5', '4', '5', '1', '1', '1', '2018-01-23 16:10:08', '2018-01-23 16:10:08');
INSERT INTO `dataflow` VALUES ('9', '200', '50M', '50', '月底失效', null, '0', '100', '6606', null, '5', '7', '6', '0', '1', '1', '2018-01-23 16:11:34', '2018-01-23 16:11:34');
INSERT INTO `dataflow` VALUES ('10', '200', '70M', '70', '月底失效', null, '0', '100', '6606', null, '10', '0', '0', '1', '0', '0', '2018-01-23 16:12:18', '2018-01-23 16:12:18');
INSERT INTO `dataflow` VALUES ('11', '200', '100M', '100', '月底失效', null, '0', '100', '6606', null, '0', '10', '10', '0', '1', '1', '2018-01-23 16:13:21', '2018-01-23 16:13:21');
INSERT INTO `dataflow` VALUES ('12', '200', '150M', '150', '月底失效', null, '0', '100', '6606', null, '20', '0', '0', '1', '0', '0', '2018-01-23 16:13:53', '2018-01-23 16:13:53');
INSERT INTO `dataflow` VALUES ('13', '200', '200M', '200', '月底失效', null, '0', '100', '6606', null, '0', '15', '15', '0', '1', '1', '2018-01-23 16:14:34', '2018-01-23 16:14:34');
INSERT INTO `dataflow` VALUES ('14', '200', '300M', '300', '月底失效', null, '0', '100', '6606', null, '0', '20', '20', '0', '1', '1', '2018-01-23 16:15:01', '2018-01-23 16:15:01');
INSERT INTO `dataflow` VALUES ('15', '200', '500M', '500', '月底失效', null, '0', '100', '6606', null, '30', '30', '30', '1', '1', '1', '2018-01-23 16:15:28', '2018-01-23 16:15:28');
INSERT INTO `dataflow` VALUES ('16', '200', '1G', '1024', '月底失效', null, '0', '100', '6606', null, '50', '50', '50', '1', '1', '1', '2018-01-23 16:15:54', '2018-01-23 16:15:54');
INSERT INTO `dataflow` VALUES ('17', '200', '2G', '2048', '月底失效', null, '0', '100', '6606', null, '70', '70', '90', '1', '1', '1', '2018-01-23 16:16:12', '2018-01-23 16:16:12');
INSERT INTO `dataflow` VALUES ('18', '200', '3G', '3072', '月底失效', null, '0', '100', '6606', null, '100', '100', '120', '1', '1', '1', '2018-01-23 16:16:37', '2018-01-23 16:16:37');
INSERT INTO `dataflow` VALUES ('19', '200', '4G', '4096', '月底失效', null, '0', '100', '6606', null, '130', '0', '0', '1', '0', '0', '2018-01-23 16:17:01', '2018-01-23 16:17:01');
INSERT INTO `dataflow` VALUES ('20', '200', '6G', '5120', '月底失效', null, '0', '100', '6606', null, '180', '0', '0', '1', '0', '0', '2018-01-23 16:17:17', '2018-01-23 16:17:17');
INSERT INTO `dataflow` VALUES ('21', '200', '11G', '11264', '月底失效', null, '0', '100', '6606', null, '280', '0', '0', '1', '0', '0', '2018-01-23 16:17:28', '2018-01-23 16:17:28');
INSERT INTO `dataflow` VALUES ('22', '200', '1G', '1024', '当日有效', null, '13', '200', '6606', null, '0', '5', '0', '0', '1', '0', '2018-01-23 16:23:36', '2018-01-23 16:23:36');
INSERT INTO `dataflow` VALUES ('23', '200', '1G', '1024', '3日有效', null, '13', '200', '6606', null, '0', '10', '0', '0', '1', '0', '2018-01-23 16:29:12', '2018-01-23 16:29:12');
INSERT INTO `dataflow` VALUES ('24', '200', '1G', '1024', '7日有效', null, '13', '200', '6606', null, '0', '20', '0', '0', '1', '0', '2018-01-23 16:29:46', '2018-01-23 16:29:46');
INSERT INTO `dataflow` VALUES ('25', '200', '不限量', '1048576', '当月有效', null, '13', '200', '6606', null, '0', '99', '0', '0', '1', '0', '2018-01-23 16:30:12', '2018-01-23 16:30:12');
INSERT INTO `dataflow` VALUES ('31', '200', '8G混合(1G全国+7G省内)', '8192', '当月有效', null, '13', '200', '6606', null, '0', '50', '0', '0', '1', '0', '2018-01-23 19:50:33', '2018-01-23 19:50:33');

-- ----------------------------
-- Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standardDiscount` double(8,3) NOT NULL,
  `businessType` int(3) unsigned NOT NULL,
  `gmtCreate` datetime NOT NULL,
  `gmtModified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `businessType` (`businessType`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES ('2', '0.998', '100', '2018-01-22 15:56:04', '2018-01-23 21:20:53');
INSERT INTO `discount` VALUES ('3', '0.980', '200', '2018-01-22 15:58:18', '2018-01-23 21:21:32');

-- ----------------------------
-- Table structure for `recharge_order`
-- ----------------------------
DROP TABLE IF EXISTS `recharge_order`;
CREATE TABLE `recharge_order` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `businessType` int(3) unsigned NOT NULL DEFAULT '0' COMMENT '业务类型',
  `packageId` bigint(11) NOT NULL,
  `serialNumber` varchar(255) NOT NULL COMMENT '流水号',
  `mobileTel` varchar(12) NOT NULL COMMENT '充值手机号',
  `qCellCoreCode` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '归属地',
  `operatorCode` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '运营商',
  `itemId` varchar(255) NOT NULL COMMENT '商品编码',
  `itemName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `transactionSum` double(8,2) NOT NULL COMMENT '交易金额',
  `costPrice` double(8,2) NOT NULL COMMENT '平台成本',
  `paymentNumber` varchar(255) DEFAULT NULL COMMENT '支付单号',
  `rechargeNumber` varchar(255) DEFAULT NULL COMMENT '充值单号',
  `state` int(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态',
  `rechargeState` varchar(255) DEFAULT NULL COMMENT '充值状态',
  `describtion` varchar(255) NOT NULL COMMENT '描述',
  `gmtCreate` datetime NOT NULL COMMENT '创建时间',
  `gmtModified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `serialNumber` (`serialNumber`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1100012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recharge_order
-- ----------------------------
INSERT INTO `recharge_order` VALUES ('1100000', '200', '6', '20180130164700936', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '301', null, '支付中', '2018-01-30 16:47:01', '2018-01-30 16:47:01');
INSERT INTO `recharge_order` VALUES ('1100001', '200', '6', '20180130165101428', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '301', null, '支付中', '2018-01-30 16:51:01', '2018-01-30 16:51:02');
INSERT INTO `recharge_order` VALUES ('1100002', '200', '6', '20180130165246238', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-30 16:52:46', '2018-01-30 16:52:47');
INSERT INTO `recharge_order` VALUES ('1100003', '200', '6', '20180130165256578', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-30 16:52:57', '2018-01-31 11:37:41');
INSERT INTO `recharge_order` VALUES ('1100004', '200', '6', '20180130171156420', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-30 17:11:56', '2018-01-30 17:12:04');
INSERT INTO `recharge_order` VALUES ('1100005', '200', '6', '20180130171721632', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-30 17:17:22', '2018-01-30 17:17:40');
INSERT INTO `recharge_order` VALUES ('1100006', '200', '6', '20180130171809918', '17765198634', '31', '10000', '6606', null, '0.98', '0.98', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-30 17:18:10', '2018-01-30 17:18:17');
INSERT INTO `recharge_order` VALUES ('1100007', '100', '6', '20180131005903530', '17765198634', '31', '10000', '6606', null, '50.00', '50.00', null, null, '501', null, 'C:\\Users\\Administrator\\Desktop\\crt\\shc.e.0403.crt (系统找不到指定的路径。)', '2018-01-31 00:59:04', '2018-01-31 00:59:06');
INSERT INTO `recharge_order` VALUES ('1100008', '100', '6', '20180131010011521', '17765198634', '31', '10000', '6606', null, '50.00', '50.00', null, null, '501', null, 'C:\\Users\\Administrator\\Desktop\\crt\\shc.e.0403.crt (系统找不到指定的路径。)', '2018-01-31 01:00:12', '2018-01-31 01:00:12');
INSERT INTO `recharge_order` VALUES ('1100009', '100', '6', '20180131010016882', '17765198634', '31', '10000', '6606', null, '10.00', '10.00', null, null, '501', null, 'C:\\Users\\Administrator\\Desktop\\crt\\shc.e.0403.crt (系统找不到指定的路径。)', '2018-01-31 01:00:17', '2018-01-31 01:00:17');
INSERT INTO `recharge_order` VALUES ('1100010', '100', '6', '201801311140588985934528846019', '17765198634', '31', '10000', '6606', null, '10.00', '10.00', null, null, '501', null, '{\"error_code\":\"96112029\",\"error_msg\":\"验证签名失败\",\"return_code\":\"2\"}', '2018-01-31 11:40:59', '2018-01-31 11:41:00');
INSERT INTO `recharge_order` VALUES ('1100011', '100', '6', '201801311141588038021317698757', '17765198634', '31', '10000', '6606', null, '10.00', '10.00', null, null, '501', null, '', '2018-01-31 11:41:59', '2018-02-09 11:28:39');
