/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : donate_user

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 31/05/2019 22:02:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `headImgUrl` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像url',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1有效,0无效）',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型（暂未用）',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES (1, 'admin', '$2a$10$3uOoX1ps14CxuotogUoDreW8zXJOZB9XeGdrC/xDV36hhaE8Rn9HO', 'admin', '', '15970604754', '1234576915@qq.com', 1, 1, 'BACKEND', '2018-01-17 16:57:01', '2019-05-01 09:00:59');
INSERT INTO `app_user` VALUES (2, 'superadmin', '$2a$10$.gLkG0j2kM0stWoOvPBvqu0H9uSD0HUlpErI.PTKyZQkSUZIV2wFq', '超级管理员', NULL, '15970604754', '1234576915@qq.com', 1, 1, 'BACKEND', '2018-01-19 20:30:11', '2019-05-01 20:51:53');
INSERT INTO `app_user` VALUES (3, 'aaa', '$2a$10$r3FAdkNlHsVoiOqMU27X5uHaCmuJ8tnfVyMDebQtghwP28YwgoWL2', 'aaa', 'http://www.baidu.com', '15970604754', '1234576915@qq.com', 1, 0, 'APP', '2019-04-24 11:16:16', '2019-04-24 11:16:16');
INSERT INTO `app_user` VALUES (4, 'bbb', '$2a$10$uW2fMvB8uMzuHvJrqg0N8ul1z2XEjovEo0fVJBvzfTVPzhZ87KjUa', 'bbb', 'http://www.baidu.com', '15970604754', '1234576915@qq.com', 1, 1, 'APP', '2019-04-24 11:16:50', '2019-04-30 20:52:49');
INSERT INTO `app_user` VALUES (5, 'ccc', '$2a$10$rKr4eQXQFCTmvZ.FA0.Q2Ozk4OEjq/9weKIe69ORBXHxTnBP8WzJK', 'ccc', 'http://www.baidu.com', '15970604754', '1234576915@qq.com', 1, 1, 'APP', '2019-04-24 11:17:24', '2019-05-01 20:24:46');
INSERT INTO `app_user` VALUES (6, 'ddd', '$2a$10$FJCFAtjQzFUcRLk8wCdjtOnKFLetIUBhS4CoDQ38Gu.V/pq61TJAi', 'ddd', 'http://www.baidu.com', '15970604754', '1234576915@qq.com', 1, 1, 'APP', '2019-04-24 11:19:34', '2019-05-14 20:34:57');
INSERT INTO `app_user` VALUES (7, 'eee', '$2a$10$0oX/5k12TicNGusibMdgt.DpbTx/gWkFVuIRYxr8aV8tWsG8s4qf2', 'eee', 'http://www.baidu.com', '15970604754', '1234576915@qq.com', 1, 1, 'APP', '2019-04-24 11:19:51', '2019-05-01 20:51:19');
INSERT INTO `app_user` VALUES (8, '1111', '$2a$10$wxCvTK5IYNeziVJYyax58ueoXZO47ReLnkYDuhpN5gh9qWF.Ewy9G', '1111', NULL, '15970604754', '1234576915@qq.com', 0, 1, 'APP', '2019-05-14 21:56:03', '2019-05-15 13:39:35');
INSERT INTO `app_user` VALUES (9, '12312', '$2a$10$B/07jqh1iXySJZv3/h32Tu9Vbky/QDAUShivG7Sil1mGr4QhzAhRi', '123123', NULL, '15970604754', '1234576915@qq.com', 0, 1, 'APP', '2019-05-15 15:12:38', '2019-05-15 15:12:38');
INSERT INTO `app_user` VALUES (10, 'xshpe', '$2a$10$wX9kgaPkvHq1lBJ9o56ZDuCJCsl1cFK/b3NYAY9mxT0ClBA.jdF1a', '谢少华', NULL, '15970604754', NULL, 0, 1, 'APP', '2019-05-29 11:08:40', '2019-05-29 11:08:40');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限标识',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  `is_deleted` int(4) NOT NULL COMMENT '是否删除，0删除，1未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission`(`permission`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限标识表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 'back:permission:save', '保存权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (2, 'back:permission:update', '修改权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (3, 'back:permission:delete', '删除权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (4, 'back:permission:query', '查询权限标识', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (5, 'back:role:save', '添加角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (6, 'back:role:update', '修改角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (7, 'back:role:delete', '删除角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (8, 'back:role:permission:set', '给角色分配权限', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (9, 'back:user:query', '用户查询', '2018-01-18 17:12:00', '2018-01-18 17:12:05', 1);
INSERT INTO `sys_permission` VALUES (10, 'back:user:update', '修改用户', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (11, 'back:user:role:set', '给用户分配角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (12, 'back:user:password', '用户重置密码', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (13, 'back:menu:save', '添加菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (14, 'back:menu:update', '修改菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (15, 'back:menu:delete', '删除菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (16, 'back:menu:query', '查询菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (17, 'back:menu:set2role', '给角色分配菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (18, 'back:role:query', '查询角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (19, 'user:role:byuid', '获取用户的角色', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (20, 'role:permission:byroleid', '获取角色的权限', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (21, 'menu:byroleid', '获取角色的菜单', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (22, 'ip:black:query', '查询黑名单ip', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (23, 'ip:black:save', '添加黑名单ip', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (24, 'ip:black:delete', '删除黑名单ip', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (25, 'log:query', '日志查询', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (26, 'file:query', '文件查询', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (27, 'file:del', '文件删除', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (28, 'mail:save', '保存邮件', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (29, 'mail:update', '修改邮件', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (30, 'mail:query', '邮件查询', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (31, 'sms:query', '短信发送记录查询', '2018-01-18 17:06:39', '2018-01-18 17:06:42', 1);
INSERT INTO `sys_permission` VALUES (32, 'client:save', '保存client', '2018-06-28 17:06:39', '2018-06-28 17:06:39', 1);
INSERT INTO `sys_permission` VALUES (33, 'client:update', '修改client', '2018-06-28 17:06:39', '2018-06-28 17:06:39', 1);
INSERT INTO `sys_permission` VALUES (34, 'client:query', '查询client', '2018-06-28 17:06:39', '2018-06-28 17:06:39', 1);
INSERT INTO `sys_permission` VALUES (35, 'client:del', '删除client', '2018-06-28 17:06:39', '2018-06-28 17:06:39', 1);
INSERT INTO `sys_permission` VALUES (36, 'apply:apply', '用户申请', '2019-04-23 09:59:56', '2019-04-23 10:00:00', 1);
INSERT INTO `sys_permission` VALUES (37, 'apply:list', '申请查看', '2019-04-23 10:00:49', '2019-04-23 10:00:53', 1);
INSERT INTO `sys_permission` VALUES (38, 'apply:person:verify', '个人通过', '2019-04-23 10:01:54', '2019-04-23 10:01:57', 1);
INSERT INTO `sys_permission` VALUES (39, 'apply:person:unverify', '个人未通过', '2019-04-23 10:02:20', '2019-04-23 10:02:23', 1);
INSERT INTO `sys_permission` VALUES (40, 'apply:public:verify', '公益通过', '2019-04-23 10:02:53', '2019-04-23 10:02:55', 1);
INSERT INTO `sys_permission` VALUES (41, 'apply:public:unverify', '公益未通过', '2019-04-23 10:03:37', '2019-04-23 10:03:40', 1);
INSERT INTO `sys_permission` VALUES (42, 'apply:donate:count', '捐款统计', '2019-04-23 10:04:30', '2019-04-23 10:04:33', 1);
INSERT INTO `sys_permission` VALUES (43, 'apply:donate:list', '捐款列表', '2019-04-23 10:04:52', '2019-04-23 10:04:54', 1);
INSERT INTO `sys_permission` VALUES (44, 'apply:approve', '申请通过', '2019-04-23 10:11:53', '2019-04-23 10:11:56', 1);
INSERT INTO `sys_permission` VALUES (45, 'apply:deny', '申请拒绝', '2019-04-23 10:12:12', '2019-04-23 10:12:15', 1);
INSERT INTO `sys_permission` VALUES (46, 'bulletin:delete', '公告删除', '2019-04-27 13:38:58', '2019-04-26 13:39:03', 1);
INSERT INTO `sys_permission` VALUES (47, 'bulletin:update', '公告修改', '2019-04-11 13:39:26', '2019-04-26 13:39:29', 1);
INSERT INTO `sys_permission` VALUES (48, 'bulletin:query', '公告查询', '2019-04-17 11:51:40', '2019-05-11 22:53:42', 1);
INSERT INTO `sys_permission` VALUES (49, 'test_add', 'test_addd2', '2019-05-12 23:17:39', '2019-05-29 16:22:22', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色code',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  `is_deleted` int(4) NOT NULL COMMENT '1未删除，0删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'SUPER_ADMIN', '超级管理员', '2018-01-19 20:32:16', '2019-05-02 17:13:12', 1);
INSERT INTO `sys_role` VALUES (2, 'admin_test', 'admin_test2', '2019-05-12 23:10:56', '2019-05-31 00:51:42', 1);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `permissionId` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 29);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 33);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 37);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 41);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (1, 44);
INSERT INTO `sys_role_permission` VALUES (1, 45);
INSERT INTO `sys_role_permission` VALUES (1, 46);
INSERT INTO `sys_role_permission` VALUES (1, 47);
INSERT INTO `sys_role_permission` VALUES (1, 48);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 9);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 11);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 16);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 18);
INSERT INTO `sys_role_permission` VALUES (2, 19);
INSERT INTO `sys_role_permission` VALUES (2, 20);
INSERT INTO `sys_role_permission` VALUES (2, 21);
INSERT INTO `sys_role_permission` VALUES (2, 22);
INSERT INTO `sys_role_permission` VALUES (2, 23);
INSERT INTO `sys_role_permission` VALUES (2, 24);
INSERT INTO `sys_role_permission` VALUES (2, 25);
INSERT INTO `sys_role_permission` VALUES (2, 26);
INSERT INTO `sys_role_permission` VALUES (2, 27);
INSERT INTO `sys_role_permission` VALUES (2, 28);
INSERT INTO `sys_role_permission` VALUES (2, 29);
INSERT INTO `sys_role_permission` VALUES (2, 30);
INSERT INTO `sys_role_permission` VALUES (2, 31);
INSERT INTO `sys_role_permission` VALUES (2, 32);
INSERT INTO `sys_role_permission` VALUES (2, 33);
INSERT INTO `sys_role_permission` VALUES (2, 34);
INSERT INTO `sys_role_permission` VALUES (2, 35);
INSERT INTO `sys_role_permission` VALUES (2, 36);
INSERT INTO `sys_role_permission` VALUES (2, 37);
INSERT INTO `sys_role_permission` VALUES (2, 38);
INSERT INTO `sys_role_permission` VALUES (2, 39);
INSERT INTO `sys_role_permission` VALUES (2, 40);
INSERT INTO `sys_role_permission` VALUES (2, 41);
INSERT INTO `sys_role_permission` VALUES (2, 42);
INSERT INTO `sys_role_permission` VALUES (2, 43);
INSERT INTO `sys_role_permission` VALUES (2, 44);
INSERT INTO `sys_role_permission` VALUES (2, 45);
INSERT INTO `sys_role_permission` VALUES (2, 46);
INSERT INTO `sys_role_permission` VALUES (2, 47);
INSERT INTO `sys_role_permission` VALUES (2, 48);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1);
INSERT INTO `sys_role_user` VALUES (2, 1);

-- ----------------------------
-- Table structure for t_wechat
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat`;
CREATE TABLE `t_wechat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信openid',
  `unionid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信unionid',
  `userId` int(11) DEFAULT NULL COMMENT '绑定用户的id',
  `app` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公众号标识',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信昵称',
  `sex` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信返回的性别',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信返回的省',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信返回的城市',
  `country` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信返回的国家',
  `headimgurl` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信头像',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `openid`(`openid`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `unionid`(`unionid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_credentials
