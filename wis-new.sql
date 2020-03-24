/*
 Navicat Premium Data Transfer

 Source Server         : 本地MYSQL
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 127.0.0.1:3306
 Source Schema         : wis-new

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 24/03/2020 14:39:35
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`wis-new` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `wis-new`;

-- ----------------------------
-- Table structure for admin_department
-- ----------------------------
DROP TABLE IF EXISTS `admin_department`;
CREATE TABLE `admin_department`  (
  `DeptCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `EName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LEVEL` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `UserCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DeptDesc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parId` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order` int(10) NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `resource` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DeptCode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_department
-- ----------------------------
INSERT INTO `admin_department` VALUES ('CSZH1', '测试组织1', '', '', 'Y', '', '', 'BBM', 0, '', '1,2,3,4,25,19,20,26,32,30,31,33,34,35,36,37,38,39,40,41,42');
INSERT INTO `admin_department` VALUES ('CSZH2', '测试组织2', '', '', 'Y', '', '', 'BBM', 0, '', '19,20,26,32,30,31,33,34');
INSERT INTO `admin_department` VALUES ('CSZH3', '测试组织3', '', '', 'Y', '', '', 'BBM', 0, '', '19,20,26,32');
INSERT INTO `admin_department` VALUES ('CSZH4', '测试组织4', ' ', ' ', 'Y', ' ', ' ', 'BBM', 0, ' ', '19,20,26,32');
INSERT INTO `admin_department` VALUES ('CSZH5', '测试组织5', ' ', ' ', 'Y', ' ', ' ', 'BBM', 0, ' ', '19,20,26,32');
INSERT INTO `admin_department` VALUES ('CSZH6', '测试组织6', ' ', ' ', 'Y', ' ', ' ', 'BBM', 0, ' ', '2,3,19,20,26,32');

-- ----------------------------
-- Table structure for admin_department_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_department_resource`;
CREATE TABLE `admin_department_resource`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_id` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department_id` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_at` datetime(0) NOT NULL,
  `state` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_department_resource
-- ----------------------------

-- ----------------------------
-- Table structure for admin_operate
-- ----------------------------
DROP TABLE IF EXISTS `admin_operate`;
CREATE TABLE `admin_operate`  (
  `operate_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `operate_CreateTime` datetime(0) NOT NULL COMMENT '创建时间',
  `operate_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `operate_State` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `operate_Model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块编号',
  `operate_operate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块操作',
  PRIMARY KEY (`operate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_operate
-- ----------------------------
INSERT INTO `admin_operate` VALUES ('12edbf07-dccf-42dd-9d29-9524e3225a3e', '2019-09-26 13:58:48', 'U13800000000', '1', 'M1.1', '一审通过');
INSERT INTO `admin_operate` VALUES ('12edbf07-dccf-42dd-9d29-9524e3225a4e', '2019-09-26 13:59:48', 'U13800000000', '1', 'M1.1', '二审通过');
INSERT INTO `admin_operate` VALUES ('30b0e8a2-1c27-4b71-9ea0-c3223a52e185', '2019-09-26 15:02:57', 'U13800000000', '1', 'M1.1', '四审驳回');
INSERT INTO `admin_operate` VALUES ('31b026fa-6b07-4a7e-8a26-9bf8b049ac05', '2019-09-26 15:03:41', 'U13800000000', '1', 'M2.1', '问题');
INSERT INTO `admin_operate` VALUES ('61a697b0-e4d2-4a0f-9c1e-2d066feb3b9f', '2019-09-26 15:03:50', 'U13800000000', '1', 'M2.1', '提交');
INSERT INTO `admin_operate` VALUES ('622a5a15-bc4f-4089-bcb7-d7030810e17f', '2019-09-26 15:03:58', 'U13800000000', '1', 'M2.1', '答复');
INSERT INTO `admin_operate` VALUES ('6a8eb41d-ebe8-4335-b0bd-83d62533aeaa', '2019-09-26 15:02:36', 'U13800000000', '1', 'M1.1', '一审驳回');
INSERT INTO `admin_operate` VALUES ('81247ace-d96f-46ef-8d42-657d65206bdd', '2019-09-26 15:02:50', 'U13800000000', '1', 'M1.1', '三审驳回');
INSERT INTO `admin_operate` VALUES ('82f8501c-5847-486d-8cab-139a43f6cfc9', '2019-09-26 15:02:05', 'U13800000000', '1', 'M1.1', '三审通过');
INSERT INTO `admin_operate` VALUES ('c664382a-6de4-45e6-8828-9a904004b653', '2019-09-26 15:02:42', 'U13800000000', '1', 'M1.1', '二审驳回');
INSERT INTO `admin_operate` VALUES ('f66a1947-ba0b-429c-80b6-85b373972b7b', '2019-09-26 15:02:16', 'U13800000000', '1', 'M1.1', '四审通过');

-- ----------------------------
-- Table structure for admin_privilege
-- ----------------------------
DROP TABLE IF EXISTS `admin_privilege`;
CREATE TABLE `admin_privilege`  (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `privilege_resource_id` int(11) NOT NULL DEFAULT 0 COMMENT '资源id',
  `privilege_role_id` int(11) NOT NULL DEFAULT 0 COMMENT '角色id',
  `privilege_create_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `privilege_update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`privilege_id`) USING BTREE,
  INDEX `idx_privilege_resource_id`(`privilege_resource_id`) USING BTREE,
  INDEX `idx_privilege_role_id`(`privilege_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_privilege
-- ----------------------------
INSERT INTO `admin_privilege` VALUES (21, 7, 3, '2019-08-23 17:06:48', '2019-08-23 17:06:48');
INSERT INTO `admin_privilege` VALUES (22, 9, 3, '2019-08-23 17:06:48', '2019-08-23 17:06:48');
INSERT INTO `admin_privilege` VALUES (23, 23, 3, '2019-08-23 17:06:48', '2019-08-23 17:06:48');
INSERT INTO `admin_privilege` VALUES (24, 19, 3, '2019-08-23 17:06:48', '2019-08-23 17:06:48');
INSERT INTO `admin_privilege` VALUES (25, 20, 3, '2019-08-23 17:06:48', '2019-08-23 17:06:48');
INSERT INTO `admin_privilege` VALUES (32, 7, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (33, 9, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (34, 23, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (35, 12, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (36, 15, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (37, 24, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (38, 16, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (39, 17, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (40, 18, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (41, 19, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (42, 20, 4, '2019-08-23 17:07:00', '2019-08-23 17:07:00');
INSERT INTO `admin_privilege` VALUES (43, 7, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (44, 9, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (45, 23, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (46, 12, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (47, 15, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (48, 24, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (49, 16, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (50, 17, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (51, 18, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (52, 19, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (53, 20, 5, '2019-08-23 17:07:19', '2019-08-23 17:07:19');
INSERT INTO `admin_privilege` VALUES (54, 7, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (55, 9, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (56, 23, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (57, 12, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (58, 15, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (59, 24, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (60, 16, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (61, 17, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (62, 18, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (65, 21, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (66, 22, 6, '2019-08-23 17:07:36', '2019-08-23 17:07:36');
INSERT INTO `admin_privilege` VALUES (67, 7, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (68, 10, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (69, 23, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (70, 14, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (71, 24, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (72, 19, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (73, 20, 2, '2019-09-11 13:01:30', '2019-09-11 13:01:30');
INSERT INTO `admin_privilege` VALUES (74, 19, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (75, 20, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (76, 26, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (77, 32, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (78, 30, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (79, 31, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (80, 33, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');
INSERT INTO `admin_privilege` VALUES (81, 34, 6, '2019-09-29 11:14:20', '2019-09-29 11:14:20');

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource`  (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `resource_parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '资源父id',
  `resource_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '资源类型 0-菜单 1-功能',
  `resource_target` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '语义化索引',
  `resource_data` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '资源data，json格式',
  `resource_create_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `resource_update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `yon` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Y',
  `resource_name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_model` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`resource_id`) USING BTREE,
  INDEX `idx_resource_parent_id`(`resource_parent_id`) USING BTREE,
  INDEX `idx_resource_target`(`resource_target`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源(菜单/接口)表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_resource
-- ----------------------------
INSERT INTO `admin_resource` VALUES (1, 0, 0, '', '{\"path\":\"/system\",\"icon\":\"\",\"title\":\"系统管理\",\"type\":\"layout\",\"seq\":10,\"yon\":\"Y\",\"resource_model\":\"M1\"}', '2019-07-25 11:20:57', '2019-09-25 10:47:28', 'Y', '系统管理', 'M1');
INSERT INTO `admin_resource` VALUES (2, 1, 0, '', '{\"path\":\"system/menu\",\"icon\":\"\",\"resource_model\":\"M1.1\",\"yon\":\"Y\",\"title\":\"菜单管理\",\"type\":\"default\",\"seq\":9}', '2019-07-25 11:20:57', '2019-09-25 11:10:00', 'Y', '菜单管理', 'M1.1');
INSERT INTO `admin_resource` VALUES (3, 1, 0, '', '{\"path\":\"system/user\",\"icon\":\"\",\"title\":\"用户管理\",\"type\":\"default\",\"seq\":8,\"yon\":\"Y\",\"resource_model\":\"M1.2\"}', '2019-07-25 11:20:57', '2019-09-25 11:08:52', 'Y', '用户管理', 'M1.2');
INSERT INTO `admin_resource` VALUES (4, 1, 0, '', '{\"title\":\"角色管理\",\"path\":\"system/role\",\"icon\":\"\",\"seq\":7,\"type\":\"default\",\"yon\":\"Y\",\"resource_model\":\"M1.3\"}', '2019-07-25 11:20:57', '2019-09-25 10:47:06', 'Y', '角色管理', 'M1.3');
INSERT INTO `admin_resource` VALUES (19, 0, 0, '', '{\"path\":\"/message\",\"icon\":null,\"title\":\"消息通知\",\"type\":\"layout\",\"seq\":30,\"yon\":\"Y\",\"resource_model\":\"M2\"}', '2019-08-06 13:36:23', '2019-09-25 10:46:58', 'Y', '消息通知', 'M2');
INSERT INTO `admin_resource` VALUES (20, 19, 0, '', '{\"path\":\"message/message\",\"icon\":null,\"title\":\"消息通知\",\"type\":\"default\",\"seq\":30,\"yon\":\"Y\",\"resource_model\":\"M2.1\"}', '2019-08-06 13:37:32', '2019-09-25 10:46:53', 'Y', '消息通知', 'M2.1');
INSERT INTO `admin_resource` VALUES (25, 1, 0, '', '{\"path\":\"system/department\",\"icon\":null,\"title\":\"部门管理\",\"type\":\"default\",\"seq\":6,\"yon\":\"Y\",\"resource_model\":\"M1.4\"}', '2019-08-22 10:35:03', '2019-09-25 10:46:46', 'Y', '部门管理', 'M1.4');
INSERT INTO `admin_resource` VALUES (26, 19, 0, '', '{\"path\":\"message/messageConf\",\"icon\":null,\"title\":\"通知配置\",\"type\":\"default\",\"seq\":20,\"yon\":\"Y\",\"resource_model\":\"M2.2\"}', '2019-09-23 14:03:21', '2019-09-25 10:46:33', 'Y', '通知配置', 'M2.2');
INSERT INTO `admin_resource` VALUES (30, 0, 0, '', '{\"path\":\"/audit\",\"icon\":null,\"resource_model\":\"M3\",\"yon\":\"Y\",\"title\":\"审核设置\",\"type\":\"layout\",\"seq\":40}', '2019-09-25 10:56:48', '2019-09-29 09:55:39', 'Y', '审核设置', 'M3');
INSERT INTO `admin_resource` VALUES (31, 30, 0, '', '{\"path\":\"audit/auditConf\",\"icon\":null,\"title\":\"审核配置\",\"type\":\"default\",\"seq\":10,\"yon\":\"Y\",\"resource_model\":\"M3.1\"}', '2019-09-25 10:58:41', '2019-09-25 10:58:40', 'Y', '审核配置', 'M3.1');
INSERT INTO `admin_resource` VALUES (32, 19, 0, '', '{\"path\":\"message/operationConf\",\"icon\":null,\"resource_model\":\"M2.3\",\"yon\":\"Y\",\"title\":\"操作配置\",\"type\":\"default\",\"seq\":10}', '2019-09-26 10:42:37', '2019-09-26 14:17:12', 'Y', '操作配置', 'M2.3');
INSERT INTO `admin_resource` VALUES (33, 0, 0, '', '{\"path\":\"/file\",\"icon\":null,\"resource_model\":\"M4\",\"yon\":\"Y\",\"title\":\"附件管理\",\"type\":\"layout\",\"seq\":50}', '2019-09-29 09:55:16', '2019-09-29 09:55:44', 'Y', '附件管理', 'M4');
INSERT INTO `admin_resource` VALUES (34, 33, 0, '', '{\"path\":\"file/template\",\"icon\":null,\"resource_model\":\"M4.1\",\"yon\":\"Y\",\"title\":\"资源申请模板管理\",\"type\":\"default\",\"seq\":30}', '2019-09-29 10:00:18', '2019-09-29 10:18:11', 'Y', '资源申请模板管理', 'M4.1');
INSERT INTO `admin_resource` VALUES (35, 33, 0, '', '{\"path\":\"chart/chartView\",\"icon\":null,\"resource_model\":\"M4.2\",\"yon\":\"Y\",\"title\":\"chart\",\"type\":\"default\",\"seq\":20}', '2020-01-02 13:36:26', '2020-01-02 13:36:37', 'Y', 'chart', 'M4.2');
INSERT INTO `admin_resource` VALUES (36, 0, 0, '', '{\"path\":\"/newSystem\",\"icon\":null,\"resource_model\":\"M5\",\"yon\":\"Y\",\"title\":\"新系统管理\",\"type\":\"layout\",\"seq\":80,\"yon\":\"Y\",\"resource_model\":\"M5\"}', '2020-01-15 10:18:08', '2020-01-15 10:18:08', 'Y', '新系统管理', 'M5');
INSERT INTO `admin_resource` VALUES (37, 36, 0, '', '{\"path\":\"newSystem/orderInfo\",\"icon\":null,\"resource_model\":\"M5.1\",\"yon\":\"Y\",\"title\":\"订单管理\",\"type\":\"default\",\"seq\":10}', '2020-01-15 10:19:59', '2020-01-16 14:11:18', 'Y', '订单管理', 'M5.1');
INSERT INTO `admin_resource` VALUES (38, 36, 0, '', '{\"path\":\"newSystem/resMgr\",\"icon\":null,\"resource_model\":\"M5.2\",\"yon\":\"Y\",\"title\":\"资源管理\",\"type\":\"default\",\"seq\":20}', '2020-01-16 14:22:46', '2020-01-16 16:13:48', 'Y', '资源管理', 'M5.2');
INSERT INTO `admin_resource` VALUES (39, 0, 0, '', '{\"path\":\"/note\",\"icon\":null,\"resource_model\":\"M6\",\"yon\":\"Y\",\"title\":\"考勤管理\",\"type\":\"layout\",\"seq\":20}', '2020-03-09 17:09:00', '2020-03-10 14:25:03', 'Y', '考勤管理', 'M6');
INSERT INTO `admin_resource` VALUES (40, 39, 0, '', '{\"path\":\"note/wcheck\",\"icon\":null,\"resource_model\":\"M6.1\",\"yon\":\"Y\",\"title\":\"考勤记录\",\"type\":\"default\",\"seq\":10}', '2020-03-09 17:11:09', '2020-03-10 14:25:18', 'Y', '考勤记录', 'M6.1');
INSERT INTO `admin_resource` VALUES (41, 0, 0, '', '{\"path\":\"/user\",\"icon\":null,\"resource_model\":\"M99\",\"yon\":\"Y\",\"title\":\"客户管理\",\"type\":\"layout\",\"seq\":1}', '2020-03-23 17:00:16', '2020-03-23 17:05:59', 'Y', '客户管理', 'M99');
INSERT INTO `admin_resource` VALUES (42, 41, 0, '', '{\"path\":\"custom/custom\",\"icon\":null,\"resource_model\":\"M99.1\",\"yon\":\"Y\",\"title\":\"客户管理\",\"type\":\"default\",\"seq\":0}', '2020-03-23 17:02:06', '2020-03-24 10:36:14', 'Y', '客户管理', 'M99.1');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父角色id',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色描述',
  `role_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0-正常 1-停用',
  `role_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型 0-成员角色member 1-群组角色group',
  `role_create_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `role_update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uniq_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 0, '超级管理员', '拥有所有权限', 0, 1, '2019-07-25 09:59:35', '2019-07-25 09:59:35');
INSERT INTO `admin_role` VALUES (2, 1, '租户', '只能编辑自己创建的提交前的信息', 0, 1, '2019-08-16 11:05:45', '2019-08-21 17:08:31');
INSERT INTO `admin_role` VALUES (3, 1, '运维人员', '一审人员', 0, 1, '2019-08-16 11:07:13', '2019-08-21 17:08:31');
INSERT INTO `admin_role` VALUES (4, 1, '一级审批员', '二审人员', 0, 1, '2019-08-16 11:07:32', '2019-08-21 17:08:32');
INSERT INTO `admin_role` VALUES (5, 1, '二级审批员', '三审人员', 0, 1, '2019-08-16 11:07:51', '2019-08-21 17:08:32');
INSERT INTO `admin_role` VALUES (6, 1, '系统管理员', '四审人员及管理员', 0, 1, '2019-08-16 11:08:20', '2019-08-21 17:08:33');

-- ----------------------------
-- Table structure for admin_role_department
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_department`;
CREATE TABLE `admin_role_department`  (
  `role_department_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_department_roleid` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `role_department_departmentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织id',
  `role_department_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_department
-- ----------------------------
INSERT INTO `admin_role_department` VALUES (1, '2', '测试组织1', '2019-09-18 15:32:58');
INSERT INTO `admin_role_department` VALUES (2, '3', '测试组织1', '2019-09-18 15:33:49');
INSERT INTO `admin_role_department` VALUES (3, '4', '测试组织1', '2019-09-18 15:33:53');
INSERT INTO `admin_role_department` VALUES (4, '5', '测试组织1', '2019-09-18 15:33:59');
INSERT INTO `admin_role_department` VALUES (5, '6', '测试组织1', '2019-09-18 15:34:05');
INSERT INTO `admin_role_department` VALUES (6, '6', '测试组织2', '2019-09-19 10:56:31');
INSERT INTO `admin_role_department` VALUES (7, '5', '测试组织3', '2019-09-19 10:56:30');
INSERT INTO `admin_role_department` VALUES (8, '4', '测试组织4', '2019-09-19 10:56:28');
INSERT INTO `admin_role_department` VALUES (9, '3', '测试组织5', '2019-09-19 10:57:14');
INSERT INTO `admin_role_department` VALUES (10, '2', '测试组织6', '2019-09-19 13:37:47');

-- ----------------------------
-- Table structure for admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_user`;
CREATE TABLE `admin_role_user`  (
  `role_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_user_role_id` int(11) NOT NULL COMMENT '角色id',
  `role_user_user_id` int(11) NOT NULL COMMENT '用户id',
  `role_user_create_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `role_user_update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`role_user_id`) USING BTREE,
  INDEX `idx_role_user_role_id`(`role_user_role_id`) USING BTREE,
  INDEX `idx_role_user_user_id`(`role_user_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_user
-- ----------------------------
INSERT INTO `admin_role_user` VALUES (7, 2, 2, '2019-08-16 11:20:27', '2019-08-16 11:20:27');
INSERT INTO `admin_role_user` VALUES (8, 3, 3, '2019-08-16 11:20:44', '2019-08-16 11:20:44');
INSERT INTO `admin_role_user` VALUES (9, 4, 4, '2019-08-16 11:21:14', '2019-08-16 11:23:47');
INSERT INTO `admin_role_user` VALUES (10, 5, 5, '2019-08-16 11:21:43', '2019-08-16 11:23:48');
INSERT INTO `admin_role_user` VALUES (11, 6, 6, '2019-08-16 11:22:16', '2019-08-20 17:29:07');
INSERT INTO `admin_role_user` VALUES (17, 2, 1, '2019-08-21 16:36:07', '2019-08-21 16:36:07');
INSERT INTO `admin_role_user` VALUES (21, 6, 1, '2019-09-19 09:55:33', '2019-09-19 09:55:33');
INSERT INTO `admin_role_user` VALUES (22, 5, 1, '2019-09-25 16:03:54', '2019-09-25 16:03:54');
INSERT INTO `admin_role_user` VALUES (23, 3, 1, '2019-09-25 16:03:54', '2019-09-25 16:03:54');
INSERT INTO `admin_role_user` VALUES (25, 4, 1, '2019-09-25 16:21:36', '2019-09-25 16:21:36');
INSERT INTO `admin_role_user` VALUES (26, 1, 1, '2019-09-25 16:23:41', '2019-09-25 16:23:43');
INSERT INTO `admin_role_user` VALUES (28, 3, 7, '2019-09-27 13:25:47', '2019-09-27 13:25:47');
INSERT INTO `admin_role_user` VALUES (29, 6, 8, '2019-09-27 13:27:54', '2019-09-27 13:27:54');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_phone` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `user_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `user_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `user_token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录的token',
  `user_avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `user_login_count` int(11) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `user_last_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '最后登录ip',
  `user_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0-正常 1-停用',
  `user_create_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `user_update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `user_department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属组织',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uniq_user_phone`(`user_phone`) USING BTREE,
  UNIQUE INDEX `uniq_user_email`(`user_email`) USING BTREE,
  INDEX `idx_user_token`(`user_token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'U13800000000', '超级管理员', 'admin@admin.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', '760247ca-5341-48b9-b28f-fb00a470842f', '', 0, '192.168.111.100', 0, '2019-07-25 09:59:35', '2019-08-21 17:30:24', '测试组织1');
INSERT INTO `admin_user` VALUES (2, 'U12341111333', '租户', '123123411@qq.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', 'bebe8113-61ce-413c-8c9f-9f2a7cce5b5d', '', 1, '192.168.111.100', 0, '2019-07-26 13:27:10', '2019-08-21 17:30:29', '测试组织6');
INSERT INTO `admin_user` VALUES (3, 'U13133221133', '运维人员1', '1231412@qq.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', 'c48daaa1-1ea8-4617-988a-69d58a47f397', '', 1, '192.168.111.100', 0, '2019-07-29 13:58:03', '2019-09-27 11:15:28', '测试组织5');
INSERT INTO `admin_user` VALUES (4, 'U13322113311', '一级审批员', '1231241@qq.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', '70c41730-5262-4042-969c-8174a1afea4f', '', 1, '192.168.111.100', 0, '2019-08-16 11:21:14', '2019-08-21 17:30:44', '测试组织4');
INSERT INTO `admin_user` VALUES (5, 'U14622335533', '二级审批员', '12456343@qq.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', 'c486ab10-40f5-44c4-a8d8-a1b6f5939313', '', 1, '192.168.111.4', 0, '2019-08-16 11:21:43', '2019-08-21 17:30:49', '测试组织3');
INSERT INTO `admin_user` VALUES (6, 'U15632241251', '系统管理员', '1752352@qq.com', '$2a$10$L.MpN/awa/rl5DTJJVBDAeAJ7h1sBa3ikwkTIPX/TkayqR7iPBF1i', 'ce3955c2-0b62-44ed-8934-e9f922595b87', '', 1, '192.168.111.100', 0, '2019-08-16 11:22:16', '2019-09-27 10:47:44', '测试组织2');

-- ----------------------------
-- Table structure for configure
-- ----------------------------
DROP TABLE IF EXISTS `configure`;
CREATE TABLE `configure`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置项名',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置项值',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置项中文描述',
  `create_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统及参数配置项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of configure
-- ----------------------------

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom`  (
  `C_ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `C_Name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `C_State` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`C_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of custom
-- ----------------------------
INSERT INTO `custom` VALUES ('C1', '一号客户', 'Y');
INSERT INTO `custom` VALUES ('C2', '二号客户', 'Y');
INSERT INTO `custom` VALUES ('C3', '三号客户', 'N');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (16, '修改后的');
INSERT INTO `department` VALUES (17, '外语系');
INSERT INTO `department` VALUES (18, '经管系');
INSERT INTO `department` VALUES (19, '新闻系');
INSERT INTO `department` VALUES (20, '科学系');
INSERT INTO `department` VALUES (21, '生化系');
INSERT INTO `department` VALUES (27, '生物工程');
INSERT INTO `department` VALUES (28, '生物工程');
INSERT INTO `department` VALUES (29, '生物工程');
INSERT INTO `department` VALUES (30, '生物工程2');
INSERT INTO `department` VALUES (31, '生物工程3');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (1, '杭州', '13711111111', '杭州XXX大街11号', '2015-05-05 15:23:07');
INSERT INTO `order_info` VALUES (2, '杭州', '13722222222', '杭州XXX大街12号', '2015-05-05 15:23:09');
INSERT INTO `order_info` VALUES (3, '北京', '13712345678', '杭州XXX大街33号', '2015-05-05 15:23:20');
INSERT INTO `order_info` VALUES (11, '上海1', '13756458641', '上海市长宁区XXX街XXX号', '2015-05-28 00:00:00');

-- ----------------------------
-- Table structure for r_data_obj
-- ----------------------------
DROP TABLE IF EXISTS `r_data_obj`;
CREATE TABLE `r_data_obj`  (
  `do_id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_id` int(11) NOT NULL,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`do_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_data_obj
-- ----------------------------

-- ----------------------------
-- Table structure for r_data_permission
-- ----------------------------
DROP TABLE IF EXISTS `r_data_permission`;
CREATE TABLE `r_data_permission`  (
  `dp_id` int(11) NOT NULL AUTO_INCREMENT,
  `sr_id` int(11) NOT NULL,
  `expression_type` tinyint(4) NOT NULL,
  `column` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `equal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_data_permission
-- ----------------------------

-- ----------------------------
-- Table structure for r_data_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `r_data_permission_role`;
CREATE TABLE `r_data_permission_role`  (
  `dp_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`dp_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_data_permission_role
-- ----------------------------

-- ----------------------------
-- Table structure for r_data_type
-- ----------------------------
DROP TABLE IF EXISTS `r_data_type`;
CREATE TABLE `r_data_type`  (
  `dt_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`dt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_data_type
-- ----------------------------

-- ----------------------------
-- Table structure for r_datatype_res
-- ----------------------------
DROP TABLE IF EXISTS `r_datatype_res`;
CREATE TABLE `r_datatype_res`  (
  `dt_id` int(11) NOT NULL,
  `sr_id` int(11) NOT NULL,
  PRIMARY KEY (`dt_id`, `sr_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_datatype_res
-- ----------------------------

-- ----------------------------
-- Table structure for r_group
-- ----------------------------
DROP TABLE IF EXISTS `r_group`;
CREATE TABLE `r_group`  (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组ID',
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户组名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_group
-- ----------------------------
INSERT INTO `r_group` VALUES (4, '公司', 0);
INSERT INTO `r_group` VALUES (5, '销售部', 4);

-- ----------------------------
-- Table structure for r_group_role
-- ----------------------------
DROP TABLE IF EXISTS `r_group_role`;
CREATE TABLE `r_group_role`  (
  `group_id` int(11) NOT NULL COMMENT '用户组ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`group_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户组角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_group_role
-- ----------------------------
INSERT INTO `r_group_role` VALUES (5, 3);

-- ----------------------------
-- Table structure for r_group_user
-- ----------------------------
DROP TABLE IF EXISTS `r_group_user`;
CREATE TABLE `r_group_user`  (
  `group_id` int(11) NOT NULL COMMENT '用户组ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`group_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户组成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_group_user
-- ----------------------------
INSERT INTO `r_group_user` VALUES (5, 2);

-- ----------------------------
-- Table structure for r_role
-- ----------------------------
DROP TABLE IF EXISTS `r_role`;
CREATE TABLE `r_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_type` tinyint(4) NOT NULL COMMENT '角色类型,1个人,2用户组',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_role
-- ----------------------------
INSERT INTO `r_role` VALUES (1, '超级管理员', 1);
INSERT INTO `r_role` VALUES (3, '销售经理', 2);

-- ----------------------------
-- Table structure for r_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `r_role_permission`;
CREATE TABLE `r_role_permission`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `sf_id` int(11) NOT NULL COMMENT '系统功能ID',
  PRIMARY KEY (`role_id`, `sf_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_role_permission
-- ----------------------------
INSERT INTO `r_role_permission` VALUES (1, 1);
INSERT INTO `r_role_permission` VALUES (1, 2);
INSERT INTO `r_role_permission` VALUES (1, 3);
INSERT INTO `r_role_permission` VALUES (1, 4);
INSERT INTO `r_role_permission` VALUES (1, 5);
INSERT INTO `r_role_permission` VALUES (1, 6);
INSERT INTO `r_role_permission` VALUES (1, 7);
INSERT INTO `r_role_permission` VALUES (1, 8);
INSERT INTO `r_role_permission` VALUES (1, 9);
INSERT INTO `r_role_permission` VALUES (1, 10);
INSERT INTO `r_role_permission` VALUES (1, 11);
INSERT INTO `r_role_permission` VALUES (1, 12);
INSERT INTO `r_role_permission` VALUES (1, 13);
INSERT INTO `r_role_permission` VALUES (1, 14);
INSERT INTO `r_role_permission` VALUES (1, 15);
INSERT INTO `r_role_permission` VALUES (1, 16);
INSERT INTO `r_role_permission` VALUES (1, 17);
INSERT INTO `r_role_permission` VALUES (1, 23);
INSERT INTO `r_role_permission` VALUES (1, 24);
INSERT INTO `r_role_permission` VALUES (3, 18);
INSERT INTO `r_role_permission` VALUES (3, 19);
INSERT INTO `r_role_permission` VALUES (3, 20);

-- ----------------------------
-- Table structure for r_sys_function
-- ----------------------------
DROP TABLE IF EXISTS `r_sys_function`;
CREATE TABLE `r_sys_function`  (
  `sf_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sr_id` int(11) NOT NULL COMMENT 'r_sys_res主键',
  `operate_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限代码',
  `operate_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  PRIMARY KEY (`sf_id`, `operate_code`, `sr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统功能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_sys_function
-- ----------------------------
INSERT INTO `r_sys_function` VALUES (1, 2, 'btn_addUser', '添加用户', 'addRUser.do');
INSERT INTO `r_sys_function` VALUES (2, 2, 'grid_setRole', '设置角色', 'listAllRRole.do,listTreeRole.do,listUserRRole.do,addUserRole.do,delUserRole.do');
INSERT INTO `r_sys_function` VALUES (3, 2, 'grid_resetPwsd', '重置密码', 'resetUserPassword.do');
INSERT INTO `r_sys_function` VALUES (4, 2, 'html_search', '查询', 'listRUser.do');
INSERT INTO `r_sys_function` VALUES (5, 3, 'html_search', '查询', 'listRRole.do');
INSERT INTO `r_sys_function` VALUES (6, 3, 'grid_setRight', '设置权限', 'listMenuByTabId.do, listRolePermissionByRoleId.do, listTopMenu.do,listRoleRelationInfo.do, addRolePermission.do, delRolePermission.do');
INSERT INTO `r_sys_function` VALUES (7, 3, 'grid_del', '删除角色', 'delRRole.do');
INSERT INTO `r_sys_function` VALUES (8, 3, 'btn_add', '添加角色', 'addRRole.do');
INSERT INTO `r_sys_function` VALUES (9, 4, 'html_search', '查询', 'listTopMenu.do,listMenuByTabId.do,listSysFunctionBySrId.do, addTopMenu.do');
INSERT INTO `r_sys_function` VALUES (10, 4, 'html_addSysFun', '添加操作权限', 'addSysFunction.do');
INSERT INTO `r_sys_function` VALUES (11, 4, 'grid_auth', '授权', 'listTreeRole.do, listAllRRole.do,addRolePermission.do,delRolePermission.do');
INSERT INTO `r_sys_function` VALUES (12, 4, 'grid_del', '删除操作权限', 'delRSysFunction.do');
INSERT INTO `r_sys_function` VALUES (13, 4, 'opt_node', '操作节点', 'addRSysRes.do,updateRSysRes.do,delRSysRes.do');
INSERT INTO `r_sys_function` VALUES (14, 5, 'html_search', '查询', 'listAllGroup.do,listRGroupUser.do,listRoleByGroupId.do');
INSERT INTO `r_sys_function` VALUES (15, 5, 'opt_node', '操作节点', 'addRGroup.do,updateRGroup.do,delRGroup.do');
INSERT INTO `r_sys_function` VALUES (16, 5, 'opt_groupUser', '操作用户组成员', 'listGroupNoAddUser.do,addGroupUser.do,delRGroupUser.do');
INSERT INTO `r_sys_function` VALUES (17, 5, 'opt_groupRole', '操作用户组角色', 'addRGroupRole.do,delRGroupRole.do');
INSERT INTO `r_sys_function` VALUES (18, 7, 'html_search', '查询', 'listBuiOrderInfo.do');
INSERT INTO `r_sys_function` VALUES (19, 7, 'grid_edit', '修改', 'updateBuiOrderInfo.do');
INSERT INTO `r_sys_function` VALUES (20, 7, 'grid_del', '删除', 'delBuiOrderInfo.do');
INSERT INTO `r_sys_function` VALUES (23, 7, 'btn_add', '新增', 'addBuiOrderInfo.do');
INSERT INTO `r_sys_function` VALUES (24, 7, 'btn_export', '导出', 'exportOrderInfo.do');

-- ----------------------------
-- Table structure for r_sys_res
-- ----------------------------
DROP TABLE IF EXISTS `r_sys_res`;
CREATE TABLE `r_sys_res`  (
  `sr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  `res_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `tab_id` int(11) NULL DEFAULT NULL COMMENT 'r_sys_res_tab表外键',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  PRIMARY KEY (`sr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_sys_res
-- ----------------------------
INSERT INTO `r_sys_res` VALUES (1, 0, '权限管理', 1, NULL);
INSERT INTO `r_sys_res` VALUES (2, 1, '用户管理', 1, 'permission/user.jsp');
INSERT INTO `r_sys_res` VALUES (3, 1, '角色管理', 1, 'permission/role.jsp');
INSERT INTO `r_sys_res` VALUES (4, 1, '资源管理', 1, 'permission/resMgr.jsp');
INSERT INTO `r_sys_res` VALUES (5, 1, '用户组管理', 1, 'permission/group.jsp');
INSERT INTO `r_sys_res` VALUES (6, 0, '销售管理', 2, '');
INSERT INTO `r_sys_res` VALUES (7, 6, '订单管理', 2, 'permission/orderInfo.jsp');

-- ----------------------------
-- Table structure for r_sys_res_tab
-- ----------------------------
DROP TABLE IF EXISTS `r_sys_res_tab`;
CREATE TABLE `r_sys_res_tab`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tab_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_sys_res_tab
-- ----------------------------
INSERT INTO `r_sys_res_tab` VALUES (1, '系统管理');
INSERT INTO `r_sys_res_tab` VALUES (2, '业务系统');

-- ----------------------------
-- Table structure for r_user
-- ----------------------------
DROP TABLE IF EXISTS `r_user`;
CREATE TABLE `r_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `state` tinyint(4) NOT NULL COMMENT '状态,1启用,0禁用',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_user
-- ----------------------------
INSERT INTO `r_user` VALUES (1, 'admin', '1000:8dc4c0d4e2d3585d3d47ed593c96a1bf0430247e09b09ccf:e6a6747ce9296cb6a67c95d278a4ab4cf37aa9a41801cc5f', 1, '2016-05-06 14:06:17', '2016-05-09 15:23:38');
INSERT INTO `r_user` VALUES (2, 'sell001', '1000:1051bbfa39ad517455b2b7656984ba8c6f241814748f0bd8:3fbec62c7e5b81b586db6bd238fff22b65820df5165891a1', 1, '2016-05-06 14:44:00', '2016-05-06 14:47:36');

-- ----------------------------
-- Table structure for r_user_role
-- ----------------------------
DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE `r_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_type` tinyint(4) NOT NULL COMMENT '角色类型,1个人,2用户组',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_user_role
-- ----------------------------
INSERT INTO `r_user_role` VALUES (1, 1, 1);
INSERT INTO `r_user_role` VALUES (2, 3, 2);

-- ----------------------------
-- Table structure for resource_apply
-- ----------------------------
DROP TABLE IF EXISTS `resource_apply`;
CREATE TABLE `resource_apply`  (
  `RA_Code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `RA_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `RA_UpdateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `RA_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `RA_UpdateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `RA_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `RA_Batch` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `RA_ApplyType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `RA_Mark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`RA_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_apply
-- ----------------------------
INSERT INTO `resource_apply` VALUES ('SQ2019091111295194525', '2019-09-11 11:29:51.00000', '2019-09-11 11:29:51.00000', 'U13800000000', 'U13800000000', 'A4', '2', '1ae5c8ab-75fb-43e4-b553-1a9b42245f44', '111111');
INSERT INTO `resource_apply` VALUES ('SQ2019091113015350630', '2019-09-11 13:01:53.00000', '2019-09-11 13:01:53.00000', 'U12341111333', 'U12341111333', 'A4', '2', '9b7304ac-4086-4492-95a3-db70b67f8365', '1111');
INSERT INTO `resource_apply` VALUES ('SQ2019091113041950297', '2019-09-11 13:04:19.00000', '2019-09-11 13:04:19.00000', 'U14622335533', 'U14622335533', 'A0', '2', '9b7304ac-4086-4492-95a3-db70b67f8365', '111');
INSERT INTO `resource_apply` VALUES ('SQ2019091113472694021', '2019-09-11 13:47:26.00000', '2019-09-11 13:47:26.00000', 'U13800000000', 'U13800000000', 'A1', '2', '2e4ebb00-9574-496e-aead-ad1afc64feb3', '123');
INSERT INTO `resource_apply` VALUES ('SQ2019091115091355642', '2019-09-11 15:09:13.00000', '2019-09-11 15:09:13.00000', 'U12341111333', 'U12341111333', 'B4', '2', '1ae5c8ab-75fb-43e4-b553-1a9b42245f44', '测试通知消息');
INSERT INTO `resource_apply` VALUES ('SQ2019091115385482062', '2019-09-11 15:38:54.00000', '2019-09-11 15:38:54.00000', 'U13800000000', 'U13800000000', 'B0', '1', '12edbf07-dccf-42dd-9d29-9524e3225a3e', '测试文件上传预览');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `POLITICS_STATUS` int(11) NOT NULL DEFAULT 1,
  `NATIONALITY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `STU_NO` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GENDER` tinyint(4) NULL DEFAULT NULL,
  `DEPARTMENT` int(11) NULL DEFAULT NULL,
  `ADDRESS` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `REGIST_DATE` datetime(0) NULL DEFAULT NULL,
  `BIRTHDAY` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '李四', 1, '汉族', 'NO000001', 1, 16, 'CN', '000013398761567', '2011-12-02 00:00:00', '2012-10-17 00:00:00');
INSERT INTO `student` VALUES (2, '张三', 1, '汉族', 'NO000002', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2011-12-02 12:11:00', NULL);
INSERT INTO `student` VALUES (3, '张三', 1, '汉族', 'NO000003', 0, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2011-12-02 00:00:00', NULL);
INSERT INTO `student` VALUES (4, '张三', 1, '汉族', 'NO000004', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2011-12-02 00:00:00', '2012-10-19 00:00:00');
INSERT INTO `student` VALUES (5, '张三', 1, '汉族', 'NO000005', 0, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '013398761567', '2011-12-02 00:00:00', NULL);
INSERT INTO `student` VALUES (6, 'bbb', 2, '汉族', 'NO000006', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '0013398761567', '2010-02-16 00:00:00', '2013-01-08 00:00:00');
INSERT INTO `student` VALUES (7, '张三', 1, '汉族', 'NO000007', 1, 17, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2012-01-18 00:00:00', '2012-10-06 00:00:00');
INSERT INTO `student` VALUES (8, '张三1', 1, '汉族', 'NO0000011', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2011-12-02 00:00:00', '2012-10-05 00:00:00');
INSERT INTO `student` VALUES (9, '张三2', 1, '汉族', 'NO0000012', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13398761567', '2011-12-02 00:00:00', '2014-06-12 00:00:00');
INSERT INTO `student` VALUES (10, '张三3', 1, '汉族', 'NO0000013', 0, 16, '浙江省杭州市', '013398761567', '2011-12-02 00:00:00', '2012-10-16 00:00:00');
INSERT INTO `student` VALUES (11, '张三4', 1, '汉族', 'NO0000014', 1, 18, '河南省开封市', '13398761567', '2011-12-02 00:00:00', NULL);
INSERT INTO `student` VALUES (13, '张三6', 1, '汉族', 'NO0000016', 1, 16, '河南省濮阳市', '13398761567', '2011-12-02 00:00:00', '2012-10-26 00:00:00');
INSERT INTO `student` VALUES (14, '张三7', 1, '汉族', 'NO0000017', 1, 16, '湖北省武汉市', '13398761567', '2011-12-02 00:00:00', NULL);
INSERT INTO `student` VALUES (17, '张克', 1, '汉族', 'NO00000013', 0, 16, '湖南', '13398761567', '2012-01-04 00:00:00', '2012-10-15 00:00:00');
INSERT INTO `student` VALUES (18, '张克4', 0, '汉族', 'NO00000014', 1, 19, '湖南4', '13398761567', '2012-01-29 00:00:00', '2012-10-18 00:00:00');
INSERT INTO `student` VALUES (19, '张克45', 2, '汉族', 'NO00000015', 0, 16, '湖南5', '13398761567', '2012-02-14 00:00:00', '2012-10-08 00:00:00');
INSERT INTO `student` VALUES (20, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '13398761567', '2011-12-02 00:00:00', '2013-12-12 00:00:00');
INSERT INTO `student` VALUES (21, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '013398761567', '2011-12-02 00:00:00', '2008-10-15 00:00:00');
INSERT INTO `student` VALUES (22, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '0013398761567', '2011-12-02 00:00:00', '2013-01-07 00:00:00');
INSERT INTO `student` VALUES (23, 'JIM', 2, '汉族', 'NO0000001', 1, 16, 'USA', '00013398761567', '2011-12-02 00:00:00', '2013-01-07 00:00:00');
INSERT INTO `student` VALUES (24, 'JIM', 2, '汉族', 'NO0000001', 1, 16, 'USA', '0013398761567', '2011-12-02 00:00:00', '2012-10-01 00:00:00');
INSERT INTO `student` VALUES (25, 'JIM', 1, '汉族', 'NO0000001', 1, 17, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-12 00:00:00');
INSERT INTO `student` VALUES (26, 'JIM', 2, '汉族', 'NO0000001', 1, 16, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-18 00:00:00');
INSERT INTO `student` VALUES (27, 'JIM', 1, '汉族', 'NO0000001', 1, 18, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-19 00:00:00');
INSERT INTO `student` VALUES (30, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '013398761567', '2011-12-02 00:00:00', '2013-01-07 00:00:00');
INSERT INTO `student` VALUES (31, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-16 00:00:00');
INSERT INTO `student` VALUES (33, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-27 00:00:00');
INSERT INTO `student` VALUES (34, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '13398761567', '2011-12-02 00:00:00', '2012-10-27 00:00:00');
INSERT INTO `student` VALUES (35, 'JIM', 1, '汉族', 'NO0000001', 1, 16, 'USA', '013398761567', '2011-12-02 00:00:00', '2012-10-27 00:00:00');
INSERT INTO `student` VALUES (36, 'Jus', 0, '回族', 'NO0000001', 0, 16, 'UK1wer', '13398761560', '2012-09-24 00:00:00', '2012-09-28 00:00:00');
INSERT INTO `student` VALUES (37, '安布雷拉', 1, '汉族', 'NO00909', 0, 21, 'USA', '13398761567', '2012-04-27 00:00:00', '2012-08-07 00:00:00');
INSERT INTO `student` VALUES (38, '赵六', 2, '汉族', 'NO20120924', 1, NULL, '上海市黄浦区福建中路225号中悦大厦503室（人民广场福州路上海书城斜对面）', '000', '2012-09-21 00:00:00', '2012-09-14 00:00:00');
INSERT INTO `student` VALUES (39, '钱七1', 1, '汉族', 'NO2013121301', 1, 16, 'UK1', '234234', '2013-12-17 00:00:00', '2013-11-30 00:00:00');
INSERT INTO `student` VALUES (40, '周杰伦', 1, '汉族', 'NO20140506001', 0, 16, '阿斯顿飞', '13777777777', '2014-06-06 00:00:00', '2014-06-12 00:00:00');
INSERT INTO `student` VALUES (41, '周杰伦', 2, '汉族', 'NO20140506002', 1, 16, '北京市朝阳区广顺北大街33号院1号楼福码大厦', '13777777777', '2014-06-01 00:00:00', '2014-06-12 00:00:00');
INSERT INTO `student` VALUES (42, '刘德华', 0, '汉族', 'NO20140506003', 0, 16, '台北', '13777777777', '2014-06-10 00:00:00', '2013-11-07 00:00:00');

-- ----------------------------
-- Table structure for system_file
-- ----------------------------
DROP TABLE IF EXISTS `system_file`;
CREATE TABLE `system_file`  (
  `SF_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SF_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_State` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_BussinessCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_FileType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_FileName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_Path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SF_TempPath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`SF_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_file
-- ----------------------------
INSERT INTO `system_file` VALUES ('517cffb8238246c397e3782d2e2710a3', '2019-12-20 09:46:11.00000', 'U13800000000', '1', '1ae5c8ab-75fb-43e4-b553-1a9b42245f44', 'xlsx文件', '行政-学生管理系统问题及需求跟踪表V1.0.0[F06-S00-P00]_I.xlsx', 'E:\\UPLOAD\\行政-学生管理系统问题及需求跟踪表V1.0.0[F06-S00-P00]_IU138000000002019122009461135430.xlsx', '517cffb8238246c397e3782d2e2710a3.xlsx');
INSERT INTO `system_file` VALUES ('5cbd2a99001d4f18b1f5bd66a7bc3ee7', '2019-09-29 14:26:31.00000', 'U13800000000', '1', '12edbf07-dccf-42dd-9d29-9524e3225a3e', 'xls文件', '松江政务云资源申请系统数据库设计20190806.xls', 'E:\\UPLOAD\\松江政务云资源申请系统数据库设计20190806U138000000002019092914263125558.xls', '5cbd2a99001d4f18b1f5bd66a7bc3ee7.xls');
INSERT INTO `system_file` VALUES ('79c171ea339340328269ea88645bbe0b', '2019-09-29 14:59:28.00000', 'U13800000000', '1', '12edbf07-dccf-42dd-9d29-9524e3225a3e', 'xls文件', '教职工部门角色匹配1.xls', 'E:\\UPLOAD\\教职工部门角色匹配1U138000000002019092914592820012.xls', '79c171ea339340328269ea88645bbe0b.xls');
INSERT INTO `system_file` VALUES ('aeb9515831514f8994b2fb181c6724e0', '2020-03-10 15:47:06.00000', 'U13800000000', '1', '12edbf07-dccf-42dd-9d29-9524e3225a3e', 'xls文件', 'ceshi.xls', 'E:\\UPLOAD\\ceshiU138000000002020031015470649585.xls', 'aeb9515831514f8994b2fb181c6724e0.xls');

-- ----------------------------
-- Table structure for system_filetemplate
-- ----------------------------
DROP TABLE IF EXISTS `system_filetemplate`;
CREATE TABLE `system_filetemplate`  (
  `SFT_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SFT_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SFT_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SFT_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SFT_TemplateType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SFT_ModelCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`SFT_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_filetemplate
-- ----------------------------
INSERT INTO `system_filetemplate` VALUES ('12edbf07-dccf-42dd-9d29-9524e3225a3e', '2019-09-11 11:28:16.00000', 'U13800000000', '1', 'Z1', '');
INSERT INTO `system_filetemplate` VALUES ('1ae5c8ab-75fb-43e4-b553-1a9b42245f44', '2019-09-11 11:28:25.00000', 'U13800000000', '1', 'A1', '');
INSERT INTO `system_filetemplate` VALUES ('2e4ebb00-9574-496e-aead-ad1afc64feb3', '2019-09-11 11:28:34.00000', 'U13800000000', '1', 'B1', '');
INSERT INTO `system_filetemplate` VALUES ('9b7304ac-4086-4492-95a3-db70b67f8365', '2019-09-11 11:28:43.00000', 'U13800000000', '1', 'C1', '');

-- ----------------------------
-- Table structure for system_message
-- ----------------------------
DROP TABLE IF EXISTS `system_message`;
CREATE TABLE `system_message`  (
  `SM_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SM_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_Content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_BussinessCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_Model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_Operate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SM_Notified` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SM_Readed` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SM_WXNotified` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SM_DXNotified` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SM_YJNotified` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SM_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_message
-- ----------------------------
INSERT INTO `system_message` VALUES ('02a26943-3a65-4940-9b0e-ab8a51238e91', '2019-09-11 12:59:02.00000', 'U13800000000', '1', '资源申请[SQ2019091111295194525]已通过一审，正在进行二审。', 'SQ2019091111295194525', 'M1.1.1', '一审通过', 'resource/applyDetail.vue', 'U13800000000,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('136a868f-047d-43d0-ac1e-89c25ca51dc9', '2019-09-11 15:09:16.00000', 'U12341111333', '1', '资源申请[SQ2019091115091355642]已提交,正在审批中。', 'SQ2019091115091355642', 'M1.1.1', '提交', 'resource/applyDetail.vue', 'U13800000000,U13133221133', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('27a56d54-0aa4-499b-a8a8-0861f32d46a9', '2019-09-11 13:04:30.00000', 'U14622335533', '1', '资源申请[SQ2019091113041950297]已提交,正在审批中。', 'SQ2019091113041950297', 'M1.1.1', '提交', 'resource/applyDetail.vue', 'U13800000000,U13133221133', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('2c1cd0f3-f1c1-46fc-93c9-81cae6522bfd', '2019-09-11 13:10:40.00000', 'U13800000000', '1', '问题[SQ2019091113102181940]已发布。', 'SQ2019091113102181940', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('2e4a087e-b6d8-4ac5-bfd9-ff71f53b5eb3', '2019-09-11 12:59:21.00000', 'U13800000000', '1', '资源申请[SQ2019091111295194525]已通过四审。', 'SQ2019091111295194525', 'M1.1.1', '四审通过', 'resource/applyDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('360b61d6-74c1-4415-a16d-d6ac0991c2f8', '2019-09-11 13:05:44.00000', 'U15632241251', '1', '资源申请[SQ2019091113015350630]已通过四审。', 'SQ2019091113015350630', 'M1.1.1', '四审通过', 'resource/applyDetail.vue', 'U13800000000,U15632241251,U12341111333', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('536a6586-890a-4d16-b756-a7093e681b96', '2019-09-11 12:59:11.00000', 'U13800000000', '1', '资源申请[SQ2019091111295194525]已通过二审，正在进行三审。', 'SQ2019091111295194525', 'M1.1.1', '二审通过', 'resource/applyDetail.vue', 'U13800000000,U14622335533', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('58ccf646-2668-4275-a588-ac1bbdcbf4e1', '2019-09-11 15:10:30.00000', 'U13322113311', '1', '资源申请[SQ2019091115091355642]已通过二审，正在进行三审。', 'SQ2019091115091355642', 'M1.1.1', '二审通过', 'resource/applyDetail.vue', 'U13800000000,U14622335533', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('5d0c3d69-5172-4091-83ea-244a4e32f6d3', '2019-09-11 13:03:23.00000', 'U13322113311', '1', '资源申请[SQ2019091113015350630]已通过二审，正在进行三审。', 'SQ2019091113015350630', 'M1.1.1', '二审通过', 'resource/applyDetail.vue', 'U13800000000,U14622335533', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('615e1298-5ba9-47b1-b3f0-793635b54f0c', '2019-09-11 13:07:47.00000', 'U13322113311', '1', '问题[SQ2019091113063629760]已答复。', 'SQ2019091113063629760', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251,U12341111333', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('6addb692-b5a6-4bd4-902f-bb5a58bbc276', '2019-09-11 13:02:33.00000', 'U13133221133', '1', '资源申请[SQ2019091113015350630]已通过一审，正在进行二审。', 'SQ2019091113015350630', 'M1.1.1', '一审通过', 'resource/applyDetail.vue', 'U13800000000,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('7bf84b48-e7e1-460b-9128-474f0ce83594', '2019-09-11 15:10:58.00000', 'U14622335533', '1', '资源申请[SQ2019091115091355642]已通过三审，正在进行四审。', 'SQ2019091115091355642', 'M1.1.1', '三审通过', 'resource/applyDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('86880d64-d834-40a2-b598-97d8dd973c10', '2019-09-11 13:10:51.00000', 'U13800000000', '1', '问题[SQ2019091113102181940]已答复。', 'SQ2019091113102181940', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('8de8b36e-2968-4c12-91c1-35c5aefaa54d', '2019-09-11 13:10:06.00000', 'U13800000000', '1', '问题[SQ2019091113003816449]已答复。', 'SQ2019091113003816449', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251,U12341111333', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('90495fe5-9c9a-4724-9f10-f30bad846d0f', '2019-09-11 13:01:55.00000', 'U12341111333', '1', '资源申请[SQ2019091113015350630]已提交,正在审批中。', 'SQ2019091113015350630', 'M1.1.1', '提交', 'resource/applyDetail.vue', 'U13800000000,U13133221133', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('9687a070-461b-4c95-8ccb-dc11e7fab350', '2019-09-11 13:03:58.00000', 'U14622335533', '1', '资源申请[SQ2019091113015350630]已通过三审，正在进行四审。', 'SQ2019091113015350630', 'M1.1.1', '三审通过', 'resource/applyDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('9e258af1-6174-4990-a01d-48a262bbc975', '2019-09-11 15:11:26.00000', 'U15632241251', '1', '资源申请[SQ2019091115091355642]四审被驳回。', 'SQ2019091115091355642', 'M1.1.1', '四审驳回', 'resource/applyDetail.vue', 'U13800000000,U14622335533,U13133221133,U13322113311,U12341111333', 'U12341111333', NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('a43e6580-d1b3-4378-816d-d446870802fb', '2019-09-11 13:00:40.00000', 'U12341111333', '1', '问题[SQ2019091113003816449]已发布。', 'SQ2019091113003816449', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('a76ad035-80ff-4117-8d6c-2e3db7098a84', '2019-09-11 11:29:56.00000', 'U13800000000', '1', '资源申请[SQ2019091111295194525]已提交,正在审批中。', 'SQ2019091111295194525', 'M1.1.1', '提交', 'resource/applyDetail.vue', 'U13800000000,U13133221133', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('bf3e71f1-e338-4943-a742-700defbf1987', '2019-09-11 13:10:22.00000', 'U13800000000', '1', '问题[SQ2019091113102181940]已发布。', 'SQ2019091113102181940', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('c330fbec-1a3b-4944-b347-5be924d85758', '2019-09-11 13:06:38.00000', 'U12341111333', '1', '问题[SQ2019091113063629760]已发布。', 'SQ2019091113063629760', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('c65bc5a2-72cd-4e84-932e-7d73f96b0b64', '2019-09-11 13:07:52.00000', 'U13322113311', '1', '问题[SQ2019091113003816449]已答复。', 'SQ2019091113003816449', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251,U12341111333', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('c66384a8-2694-4104-85b1-56f8c6860076', '2019-09-11 13:10:30.00000', 'U13800000000', '1', '问题[SQ2019091113102181940]已答复。', 'SQ2019091113102181940', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('c733dd6a-fe69-45d9-9966-5a1fe8bcdd77', '2019-09-11 12:59:16.00000', 'U13800000000', '1', '资源申请[SQ2019091111295194525]已通过三审，正在进行四审。', 'SQ2019091111295194525', 'M1.1.1', '三审通过', 'resource/applyDetail.vue', 'U13800000000,U15632241251', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('d059cc0a-d79e-4f2b-be39-afe9d95ca27a', '2019-09-11 13:08:41.00000', 'U12341111333', '1', '问题[SQ2019091113065586579]已发布。', 'SQ2019091113065586579', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('d441e50a-33c2-4e43-b700-44a070e9798a', '2019-09-11 13:47:43.00000', 'U13800000000', '1', '资源申请[SQ2019091113472694021]已通过一审，正在进行二审。', 'SQ2019091113472694021', 'M1.1.1', '一审通过', 'resource/applyDetail.vue', 'U13800000000,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('d45deafe-1526-4eba-93c5-10054c3338da', '2019-09-11 13:06:56.00000', 'U12341111333', '1', '问题[SQ2019091113065586579]已发布。', 'SQ2019091113065586579', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('d82606ac-d943-4335-a06e-ed4e5b2d0725', '2019-09-11 13:09:09.00000', 'U12341111333', '1', '问题[SQ2019091113003816449]已发布。', 'SQ2019091113003816449', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('eccf50be-de08-4c4b-b03d-9411988cfe2f', '2019-09-11 15:09:45.00000', 'U13133221133', '1', '资源申请[SQ2019091115091355642]已通过一审，正在进行二审。', 'SQ2019091115091355642', 'M1.1.1', '一审通过', 'resource/applyDetail.vue', 'U13800000000,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('f1413e2c-d325-4078-ab33-5822c8d6cacc', '2019-09-11 13:08:56.00000', 'U12341111333', '1', '问题[SQ2019091113063629760]已发布。', 'SQ2019091113063629760', 'M2.2.1', '问题', 'workorder/orderDetail.vue', 'U13800000000,U14622335533,U15632241251,U13322113311', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('f99239e3-2f22-4b53-a6e8-c0b44807422f', '2019-09-11 13:07:39.00000', 'U13322113311', '1', '问题[SQ2019091113065586579]已答复。', 'SQ2019091113065586579', 'M2.2.1', '答复', 'workorder/orderDetail.vue', 'U13800000000,U15632241251,U12341111333', NULL, NULL, NULL, NULL);
INSERT INTO `system_message` VALUES ('fe389d4d-5de0-48b5-be11-41a00f45d1ef', '2019-09-11 13:47:29.00000', 'U13800000000', '1', '资源申请[SQ2019091113472694021]已提交,正在审批中。', 'SQ2019091113472694021', 'M1.1.1', '提交', 'resource/applyDetail.vue', 'U13800000000,U13133221133', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for system_messageconfig
-- ----------------------------
DROP TABLE IF EXISTS `system_messageconfig`;
CREATE TABLE `system_messageconfig`  (
  `SMC_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SMC_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_Content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_Model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_Operate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_Notified` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SMC_Version` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SMC_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_messageconfig
-- ----------------------------
INSERT INTO `system_messageconfig` VALUES ('211e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '问题[1]已发布。', 'M2.1', '31b026fa-6b07-4a7e-8a26-9bf8b049ac05', 'workorder/orderDetail.vue', '4,5,6', '1');
INSERT INTO `system_messageconfig` VALUES ('221e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '资源申请[1]已提交,正在审批中。', 'M2.1', '61a697b0-e4d2-4a0f-9c1e-2d066feb3b9f', 'resource/applyDetail.vue', '3', '1');
INSERT INTO `system_messageconfig` VALUES ('241e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '问题[1]已答复。', 'M2.1', '622a5a15-bc4f-4089-bcb7-d7030810e17f', 'workorder/orderDetail.vue', '6', '1');
INSERT INTO `system_messageconfig` VALUES ('321e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '资源申请[1]已通过一审，正在进行二审。', 'M2.1', '622a5a15-bc4f-4089-bcb7-d7030810e17f', 'resource/applyDetail.vue', '4', '1');
INSERT INTO `system_messageconfig` VALUES ('421e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '资源申请[1]已通过二审，正在进行三审。', 'M1.1', '12edbf07-dccf-42dd-9d29-9524e3225a4e', 'resource/applyDetail.vue', '5', '1');
INSERT INTO `system_messageconfig` VALUES ('521e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '资源申请[1]已通过三审，正在进行四审。', 'M1.1', '82f8501c-5847-486d-8cab-139a43f6cfc9', 'resource/applyDetail.vue', '6', '1');
INSERT INTO `system_messageconfig` VALUES ('621e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', '资源申请[1]已通过四审。', 'M1.1', '12edbf07-dccf-42dd-9d29-9524e3225a3e', 'resource/applyDetail.vue', '5', '1');
INSERT INTO `system_messageconfig` VALUES ('621e2eca-bf4a-4844-baae-7259db17b42a', '2019-08-21 15:22:03.00000', 'U13800000000', '1', '资源申请[1]一审被驳回。', 'M1.1', '12edbf07-dccf-42dd-9d29-9524e3225a4e', 'resource/applyDetail.vue', '3', '1');
INSERT INTO `system_messageconfig` VALUES ('621e2eca-bf4a-4844-baae-7259db17b43a', '2019-08-21 15:22:03.00000', 'U13800000000', '1', '资源申请[1]二审被驳回。', 'M1.1', '622a5a15-bc4f-4089-bcb7-d7030810e17f', 'resource/applyDetail.vue', '3', '1');
INSERT INTO `system_messageconfig` VALUES ('621e2eca-bf4a-4844-baae-7259db17b44a', '2019-08-21 15:22:03.00000', 'U13800000000', '1', '资源申请[1]三审被驳回。', 'M1.1', '12edbf07-dccf-42dd-9d29-9524e3225a4e', 'resource/applyDetail.vue', '3,4', '1');
INSERT INTO `system_messageconfig` VALUES ('621e2eca-bf4a-4844-baae-7259db17b45a', '2019-08-21 15:22:03.00000', 'U13800000000', '1', '资源申请[1]四审被驳回。', 'M1.1', '30b0e8a2-1c27-4b71-9ea0-c3223a52e185', 'resource/applyDetail.vue', '3,4', '1');

-- ----------------------------
-- Table structure for system_processconfig
-- ----------------------------
DROP TABLE IF EXISTS `system_processconfig`;
CREATE TABLE `system_processconfig`  (
  `SPC_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SPC_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_State` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_Model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_Version` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_Step` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPC_Role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`SPC_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_processconfig
-- ----------------------------
INSERT INTO `system_processconfig` VALUES ('121e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', 'M1.2', '1', 'A3', '5,6');
INSERT INTO `system_processconfig` VALUES ('121e2eca-bf4a-4844-baae-7259db17b49a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', 'M2.2', '1', 'A2', '5');
INSERT INTO `system_processconfig` VALUES ('121e2eca-bf4a-4844-baae-7259db17b49c', '2019-08-06 16:00:58.00000', 'U13800000000', '1', 'M1.4', '1', 'A1', '4');
INSERT INTO `system_processconfig` VALUES ('221e2eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', 'M3.1', '1', 'A0', '3');
INSERT INTO `system_processconfig` VALUES ('221e3eca-bf4a-4844-baae-7259db17b41a', '2019-08-06 16:00:58.00000', 'U13800000000', '1', 'M2.1', '1', 'B0', '2');

-- ----------------------------
-- Table structure for system_processrecord
-- ----------------------------
DROP TABLE IF EXISTS `system_processrecord`;
CREATE TABLE `system_processrecord`  (
  `SPR_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `SPR_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_State` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_Model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_ModelCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_Action` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_Result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `SPR_Description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SPR_Batch` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1',
  `SPR_ConfigNo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SPR_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_processrecord
-- ----------------------------
INSERT INTO `system_processrecord` VALUES ('025c7403-dbc6-4e97-9f4c-d60b5e0009ef', '2019-09-11 13:05:44.00000', 'U15632241251', '1', 'M1.1.1', 'SQ2019091113015350630', '四审', 'Y', '111', '2', '121e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('0ec09665-cbc3-462e-82d1-4fed08fa954e', '2019-09-11 13:04:30.00000', 'U14622335533', '1', 'M1.1.1', 'SQ2019091113041950297', '提交', 'Y', NULL, '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('16a04da4-5656-460e-bf36-7e0c18bc6339', '2019-09-11 13:47:29.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091113472694021', '提交', 'Y', NULL, '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('174cb868-8110-49ba-a2b8-866470833635', '2019-09-11 13:01:55.00000', 'U12341111333', '1', 'M1.1.1', 'SQ2019091113015350630', '提交', 'Y', NULL, '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('2db48c3f-82ac-4f2e-a5a2-fd753884e054', '2019-09-11 15:11:26.00000', 'U15632241251', '1', 'M1.1.1', 'SQ2019091115091355642', '四审', 'N', '1', '2', '121e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('56f2bd18-f08a-40b0-b24e-c3adf01b25a8', '2019-09-11 12:59:16.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091111295194525', '三审', 'Y', '333', '2', '121e2eca-bf4a-4844-baae-7259db17b49a');
INSERT INTO `system_processrecord` VALUES ('6bd9d1c8-f9a3-48b2-a3ca-97d73d9b6996', '2019-09-11 13:03:23.00000', 'U13322113311', '1', 'M1.1.1', 'SQ2019091113015350630', '二审', 'Y', '2222', '2', '121e2eca-bf4a-4844-baae-7259db17b49c');
INSERT INTO `system_processrecord` VALUES ('75ed2309-8a66-4d1c-ada5-fcad9acf520f', '2019-09-11 15:10:30.00000', 'U13322113311', '1', 'M1.1.1', 'SQ2019091115091355642', '二审', 'Y', '111', '2', '121e2eca-bf4a-4844-baae-7259db17b49c');
INSERT INTO `system_processrecord` VALUES ('782bd469-f969-406e-b3a0-ada0bc13e4ff', '2019-09-11 12:59:21.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091111295194525', '四审', 'Y', '444', '2', '121e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('889c6c64-e33e-433c-9fdd-ddc54868d0af', '2019-09-11 13:03:58.00000', 'U14622335533', '1', 'M1.1.1', 'SQ2019091113015350630', '三审', 'Y', '111', '2', '121e2eca-bf4a-4844-baae-7259db17b49a');
INSERT INTO `system_processrecord` VALUES ('8adf6f17-c21b-41d7-837e-400b8a5c6ec0', '2019-09-11 12:59:11.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091111295194525', '二审', 'Y', '222', '2', '121e2eca-bf4a-4844-baae-7259db17b49c');
INSERT INTO `system_processrecord` VALUES ('9cab3d03-dc88-451b-943f-1a5a178f3049', '2019-09-11 13:47:43.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091113472694021', '一审', 'Y', '1111', '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('a4c781ca-f280-4bb3-a690-8da9e4598f2f', '2019-09-11 11:29:56.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091111295194525', '提交', 'Y', NULL, '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('bc3c0b67-5650-4424-82e3-fccefae1f9a1', '2019-09-11 15:09:45.00000', 'U13133221133', '1', 'M1.1.1', 'SQ2019091115091355642', '一审', 'Y', '123', '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('d61b7f7a-082f-45d2-b6e8-1431df86c655', '2019-09-11 12:59:02.00000', 'U13800000000', '1', 'M1.1.1', 'SQ2019091111295194525', '一审', 'Y', '111', '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('e7db6108-f786-4586-bdd7-54ee788adb85', '2019-09-11 13:02:33.00000', 'U13133221133', '1', 'M1.1.1', 'SQ2019091113015350630', '一审', 'Y', '1', '2', '221e2eca-bf4a-4844-baae-7259db17b41a');
INSERT INTO `system_processrecord` VALUES ('e9516ed9-2ff5-4a75-ab82-6cffb57daab1', '2019-09-11 15:10:58.00000', 'U14622335533', '1', 'M1.1.1', 'SQ2019091115091355642', '三审', 'Y', '3333', '2', '121e2eca-bf4a-4844-baae-7259db17b49a');
INSERT INTO `system_processrecord` VALUES ('f314cc7f-ad08-4650-b9a7-9f13462bafc3', '2019-09-11 15:09:16.00000', 'U12341111333', '1', 'M1.1.1', 'SQ2019091115091355642', '提交', 'Y', NULL, '2', '221e2eca-bf4a-4844-baae-7259db17b41a');

-- ----------------------------
-- Table structure for wcheck
-- ----------------------------
DROP TABLE IF EXISTS `wcheck`;
CREATE TABLE `wcheck`  (
  `time` datetime(0) NULL DEFAULT NULL,
  `usercode` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stime` datetime(0) NULL DEFAULT NULL,
  `etime` datetime(0) NULL DEFAULT NULL,
  `outdesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wcheck
-- ----------------------------
INSERT INTO `wcheck` VALUES ('2020-01-14 10:19:16', '1111111', '张三', '2020-01-14 10:19:43', '2020-01-14 10:19:46', '侧额是', 1);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0235', '张三', '2019-12-23 09:05:09', '2019-12-23 17:40:12', '正常', 2);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0235', '张三', '2019-12-23 09:05:09', '2019-12-23 17:38:12', '正常', 3);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0266', '李四', '2019-12-23 09:05:09', '2019-12-23 19:45:09', '正常', 4);
INSERT INTO `wcheck` VALUES ('2019-12-23 08:55:45', '0455', '小王子', '2019-12-23 08:55:45', '2019-12-23 18:05:08', '正常', 5);
INSERT INTO `wcheck` VALUES ('2019-12-23 08:55:45', '0111', '王子11', '2019-12-23 08:55:45', '2019-12-23 18:05:08', '正常', 6);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0222', '李四22', '2019-12-23 09:05:09', '2019-12-23 19:45:09', '正常', 7);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0333', '张三33', '2019-12-23 09:05:09', '2019-12-23 17:38:12', '正常', 8);
INSERT INTO `wcheck` VALUES ('2019-12-23 08:55:45', '0123', '测试1', '2019-12-23 08:55:45', '2019-12-23 18:05:08', '正常', 9);
INSERT INTO `wcheck` VALUES ('2019-12-23 09:05:09', '0234', '测试2', '2019-12-23 09:05:09', '2019-12-23 19:45:09', '正常', 10);
INSERT INTO `wcheck` VALUES ('2019-12-23 08:51:09', '0456', '测试3', '2019-12-23 08:51:09', '2019-12-23 17:33:12', '正常', 11);

-- ----------------------------
-- Table structure for workorder_class
-- ----------------------------
DROP TABLE IF EXISTS `workorder_class`;
CREATE TABLE `workorder_class`  (
  `WC_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WC_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `WC_UpdateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `WC_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WC_UpdateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WC_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WC_Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WC_Description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`WC_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workorder_class
-- ----------------------------
INSERT INTO `workorder_class` VALUES ('225f0750-df6c-455a-a58e-3d561b2dc59a', '2019-09-11 11:29:20.00000', '2019-09-16 14:59:38.00000', 'U13800000000', 'U13800000000', '1', '问题分类3', '3');
INSERT INTO `workorder_class` VALUES ('66663706-b946-4605-8176-c4ae54eacf0f', '2019-09-16 14:59:58.00000', '2019-09-16 15:18:35.00000', '', '', '1', '问题分类6', '6');
INSERT INTO `workorder_class` VALUES ('733c903a-fd25-4a9c-a9eb-c2597f3c10d5', '2019-09-11 11:29:03.00000', '2019-09-11 11:29:03.00000', 'U13800000000', 'U13800000000', '1', '问题分类1', '1');
INSERT INTO `workorder_class` VALUES ('80f1797a-b16a-47e3-98b3-29640f68734f', '2019-09-16 15:03:47.00000', '2019-09-16 15:18:54.00000', 'U13800000000', 'U13800000000', '1', '问题分类7', '12345');
INSERT INTO `workorder_class` VALUES ('c70f6746-24bf-47c4-bacc-9f7539f03f01', '2019-09-11 11:29:11.00000', '2019-09-16 14:58:59.00000', 'U13800000000', 'U13800000000', '1', '问题分类2', '2');
INSERT INTO `workorder_class` VALUES ('d8025d53-0cd8-4b32-88f3-6abc81651aa3', '2019-09-16 10:44:30.00000', '2019-09-16 11:04:35.00000', 'U13800000000', 'U13800000000', '1', '问题分类1', '1123');
INSERT INTO `workorder_class` VALUES ('e767f94c-e970-4843-b26c-34f849861542', '2019-09-11 11:29:42.00000', '2019-09-16 11:16:22.00000', 'U13800000000', 'U13800000000', '1', '问题分类5', '5');
INSERT INTO `workorder_class` VALUES ('fca8bd94-1914-4818-8db7-689579ecea35', '2019-09-11 11:29:32.00000', '2019-09-16 15:18:47.00000', 'U13800000000', 'U13800000000', '1', '问题分类4', '4');

-- ----------------------------
-- Table structure for workorder_order
-- ----------------------------
DROP TABLE IF EXISTS `workorder_order`;
CREATE TABLE `workorder_order`  (
  `WO_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WO_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `WO_UpdateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `WO_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WO_UpdateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WO_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WO_Class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_Description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_Telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_Mail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_Replier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_Reply` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WO_ReplyTime` datetime(5) NULL DEFAULT NULL,
  PRIMARY KEY (`WO_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workorder_order
-- ----------------------------
INSERT INTO `workorder_order` VALUES ('SQ2019091113003816449', '2019-09-11 13:00:38.00000', '2019-09-11 13:09:09.00000', 'U12341111333', '', '2', NULL, NULL, '11111111111111', '12312312312', NULL, NULL, NULL);
INSERT INTO `workorder_order` VALUES ('SQ2019091113063629760', '2019-09-11 13:06:36.00000', '2019-09-11 13:08:56.00000', 'U12341111333', '', '1', NULL, NULL, '11111111111111111', '1111111111133333333', NULL, NULL, NULL);
INSERT INTO `workorder_order` VALUES ('SQ2019091113065586579', '2019-09-11 13:06:55.00000', '2019-09-11 13:08:41.00000', 'U12341111333', '', '1', NULL, NULL, '31312313131', '11111111111111111111', NULL, NULL, NULL);
INSERT INTO `workorder_order` VALUES ('SQ2019091113102181940', '2019-09-11 13:10:21.00000', '2019-09-11 13:10:40.00000', 'U13800000000', '', '3', NULL, NULL, '1111111111', '22222222222', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for workorder_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `workorder_orderdetail`;
CREATE TABLE `workorder_orderdetail`  (
  `WOD_Code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_MainCode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_CreateTime` datetime(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5),
  `WOD_CreateUser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_State` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_Type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_Class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_Description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `WOD_Reply` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`WOD_Code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workorder_orderdetail
-- ----------------------------
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113003830472', 'SQ2019091113003816449', '2019-09-11 13:00:38.00000', 'U12341111333', '1', 'Q', '733c903a-fd25-4a9c-a9eb-c2597f3c10d5', '123', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113063687036', 'SQ2019091113063629760', '2019-09-11 13:06:36.00000', 'U12341111333', '1', 'Q', 'fca8bd94-1914-4818-8db7-689579ecea35', '123123123', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113065586929', 'SQ2019091113065586579', '2019-09-11 13:06:55.00000', 'U12341111333', '1', 'Q', 'c70f6746-24bf-47c4-bacc-9f7539f03f01', '1111', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113073919443', 'SQ2019091113065586579', '2019-09-11 13:07:39.00000', 'U13322113311', '1', 'A', 'c70f6746-24bf-47c4-bacc-9f7539f03f01', '1111', '2222222');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113074763527', 'SQ2019091113063629760', '2019-09-11 13:07:47.00000', 'U13322113311', '1', 'A', 'fca8bd94-1914-4818-8db7-689579ecea35', '123123123', '123213131');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113075237662', 'SQ2019091113003816449', '2019-09-11 13:07:52.00000', 'U13322113311', '1', 'A', '733c903a-fd25-4a9c-a9eb-c2597f3c10d5', '123', '123');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113083994858', 'SQ2019091113065586579', '2019-09-11 13:08:39.00000', 'U12341111333', '1', 'Q', '225f0750-df6c-455a-a58e-3d561b2dc59a', '1111', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113085339817', 'SQ2019091113063629760', '2019-09-11 13:08:53.00000', 'U12341111333', '1', 'Q', 'e767f94c-e970-4843-b26c-34f849861542', '555555555', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113090884642', 'SQ2019091113003816449', '2019-09-11 13:09:08.00000', 'U12341111333', '1', 'Q', '225f0750-df6c-455a-a58e-3d561b2dc59a', '33333', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113100626313', 'SQ2019091113003816449', '2019-09-11 13:10:06.00000', 'U13800000000', '1', 'A', '225f0750-df6c-455a-a58e-3d561b2dc59a', '33333', '3333333');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113102155396', 'SQ2019091113102181940', '2019-09-11 13:10:21.00000', 'U13800000000', '1', 'Q', '225f0750-df6c-455a-a58e-3d561b2dc59a', '123', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113103028335', 'SQ2019091113102181940', '2019-09-11 13:10:30.00000', 'U13800000000', '1', 'A', '225f0750-df6c-455a-a58e-3d561b2dc59a', '123', '333333');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113103855194', 'SQ2019091113102181940', '2019-09-11 13:10:38.00000', 'U13800000000', '1', 'Q', 'fca8bd94-1914-4818-8db7-689579ecea35', '4444444', '');
INSERT INTO `workorder_orderdetail` VALUES ('SQ2019091113105192919', 'SQ2019091113102181940', '2019-09-11 13:10:51.00000', 'U13800000000', '1', 'A', 'fca8bd94-1914-4818-8db7-689579ecea35', '4444444', '444444444');

-- ----------------------------
-- View structure for v_系统表_流程记录表
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_流程记录表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_流程记录表` AS select `system_processrecord`.`SPR_Code` AS `流程编号`,`system_processrecord`.`SPR_CreateTime` AS `创建时间`,`system_processrecord`.`SPR_CreateUser` AS `创建人`,`system_processrecord`.`SPR_State` AS `状态`,`system_processrecord`.`SPR_Model` AS `流程模块`,`system_processrecord`.`SPR_ModelCode` AS `流程主键`,`system_processrecord`.`SPR_Action` AS `操作`,`system_processrecord`.`SPR_Result` AS `操作结果`,`system_processrecord`.`SPR_Description` AS `描述`,`system_processrecord`.`SPR_Batch` AS `流程批次`,`system_processrecord`.`SPR_ConfigNo` AS `流程配置编号` from `system_processrecord`;

-- ----------------------------
-- View structure for v_系统表_流程配置表
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_流程配置表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_流程配置表` AS select `system_processconfig`.`SPC_Code` AS `流程配置编号`,`system_processconfig`.`SPC_CreateTime` AS `创建时间`,`system_processconfig`.`SPC_CreateUser` AS `创建人`,`system_processconfig`.`SPC_State` AS `状态`,`system_processconfig`.`SPC_Model` AS `流程模块`,`system_processconfig`.`SPC_Version` AS `版本号`,`system_processconfig`.`SPC_Step` AS `步骤编号`,`system_processconfig`.`SPC_Role` AS `配置角色` from `system_processconfig`;

-- ----------------------------
-- View structure for v_系统表_通知消息
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_通知消息`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_通知消息` AS select `system_message`.`SM_Code` AS `通知编号`,`system_message`.`SM_CreateTime` AS `创建时间`,`system_message`.`SM_CreateUser` AS `创建人`,`system_message`.`SM_State` AS `状态`,`system_message`.`SM_Content` AS `通知内容`,`system_message`.`SM_BussinessCode` AS `关联业务主键`,`system_message`.`SM_Model` AS `关联模块`,`system_message`.`SM_Operate` AS `关联模块的操作`,`system_message`.`SM_URL` AS `详情地址`,`system_message`.`SM_Notified` AS `被通知人`,`system_message`.`SM_Readed` AS `已阅人` from `system_message`;

-- ----------------------------
-- View structure for v_系统表_通知配置表
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_通知配置表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_通知配置表` AS select `system_messageconfig`.`SMC_Code` AS `通知配置编号`,`system_messageconfig`.`SMC_CreateTime` AS `创建时间`,`system_messageconfig`.`SMC_CreateUser` AS `创建人`,`system_messageconfig`.`SMC_State` AS `状态`,`system_messageconfig`.`SMC_Content` AS `通知内容`,`system_messageconfig`.`SMC_Model` AS `关联模块`,`system_messageconfig`.`SMC_Operate` AS `关联模块的操作`,`system_messageconfig`.`SMC_URL` AS `详情地址`,`system_messageconfig`.`SMC_Notified` AS `被通知人`,`system_messageconfig`.`SMC_Version` AS `版本号` from `system_messageconfig`;

-- ----------------------------
-- View structure for v_系统表_附件信息
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_附件信息`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_附件信息` AS select `system_file`.`SF_Code` AS `附件编号`,`system_file`.`SF_CreateTime` AS `创建时间`,`system_file`.`SF_CreateUser` AS `创建人`,`system_file`.`SF_State` AS `状态`,`system_file`.`SF_BussinessCode` AS `业务表编号`,`system_file`.`SF_FileType` AS `附件类型`,`system_file`.`SF_FileName` AS `附件名称`,`system_file`.`SF_Path` AS `附件路径`,`system_file`.`SF_TempPath` AS `临时路径` from `system_file`;

-- ----------------------------
-- View structure for v_系统表_附件模板主表
-- ----------------------------
DROP VIEW IF EXISTS `v_系统表_附件模板主表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_系统表_附件模板主表` AS select `system_filetemplate`.`SFT_Code` AS `模板编号`,`system_filetemplate`.`SFT_CreateTime` AS `创建时间`,`system_filetemplate`.`SFT_CreateUser` AS `创建人`,`system_filetemplate`.`SFT_State` AS `状态`,`system_filetemplate`.`SFT_TemplateType` AS `模板类型`,`system_filetemplate`.`SFT_ModelCode` AS `模块编号` from `system_filetemplate`;

-- ----------------------------
-- View structure for v_资源申请_资源申请主表
-- ----------------------------
DROP VIEW IF EXISTS `v_资源申请_资源申请主表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_资源申请_资源申请主表` AS select `resource_apply`.`RA_Code` AS `申请编号`,`resource_apply`.`RA_CreateTime` AS `创建时间`,`resource_apply`.`RA_UpdateTime` AS `更新时间`,`resource_apply`.`RA_CreateUser` AS `创建人`,`resource_apply`.`RA_UpdateUser` AS `更新人`,`resource_apply`.`RA_State` AS `状态`,`resource_apply`.`RA_Batch` AS `流程批次`,`resource_apply`.`RA_ApplyType` AS `申请类型`,`resource_apply`.`RA_Mark` AS `备注` from `resource_apply`;

-- ----------------------------
-- View structure for v_问题工单_工单主表
-- ----------------------------
DROP VIEW IF EXISTS `v_问题工单_工单主表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_问题工单_工单主表` AS select `workorder_order`.`WO_Code` AS `工单编号`,`workorder_order`.`WO_CreateTime` AS `创建时间`,`workorder_order`.`WO_UpdateTime` AS `提交时间`,`workorder_order`.`WO_CreateUser` AS `创建人`,`workorder_order`.`WO_UpdateUser` AS `提交人`,`workorder_order`.`WO_State` AS `状态`,`workorder_order`.`WO_Class` AS `问题分类`,`workorder_order`.`WO_Description` AS `问题描述`,`workorder_order`.`WO_Telephone` AS `电话`,`workorder_order`.`WO_Mail` AS `邮箱`,`workorder_order`.`WO_Replier` AS `答复人`,`workorder_order`.`WO_Reply` AS `答复`,`workorder_order`.`WO_ReplyTime` AS `答复日期` from `workorder_order`;

-- ----------------------------
-- View structure for v_问题工单_工单明细表
-- ----------------------------
DROP VIEW IF EXISTS `v_问题工单_工单明细表`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_问题工单_工单明细表` AS select `workorder_orderdetail`.`WOD_Code` AS `工单明细编号`,`workorder_orderdetail`.`WOD_MainCode` AS `工单主表编号`,`workorder_orderdetail`.`WOD_CreateTime` AS `创建时间`,`workorder_orderdetail`.`WOD_CreateUser` AS `创建人`,`workorder_orderdetail`.`WOD_State` AS `状态`,`workorder_orderdetail`.`WOD_Type` AS `明细类型`,`workorder_orderdetail`.`WOD_Class` AS `问题分类`,`workorder_orderdetail`.`WOD_Description` AS `问题描述`,`workorder_orderdetail`.`WOD_Reply` AS `答复` from `workorder_orderdetail`;

-- ----------------------------
-- View structure for v_问题工单_问题分类
-- ----------------------------
DROP VIEW IF EXISTS `v_问题工单_问题分类`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_问题工单_问题分类` AS select `workorder_class`.`WC_Code` AS `分类编号`,`workorder_class`.`WC_CreateTime` AS `创建时间`,`workorder_class`.`WC_UpdateTime` AS `更新时间`,`workorder_class`.`WC_CreateUser` AS `创建人`,`workorder_class`.`WC_UpdateUser` AS `更新人`,`workorder_class`.`WC_State` AS `状态`,`workorder_class`.`WC_Name` AS `分类名称`,`workorder_class`.`WC_Description` AS `描述` from `workorder_class`;

-- ----------------------------
-- Procedure structure for addRoleUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `addRoleUser`;
delimiter ;;
CREATE PROCEDURE `addRoleUser`(IN roleId INT, in userIds_source varchar(50))
BEGIN
	DECLARE s int DEFAULT 0;
	DECLARE userId int;
	DECLARE userId_add varchar(11) default '';
	DECLARE i int;
	DECLARE num int;
	DECLARE userIds_data varchar(50) default '';
	DECLARE userIds_del varchar(50) default '';
	DECLARE userIds_add varchar(50) default '';
	DECLARE del_sql varchar(200) default '';
	DECLARE userRole CURSOR FOR SELECT role_user_user_id FROM admin_role_user WHERE role_user_role_id=roleId;
  	DECLARE CONTINUE HANDLER FOR NOT FOUND SET s=1;
  	OPEN userRole;
  		FETCH userRole INTO userId;
  		WHILE s <> 1 DO
  			SET userIds_data = CONCAT(userIds_data, CONCAT(userId, ','));
  			FETCH userRole INTO userId;
  		END WHILE;
  	CLOSE userRole;
  	SET userIds_data = CONCAT(',', userIds_data);
  	SET userIds_del = userIds_data;
  	SET num = (length(userIds_source) - length(replace(userIds_source,',','')));
  	SET i = 1;
  	WHILE i <= num DO
  		SET userIds_del = replace(userIds_del,CONCAT(',', reverse(substring_index(reverse(substring_index(userIds_source,',', i)),',',1)), ','), ',');
  		SET i = i + 1;
  	END WHILE;
  	SET num = (length(userIds_del) - length(replace(userIds_del,',','')));
  	SET userIds_del = substring_index(userIds_del,',', num);
  	SET userIds_del = reverse(substring_index(reverse(userIds_del), ',', num - 1));
  	SET userIds_add = userIds_source;
  	SET num = (length(userIds_data) - length(replace(userIds_data,',','')));
  	SET i = 1;
  	WHILE i <= num DO
  		SET userIds_add = replace(userIds_add,CONCAT(',', reverse(substring_index(reverse(substring_index(userIds_data,',', i)),',',1)), ','), ',');
  		SET i = i + 1;
  	END WHILE;
  	SET num = (length(userIds_add) - length(replace(userIds_add,',','')));
  	SET userIds_add = substring_index(userIds_add,',', num);
  	SET userIds_add = reverse(substring_index(reverse(userIds_add), ',', num - 1));
  	if userIds_del != '' then
  		SET @del_sql = CONCAT("delete from admin_role_user where role_user_user_id in (", userIds_del, ") and role_user_role_id=", roleId);
  		prepare mainStmt from @del_sql;
  		execute mainStmt;
  		deallocate prepare mainStmt;
  	end if;
  	if userIds_add != '' then
    	SET num = (length(userIds_add) - length(replace(userIds_add,',',''))) + 1;
  		SET i = 1;
  		WHILE i <= num DO
  			SET userId_add = reverse(substring_index(reverse(substring_index(userIds_add,',', i)),',',1));
  			insert into admin_role_user(role_user_role_id, role_user_user_id) values(roleId, userId_add);
  			SET i = i + 1;
  		END WHILE;
  	end if;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for addUserRole
-- ----------------------------
DROP PROCEDURE IF EXISTS `addUserRole`;
delimiter ;;
CREATE PROCEDURE `addUserRole`(IN userId INT, in roleIds_source varchar(50))
BEGIN
	DECLARE s int DEFAULT 0;
	DECLARE roleId int;
	DECLARE roleId_add varchar(11) default '';
	DECLARE i int;
	DECLARE num int;
	DECLARE roleIds_data varchar(50) default '';
	DECLARE roleIds_del varchar(50) default '';
	DECLARE roleIds_add varchar(50) default '';
	DECLARE del_sql varchar(200) default '';
	DECLARE roleUser CURSOR FOR SELECT role_user_role_id FROM admin_role_user WHERE role_user_user_id=userId;
  	DECLARE CONTINUE HANDLER FOR NOT FOUND SET s=1;
  	OPEN roleUser;
  		FETCH roleUser INTO roleId;
  		WHILE s <> 1 DO
  			SET roleIds_data = CONCAT(roleIds_data, CONCAT(roleId, ','));
  			FETCH roleUser INTO roleId;
  		END WHILE;
  	CLOSE roleUser;
  	SET roleIds_data = CONCAT(',', roleIds_data);
  	SET roleIds_del = roleIds_data;
  	SET num = (length(roleIds_source) - length(replace(roleIds_source,',','')));
  	SET i = 1;
  	WHILE i <= num DO
  		SET roleIds_del = replace(roleIds_del,CONCAT(',', reverse(substring_index(reverse(substring_index(roleIds_source,',', i)),',',1)), ','), ',');
  		SET i = i + 1;
  	END WHILE;
  	SET num = (length(roleIds_del) - length(replace(roleIds_del,',','')));
  	SET roleIds_del = substring_index(roleIds_del,',', num);
  	SET roleIds_del = reverse(substring_index(reverse(roleIds_del), ',', num - 1));
  	SET roleIds_add = roleIds_source;
  	SET num = (length(roleIds_data) - length(replace(roleIds_data,',','')));
  	SET i = 1;
  	WHILE i <= num DO
  		SET roleIds_add = replace(roleIds_add,CONCAT(',', reverse(substring_index(reverse(substring_index(roleIds_data,',', i)),',',1)), ','), ',');
  		SET i = i + 1;
  	END WHILE;
  	SET num = (length(roleIds_add) - length(replace(roleIds_add,',','')));
  	SET roleIds_add = substring_index(roleIds_add,',', num);
  	SET roleIds_add = reverse(substring_index(reverse(roleIds_add), ',', num - 1));
  	if roleIds_del != '' then
  	  SET @del_sql = CONCAT("delete from admin_role_user where role_user_role_id in (", roleIds_del, ") and role_user_user_id=", userId);
  		prepare mainStmt from @del_sql;
  		execute mainStmt;
  		deallocate prepare mainStmt;
  	end if;
  	if roleIds_add != '' then
    	SET num = (length(roleIds_add) - length(replace(roleIds_add,',',''))) + 1;
  		SET i = 1;
  		WHILE i <= num DO
  			SET roleId_add = reverse(substring_index(reverse(substring_index(roleIds_add,',', i)),',',1));
  			insert into admin_role_user(role_user_role_id, role_user_user_id) values(roleId_add, userId);
  			SET i = i + 1;
  		END WHILE;
  	end if;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
