/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : donate_pay

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 31/05/2019 22:02:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dt_pay
-- ----------------------------
DROP TABLE IF EXISTS `dt_pay`;
CREATE TABLE `dt_pay`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appeal_id` int(11) DEFAULT NULL COMMENT '求助Id',
  `created_user_id` int(11) DEFAULT NULL COMMENT '捐赠人Id',
  `modified_user_id` int(11) DEFAULT NULL COMMENT '修改人Id',
  `receiver_id` int(11) DEFAULT NULL COMMENT '受赠者Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '呼吁标题',
  `money` decimal(10, 2) DEFAULT NULL COMMENT '捐赠金额',
  `custom` int(4) DEFAULT NULL COMMENT '是否自定义',
  `pay_state` int(4) DEFAULT NULL COMMENT '状态，0：审核通过；1：审核中；2拒绝',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '祝福语',
  `gmt_created` datetime(0) DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改日期',
  `pay_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自定义金额生成四位数随机标识',
  `is_deleted` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_pay
-- ----------------------------
INSERT INTO `dt_pay` VALUES ('022eed65-f245-4319-8adf-bdb242a8feaa', 21, 6, NULL, 4, '公益万里行——捐赠暖校园', 0.10, NULL, 1, 'demo测试', '2019-05-29 16:16:11', '2019-05-29 16:16:11', NULL, 0);
INSERT INTO `dt_pay` VALUES ('057d8b1d-5194-4b0a-b25f-1a35280ba968', 25, 6, NULL, 6, 'demo测试', 0.10, NULL, 1, '05311测试', '2019-05-31 18:47:16', '2019-05-31 18:47:16', NULL, 0);
INSERT INTO `dt_pay` VALUES ('0795d839-cb9d-455b-9fd8-89615c4a6705', 20, 5, NULL, 6, '校园爱心，帮扶他人', 0.10, NULL, 1, '5623', '2019-05-27 14:28:23', '2019-05-27 14:28:23', NULL, 0);
INSERT INTO `dt_pay` VALUES ('129005f2-7766-4aa7-9356-6273fec090c0', 26, 6, NULL, 2, '0530测试', 0.10, NULL, 0, '', '2019-05-30 21:15:58', '2019-05-30 21:15:58', NULL, 0);
INSERT INTO `dt_pay` VALUES ('16e2c065-c71e-4dfb-8417-da539f43e3aa', 21, 1, NULL, 4, '公益万里行——捐赠暖校园', 0.10, NULL, 1, '213231', '2019-05-29 15:37:40', '2019-05-29 15:37:40', NULL, 0);
INSERT INTO `dt_pay` VALUES ('1ff27d83-4b77-492a-92e9-a5aa81657fe1', 18, 5, NULL, 2, '我想活着', 0.10, NULL, 1, '我想活着', '2019-05-27 13:58:03', '2019-05-27 13:58:03', NULL, 0);
INSERT INTO `dt_pay` VALUES ('22b98e7c-3a5e-4bea-9f1a-396e7e8010fd', 18, 2, NULL, 2, '我想活着', 0.10, NULL, 1, '我想活着', '2019-05-29 10:42:06', '2019-05-29 10:42:06', NULL, 0);
INSERT INTO `dt_pay` VALUES ('22dce2c1-0a2f-45f1-a18b-96f793da88e3', 15, 2, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 0, '213324342', '2019-05-26 18:43:24', '2019-05-26 18:43:24', NULL, 0);
INSERT INTO `dt_pay` VALUES ('25297547-5180-462a-a9b9-909b4bf6128b', 15, 6, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 1, '123', '2019-05-30 22:44:37', '2019-05-30 22:44:37', NULL, 0);
INSERT INTO `dt_pay` VALUES ('27018b16-ead3-477c-af09-7e65cde6a611', 21, 6, NULL, 2, '公益万里行——捐赠暖校园', 0.10, NULL, 1, '111', '2019-05-30 21:24:27', '2019-05-30 21:24:27', NULL, 0);
INSERT INTO `dt_pay` VALUES ('28743822-7076-42d8-b99e-9c397c8bd2e6', 17, 6, NULL, 3, '爱心涌动 情满校园', 0.10, NULL, 1, '', '2019-05-30 22:46:47', '2019-05-30 22:46:47', NULL, 0);
INSERT INTO `dt_pay` VALUES ('2b898f93-c74b-48fb-ab03-db6fd45d0f4f', 15, 5, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 1, '你好', '2019-05-27 11:46:01', '2019-05-27 11:46:01', NULL, 0);
INSERT INTO `dt_pay` VALUES ('31740a63-0e45-44ba-baf6-f8f7b1204686', 26, 6, NULL, 2, '0530测试', 0.10, NULL, 1, '', '2019-05-30 21:16:12', '2019-05-30 21:16:12', NULL, 0);
INSERT INTO `dt_pay` VALUES ('36ae3365-7e5a-4dfb-b02d-af133822e5f5', 15, 2, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 0, '213324342', '2019-05-26 18:42:58', '2019-05-26 18:42:58', NULL, 0);
INSERT INTO `dt_pay` VALUES ('3d41d637-9b8c-46f6-b5e0-75aa3996434d', 25, 4, NULL, 6, 'demo测试', 0.10, NULL, 0, 'www', '2019-05-31 01:23:32', '2019-05-31 01:23:32', NULL, 0);
INSERT INTO `dt_pay` VALUES ('40f9f979-df22-4b83-a39b-356bf6b0f9f4', 17, 5, NULL, 3, '爱心涌动 情满校园', 0.10, NULL, 1, '校园', '2019-05-27 14:47:35', '2019-05-27 14:47:35', NULL, 0);
INSERT INTO `dt_pay` VALUES ('51db17de-dce8-48b0-925e-d048d60d9c34', 22, 1, NULL, 4, '我才二十岁，不想这么离开', 0.10, NULL, 1, '', '2019-05-31 00:48:15', '2019-05-31 00:48:15', NULL, 0);
INSERT INTO `dt_pay` VALUES ('52de317d-439b-48c9-8a8d-55e041b2fd09', 20, 5, NULL, 6, '校园爱心，帮扶他人', 0.10, NULL, 1, '2134342', '2019-05-27 01:08:12', '2019-05-27 01:08:12', NULL, 0);
INSERT INTO `dt_pay` VALUES ('59dd2525-b222-41ea-8583-d0b82a57212e', 26, 6, NULL, 2, '0530测试', 0.10, NULL, 1, '', '2019-05-30 21:23:20', '2019-05-30 21:23:20', NULL, 0);
INSERT INTO `dt_pay` VALUES ('5c4c3a6c-fa7e-4139-9363-7f6930f02972', 22, 1, NULL, 4, '我才二十岁，不想这么离开', 0.10, NULL, 0, '321213', '2019-05-29 15:32:57', '2019-05-29 15:32:57', NULL, 0);
INSERT INTO `dt_pay` VALUES ('65a337fc-6956-4d5c-a973-fb4bd10f8888', 19, 6, NULL, 2, '我要重新站起来', 0.10, NULL, 1, '哈哈哈', '2019-05-30 21:08:23', '2019-05-30 21:08:23', NULL, 0);
INSERT INTO `dt_pay` VALUES ('6829f007-f791-4257-aecf-0872ad2666ab', 13, 5, NULL, 2, '救救孩子', 0.10, NULL, 1, '救救孩子', '2019-05-27 14:35:24', '2019-05-27 14:35:24', NULL, 0);
INSERT INTO `dt_pay` VALUES ('6c022619-edee-4aef-92e1-5fbb1e961e18', 15, 6, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 0, '0527', '2019-05-27 21:41:10', '2019-05-27 21:41:10', NULL, 0);
INSERT INTO `dt_pay` VALUES ('71017b95-8a9c-4192-8626-6ec28fef51fa', 26, 6, NULL, 2, '0530测试', 0.10, NULL, 1, '', '2019-05-30 21:20:33', '2019-05-30 21:20:33', NULL, 0);
INSERT INTO `dt_pay` VALUES ('76b0ae1c-db57-432c-9a4e-cac647de5b4c', 26, 6, NULL, 6, '0530测试', 0.10, NULL, 1, '0.6', '2019-05-31 18:50:39', '2019-05-31 18:50:39', NULL, 0);
INSERT INTO `dt_pay` VALUES ('7cb98766-a1bb-407d-bd10-5dfcafbcf97a', 15, 2, NULL, 3, '爱心捐助 情暖校园', 0.10, NULL, 0, '213324342', '2019-05-26 18:44:45', '2019-05-26 18:44:45', NULL, 0);
INSERT INTO `dt_pay` VALUES ('8b05445b-71fb-47ec-8ba3-d1817d42ce35', 19, 5, NULL, 5, '我要重新站起来', 100.00, NULL, 0, '徐迟', '2019-05-27 09:06:05', '2019-05-27 09:06:05', NULL, 0);
INSERT INTO `dt_pay` VALUES ('8efbdf62-87be-4917-b7f0-1509eda19888', 22, 4, NULL, 4, '我才二十岁，不想这么离开', 0.10, NULL, 0, '1234567', '2019-05-26 23:47:56', '2019-05-26 23:47:56', NULL, 0);
INSERT INTO `dt_pay` VALUES ('908cefcb-2260-471d-937f-d2f14507bae0', 17, 2, NULL, 3, '爱心涌动 情满校园', 0.10, NULL, 1, '213231', '2019-05-26 10:29:32', '2019-05-26 10:29:32', NULL, 0);
INSERT INTO `dt_pay` VALUES ('92ece93f-65c6-4406-aa30-dc03b47441bd', 19, 5, NULL, 5, '我要重新站起来', 0.10, NULL, 1, '5555', '2019-05-27 01:22:50', '2019-05-27 01:22:50', NULL, 0);
INSERT INTO `dt_pay` VALUES ('98f98f74-6d8d-452e-a6fd-5f2f896a3547', 20, 6, NULL, 2, '校园爱心，帮扶他人', 0.10, NULL, 1, '0530测试', '2019-05-30 20:57:53', '2019-05-30 20:57:53', NULL, 0);
INSERT INTO `dt_pay` VALUES ('9fc9c17c-744e-4b85-91ed-f47b55477cf8', 13, 5, NULL, 2, '救救孩子', 0.10, NULL, 1, '马上', '2019-05-27 14:43:30', '2019-05-27 14:43:30', NULL, 0);
INSERT INTO `dt_pay` VALUES ('bec385e2-e0f4-467c-971e-00f53d198d5d', 26, 6, NULL, 2, '0530测试', 0.10, NULL, 1, '', '2019-05-30 21:11:54', '2019-05-30 21:11:54', NULL, 0);
INSERT INTO `dt_pay` VALUES ('cb545147-c033-403b-a31e-5a30e7d59655', 25, 1, NULL, 6, 'demo测试', 0.10, NULL, 1, '111', '2019-05-31 00:44:26', '2019-05-31 00:44:26', NULL, 0);
INSERT INTO `dt_pay` VALUES ('d09fa5f3-8768-45d6-bc11-2d70f995d3ae', 22, 5, NULL, 4, '我才二十岁，不想这么离开', 0.10, NULL, 0, '23789', '2019-05-27 11:36:45', '2019-05-27 11:36:45', NULL, 0);
INSERT INTO `dt_pay` VALUES ('daca81eb-7ff1-4a59-b5f8-8733d341d976', 19, 1, NULL, 5, '我要重新站起来', 0.10, NULL, 0, '1212', '2019-05-31 00:32:10', '2019-05-31 00:32:10', NULL, 0);
INSERT INTO `dt_pay` VALUES ('e3314cd6-37ba-404c-9a71-80c61551ae23', 20, 5, NULL, 6, '校园爱心，帮扶他人', 0.10, NULL, 1, '21345678', '2019-05-27 11:34:06', '2019-05-27 11:34:06', NULL, 0);
INSERT INTO `dt_pay` VALUES ('f1644696-f15c-45e3-ae74-cbf138e34bd0', 21, 5, NULL, 4, '公益万里行——捐赠暖校园', 0.10, NULL, 1, '公益', '2019-05-27 14:01:35', '2019-05-27 14:01:35', NULL, 0);
INSERT INTO `dt_pay` VALUES ('fb46b673-598b-4271-aaff-6567e11e9344', 25, 2, NULL, 6, 'demo测试', 0.10, NULL, 1, '0531', '2019-05-31 11:22:22', '2019-05-31 11:22:22', NULL, 0);
INSERT INTO `dt_pay` VALUES ('fe7fcc29-c249-4f26-a553-bbff2135f883', 26, 1, NULL, 6, '0530测试', 0.10, NULL, 1, '555', '2019-05-31 00:35:06', '2019-05-31 00:35:06', NULL, 0);