-- ----------------------------
DROP TABLE IF EXISTS `user_credentials`;
CREATE TABLE `user_credentials`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名或手机号等',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号类型（用户名、手机号）',
  `userId` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户凭证表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_credentials
-- ----------------------------
INSERT INTO `user_credentials` VALUES ('1111', 'USERNAME', 8);
INSERT INTO `user_credentials` VALUES ('12312', 'USERNAME', 9);
INSERT INTO `user_credentials` VALUES ('aaa', 'USERNAME', 3);
INSERT INTO `user_credentials` VALUES ('admin', 'USERNAME', 1);
INSERT INTO `user_credentials` VALUES ('bbb', 'USERNAME', 4);
INSERT INTO `user_credentials` VALUES ('ccc', 'USERNAME', 5);
INSERT INTO `user_credentials` VALUES ('ddd', 'USERNAME', 6);
INSERT INTO `user_credentials` VALUES ('eee', 'USERNAME', 7);
INSERT INTO `user_credentials` VALUES ('superadmin', 'USERNAME', 2);
INSERT INTO `user_credentials` VALUES ('xshpe', 'USERNAME', 10);

-- ----------------------------
-- Table structure for user_qualificate
-- ----------------------------
DROP TABLE IF EXISTS `user_qualificate`;
CREATE TABLE `user_qualificate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `colleague` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `studentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deleted` int(4) DEFAULT NULL COMMENT '1：删除；0：存在',
  `passed` int(4) DEFAULT NULL COMMENT '1：通过；0：拒绝',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_qualificate
-- ----------------------------
INSERT INTO `user_qualificate` VALUES (1, 2, '南昌航空大学', '软件学院', '15204129', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1557818288366.jpg', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1557818288366.jpg', 0, 1);
INSERT INTO `user_qualificate` VALUES (2, 1, '南昌航空大学', '信工学院', '15201212', NULL, 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558175914328.jpg', 0, 1);
INSERT INTO `user_qualificate` VALUES (3, 3, '南昌航空大学', '艺术学院', '15010101', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558176930241.jpg', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558176927115.jpg', 0, 1);
INSERT INTO `user_qualificate` VALUES (6, 4, '南昌大学', '软件学院', '15204111', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558178664494.jpg', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558178651161.jpg', 0, 1);
INSERT INTO `user_qualificate` VALUES (7, 5, '南昌大学', '软件学院', '1520411214', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558867725855.jpg', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558867713821.jpg', 0, 1);
INSERT INTO `user_qualificate` VALUES (8, 6, '南昌大学', '软件学院', '1520411214', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558867725855.jpg', 'http://donate-store.oss-cn-beijing.aliyuncs.com/user_img/1558867713821.jpg', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
