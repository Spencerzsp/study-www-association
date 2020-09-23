-- ----------------------------
-- Table structure for t_sys_carousel
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_carousel`;
CREATE TABLE `t_sys_carousel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `domain` varchar(255) NOT NULL COMMENT '域名',
  `url` varchar(255) DEFAULT NULL COMMENT '超链接',
  `file_url` varchar(500) DEFAULT NULL COMMENT '文件地址',
  `order` int(11) NOT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `is_delete` bigint(2) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `index_1` (`domain`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_carousel
-- ----------------------------
INSERT INTO `t_sys_carousel` VALUES ('1', '  <p>\n				已为<b style=\"color:#FFB800;font-size: 14px;\">5625</b>名学员提供培训服务   \n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">87090</b>次\n			</p>  ', 'jiaoyu.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/a8937c06-e521-4d15-8e0f-91828fb3e926.jpg', '1', '2020-06-03 16:32:20', '2020-06-22 17:49:01', '管理员', '管理员', '0');
INSERT INTO `t_sys_carousel` VALUES ('2', '       <p>\n				已为<b style=\"color:#FFB800;font-size: 14px;\">3232</b>名学员提供培训服务   \n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">17090</b>次\n			</p>       ', 'huanjing.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/98dddcd3-ea07-4b7f-a079-55c0f4639d69.jpg', '1', '2020-06-04 15:10:53', '2020-07-17 14:49:48', '管理员', '管理员', '0');
INSERT INTO `t_sys_carousel` VALUES ('3', '<p>\r\n				已为<b style=\"color:#FFB800;font-size: 14px;\">3232</b>名学员提供培训服务   \r\n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">17090</b>次\r\n			</p>', 'jianzhu.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/5208b708-ab97-41a7-8e00-f771a8edf307.jpg', '1', '2020-06-04 16:28:27', '2020-06-04 16:30:26', '管理员', '管理员', '0');
INSERT INTO `t_sys_carousel` VALUES ('4', ' <p>\n				已为<b style=\"color:#FFB800;font-size: 14px;\">3232</b>名学员提供培训服务   \n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">17090</b>次\n			</p> ', 'jiaoyu.gynt.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/c629e0fe-ca08-4f05-b762-6731db2d5798.jpg', '1', '2020-06-04 16:44:30', '2020-06-24 14:18:34', '管理员', '管理员', '0');
INSERT INTO `t_sys_carousel` VALUES ('5', '<p>\r\n				已为<b style=\"color:#FFB800;font-size: 14px;\">3232</b>名学员提供培训服务   \r\n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">17090</b>次\r\n			</p>', 'ghana.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/52d4076e-feeb-4d62-9dc2-c96ad467b8d8.jpg', '1', '2020-06-04 17:12:54', '2020-06-04 17:12:54', '管理员', '管理员', '0');
INSERT INTO `t_sys_carousel` VALUES ('6', '<p>\r\n				已为<b style=\"color:#FFB800;font-size: 14px;\">3232</b>名学员提供培训服务   \r\n				   累计观看次数达<b style=\"color:#FFB800;font-size: 14px;\">17090</b>次\r\n			</p>', 'ydyl.shanhaiyh.com', '', 'http://shiant-oss-common.oss-cn-shenzhen.aliyuncs.com/portal/carousel/cbc71646-a338-452f-8ac0-96e757d72ec8.jpg', '1', '2020-06-04 17:29:04', '2020-06-04 17:29:04', '管理员', '管理员', '0');