-- ----------------------------
-- Table structure for dt_record
-- ----------------------------
DROP TABLE IF EXISTS `dt_record`;
CREATE TABLE `dt_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_user_id` int(11) DEFAULT NULL COMMENT '捐赠人Id',
  `created_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '捐赠者姓名',
  `receiver_id` int(11) DEFAULT NULL COMMENT '接收人Id',
  `receiver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收人姓名',
  `verify_user_id` int(11) DEFAULT NULL COMMENT '校验人Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `goal` decimal(10, 2) DEFAULT NULL COMMENT '目标',
  `money` decimal(10, 2) DEFAULT NULL COMMENT '善款金额',
  `record_state` int(4) DEFAULT NULL COMMENT '捐赠状态',
  `pay_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '捐赠序列号',
  `gmt_created` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dt_record
-- ----------------------------
INSERT INTO `dt_record` VALUES (17, 2, 'superadmin', 2, 'superadmin', NULL, '我想活着', 12345.00, 0.10, 0, '881b14b2-5a2c-4495-9373-97cc2ec7fdd4', '2019-05-25 22:46:02', '2019-05-25 22:46:03', 0);
INSERT INTO `dt_record` VALUES (18, 2, 'superadmin', 3, 'aaa', NULL, '爱心涌动 情满校园', 26000.00, 0.10, 0, 'bfcbd324-bbe7-478d-bc7e-ad06fcd3ecdb', '2019-05-25 23:27:12', '2019-05-25 23:27:12', 0);
INSERT INTO `dt_record` VALUES (27, 4, 'bbb', 2, 'superadmin', NULL, '我才二十岁，不想这么离开', 30000.00, 0.10, 0, '04dd25ac-0381-430a-b277-0748248cd316', '2019-05-26 23:48:53', '2019-05-26 23:48:53', 0);
INSERT INTO `dt_record` VALUES (28, 5, 'ccc', 2, 'superadmin', NULL, '我要重新站起来', 200000.00, 0.10, 0, '2d58ec0e-db20-42db-b84d-41c830a665e6', '2019-05-27 01:23:13', '2019-05-27 01:23:13', 0);
INSERT INTO `dt_record` VALUES (29, 5, 'ccc', 2, 'superadmin', NULL, '校园爱心，帮扶他人', 30000.00, 0.10, 0, 'a76abafb-7031-4848-a25f-55823d7fa0cb', '2019-05-27 11:35:02', '2019-05-27 11:35:02', 0);
INSERT INTO `dt_record` VALUES (30, 5, 'ccc', 2, 'superadmin', NULL, '爱心捐助 情暖校园', 20000.00, 0.10, 0, 'b7d56676-be8b-4b7a-9d67-b70e9468737b', '2019-05-27 11:46:51', '2019-05-27 11:46:51', 0);
INSERT INTO `dt_record` VALUES (31, 5, 'ccc', 2, 'superadmin', NULL, '我想活着', 12345.00, 0.10, 0, '172ddaa2-0d94-4f4f-bf64-ef283d4c7253', '2019-05-27 13:58:32', '2019-05-27 13:58:32', 0);
INSERT INTO `dt_record` VALUES (32, 5, 'ccc', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '0148897f-0c30-443f-bb3c-d37509e2135e', '2019-05-27 14:02:05', '2019-05-27 14:02:05', 0);
INSERT INTO `dt_record` VALUES (33, 5, 'ccc', 2, 'superadmin', NULL, '校园爱心，帮扶他人', 30000.00, 0.10, 0, 'd91c8389-cac7-4ac7-82e2-fb553757bb3c', '2019-05-27 14:28:47', '2019-05-27 14:28:47', 0);
INSERT INTO `dt_record` VALUES (34, 5, 'ccc', 2, 'superadmin', NULL, '救救孩子', 10000.00, 0.10, 0, '676af6bf-68df-44ed-a2ac-d74505e37a14', '2019-05-27 14:36:05', '2019-05-27 14:36:05', 0);
INSERT INTO `dt_record` VALUES (35, 5, 'ccc', 2, 'superadmin', NULL, '救救孩子', 10000.00, 0.10, 0, '32245126-6cb7-4566-b0a4-ba937a07f06e', '2019-05-27 14:43:57', '2019-05-27 14:43:57', 0);
INSERT INTO `dt_record` VALUES (36, 5, 'ccc', 2, 'superadmin', NULL, '爱心涌动 情满校园', 26000.00, 0.10, 0, '8061e520-1832-46e6-95b1-db9ba7275af9', '2019-05-27 14:49:33', '2019-05-27 14:49:33', 0);
INSERT INTO `dt_record` VALUES (37, 6, 'ddd', 2, 'superadmin', NULL, '爱心捐助 情暖校园', 20000.00, 0.10, 0, 'ce707732-7875-4ce8-8053-a421c21e6cb2', '2019-05-27 21:42:23', '2019-05-27 21:42:23', 0);
INSERT INTO `dt_record` VALUES (38, 2, 'superadmin', 2, 'superadmin', NULL, '我想活着', 12345.00, 0.10, 0, 'f7ca3faa-060b-4602-8a3b-05092a6da181', '2019-05-29 10:43:54', '2019-05-29 10:43:54', 0);
INSERT INTO `dt_record` VALUES (39, 1, 'admin', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, 'ed77931a-2c25-472d-a9df-2e1e6e65f422', '2019-05-29 15:42:57', '2019-05-29 15:42:57', 0);
INSERT INTO `dt_record` VALUES (40, 1, 'admin', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, 'af240af7-d970-4128-a377-e94b45efd7c2', '2019-05-29 15:43:06', '2019-05-29 15:43:06', 0);
INSERT INTO `dt_record` VALUES (41, 1, 'admin', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '3385f783-ac27-484c-b4d8-420df74ecdd4', '2019-05-29 15:43:06', '2019-05-29 15:43:06', 0);
INSERT INTO `dt_record` VALUES (42, 1, 'admin', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '9f4b7b66-ff2b-4dd8-9f7a-058e60c0e4fb', '2019-05-29 15:43:08', '2019-05-29 15:43:08', 0);
INSERT INTO `dt_record` VALUES (43, 1, 'admin', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '6e251e98-7187-4852-97b4-6c39247cb10c', '2019-05-29 15:43:08', '2019-05-29 15:43:08', 0);
INSERT INTO `dt_record` VALUES (44, 6, 'ddd', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '3800a6cf-0486-4d09-b660-fd11ac95a46a', '2019-05-29 16:16:32', '2019-05-29 16:16:32', 0);
INSERT INTO `dt_record` VALUES (45, 6, 'ddd', 2, 'superadmin', NULL, '校园爱心，帮扶他人', 30000.00, 0.10, 0, 'ae6a653f-e525-43d9-9fbe-67c099d2ce70', '2019-05-30 20:58:21', '2019-05-30 20:58:21', 0);
INSERT INTO `dt_record` VALUES (46, 6, 'ddd', 2, 'superadmin', NULL, '我要重新站起来', 200000.00, 0.10, 0, '75a37c9b-3461-4d1f-8b1d-705938177a3e', '2019-05-30 21:08:40', '2019-05-30 21:08:40', 0);
INSERT INTO `dt_record` VALUES (47, 6, 'ddd', 2, 'superadmin', NULL, '0530测试', 20000.00, 0.10, 0, '2f2aecaf-d1ef-4fd7-a2ec-18f6cd260ee4', '2019-05-30 21:12:44', '2019-05-30 21:12:44', 0);
INSERT INTO `dt_record` VALUES (48, 6, 'ddd', 2, 'superadmin', NULL, '0530测试', 20000.00, 0.10, 0, 'af9a34b9-dd55-49eb-91d2-30160cdddb24', '2019-05-30 21:16:39', '2019-05-30 21:16:39', 0);
INSERT INTO `dt_record` VALUES (49, 6, 'ddd', 2, 'superadmin', NULL, '0530测试', 20000.00, 0.10, 0, '0f63177a-4920-453c-b14c-8f6110776e7f', '2019-05-30 21:20:48', '2019-05-30 21:20:48', 0);
INSERT INTO `dt_record` VALUES (50, 6, 'ddd', 2, 'superadmin', NULL, '0530测试', 20000.00, 0.10, 0, 'b100e6d6-a95e-4beb-b8c3-ec8e16945bf7', '2019-05-30 21:23:46', '2019-05-30 21:23:46', 0);
INSERT INTO `dt_record` VALUES (51, 6, 'ddd', 2, 'superadmin', NULL, '公益万里行——捐赠暖校园', 60000.00, 0.10, 0, '6501a511-fe50-4be7-aa3d-c529b309a4ab', '2019-05-30 21:24:45', '2019-05-30 21:24:45', 0);
INSERT INTO `dt_record` VALUES (52, 6, 'ddd', 3, 'aaa', NULL, '爱心捐助 情暖校园', 20000.00, 0.10, 0, '0e3e1161-332d-44c6-a6b9-41db8f4b7d60', '2019-05-30 22:45:00', '2019-05-30 22:45:00', 0);
INSERT INTO `dt_record` VALUES (53, 6, 'ddd', 3, 'aaa', NULL, '爱心涌动 情满校园', 26000.00, 0.10, 0, '95b51887-a202-4bb3-b944-cf4d4d3cc777', '2019-05-30 22:47:07', '2019-05-30 22:47:07', 0);
INSERT INTO `dt_record` VALUES (54, 1, 'admin', 5, 'ccc', NULL, '我要重新站起来', 200000.00, 0.10, 0, '316421ab-1a77-4079-b0d5-f307195ffb79', '2019-05-31 00:32:41', '2019-05-31 00:32:41', 0);
INSERT INTO `dt_record` VALUES (55, 1, 'admin', 6, 'ddd', NULL, '0530测试', 20000.00, 0.10, 0, '04522681-0f4b-4070-a0a2-6d09afe473a1', '2019-05-31 00:35:23', '2019-05-31 00:35:23', 0);
INSERT INTO `dt_record` VALUES (56, 1, 'admin', 6, 'ddd', NULL, 'demo测试', 6000.00, 0.10, 0, 'cb545147-c033-403b-a31e-5a30e7d59655', '2019-05-31 00:44:41', '2019-05-31 00:44:41', 0);
INSERT INTO `dt_record` VALUES (57, 1, 'admin', 4, 'bbb', NULL, '我才二十岁，不想这么离开', 30000.00, 0.10, 0, '51db17de-dce8-48b0-925e-d048d60d9c34', '2019-05-31 00:48:29', '2019-05-31 00:48:29', 0);
INSERT INTO `dt_record` VALUES (58, 2, 'superadmin', 6, 'ddd', NULL, 'demo测试', 6000.00, 0.10, 0, 'fb46b673-598b-4271-aaff-6567e11e9344', '2019-05-31 11:22:39', '2019-05-31 11:22:39', 0);
INSERT INTO `dt_record` VALUES (59, 6, 'ddd', 6, 'ddd', NULL, 'demo测试', 6000.00, 0.10, 0, '057d8b1d-5194-4b0a-b25f-1a35280ba968', '2019-05-31 18:48:08', '2019-05-31 18:48:08', 0);
INSERT INTO `dt_record` VALUES (60, 6, 'ddd', 6, 'ddd', NULL, '0530测试', 20000.00, 0.10, 0, '76b0ae1c-db57-432c-9a4e-cac647de5b4c', '2019-05-31 18:51:20', '2019-05-31 18:51:20', 0);

SET FOREIGN_KEY_CHECKS = 1;
