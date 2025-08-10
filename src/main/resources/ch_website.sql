/*
 Navicat Premium Data Transfer

 Source Server         : localMySQL
 Source Server Type    : MySQL
 Source Server Version : 50147 (5.1.47-community)
 Source Host           : localhost:3306
 Source Schema         : ch_website

 Target Server Type    : MySQL
 Target Server Version : 50147 (5.1.47-community)
 File Encoding         : 65001

 Date: 23/03/2025 13:07:32
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friend_ships
-- ----------------------------
DROP TABLE IF EXISTS `friend_ships`;
CREATE TABLE `friend_ships`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `user_id` int(11) NOT NULL,
                                 `friend_id` int(11) NOT NULL,
                                 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE INDEX `unique_friendship`(`user_id`, `friend_id`) USING BTREE,
                                 INDEX `fk_friend_id`(`friend_id`) USING BTREE,
                                 CONSTRAINT `fk_friend_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_ships
-- ----------------------------
INSERT INTO `friend_ships` VALUES (1, 1, 2, '2025-03-07 22:36:28');
INSERT INTO `friend_ships` VALUES (2, 2, 1, '2025-03-07 22:36:37');
INSERT INTO `friend_ships` VALUES (3, 1, 3, '2025-03-07 22:36:54');
INSERT INTO `friend_ships` VALUES (4, 3, 1, '2025-03-07 22:37:00');

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `nation_code` int(5) NOT NULL,
                           `nation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                           `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nation
-- ----------------------------
INSERT INTO `nation` VALUES (1, 86, '中国', '2025-03-23 13:06:01', '2025-03-23 13:03:44');
INSERT INTO `nation` VALUES (2, 49, '德国', '2025-03-23 13:06:05', '2025-03-23 13:03:49');
INSERT INTO `nation` VALUES (3, 33, '法国', '2025-03-23 13:06:07', '2025-03-23 13:03:53');
INSERT INTO `nation` VALUES (4, 7, '俄罗斯', '2025-03-23 13:06:10', '2025-03-23 13:03:58');
INSERT INTO `nation` VALUES (5, 1, '美国', '2025-03-23 13:06:13', '2025-03-23 13:04:02');

-- ----------------------------
-- Table structure for navigation_menu
-- ----------------------------
DROP TABLE IF EXISTS `navigation_menu`;
CREATE TABLE `navigation_menu`  (
                                    `id` int(3) NOT NULL AUTO_INCREMENT,
                                    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of navigation_menu
-- ----------------------------
INSERT INTO `navigation_menu` VALUES (1, '首页');
INSERT INTO `navigation_menu` VALUES (2, '系统');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                               `role_Id` int(3) NOT NULL,
                               `router_Id` int(3) NOT NULL,
                               `creation_time` datetime NULL DEFAULT NULL,
                               `update_time` datetime NULL DEFAULT NULL,
                               INDEX `fk_role_id`(`role_Id`) USING BTREE,
                               INDEX `fk_router_id`(`router_Id`) USING BTREE,
                               CONSTRAINT `fk_role_id` FOREIGN KEY (`role_Id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                               CONSTRAINT `fk_router_id` FOREIGN KEY (`router_Id`) REFERENCES `router` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 1, '2023-02-24 09:53:30', '2023-02-24 09:53:32');
INSERT INTO `permission` VALUES (1, 2, '2023-02-24 09:53:38', '2023-02-24 09:53:42');
INSERT INTO `permission` VALUES (1, 3, '2023-02-24 09:53:50', '2023-02-24 09:53:52');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '2025-03-23 13:03:30', '0000-00-00 00:00:00');
INSERT INTO `role` VALUES (2, 'normal', '2025-03-23 13:03:31', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for router
-- ----------------------------
DROP TABLE IF EXISTS `router`;
CREATE TABLE `router`  (
                           `id` int(2) NOT NULL AUTO_INCREMENT,
                           `parent_id` int(2) NULL DEFAULT NULL,
                           `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                           `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                           `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                           `nav_menu_id` int(2) NULL DEFAULT NULL,
                           `sort` int(2) NULL DEFAULT NULL,
                           `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                           `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
                           `cache` int(1) NULL DEFAULT NULL,
                           `menu` int(1) NULL DEFAULT NULL,
                           `hidden` int(1) NULL DEFAULT NULL,
                           `creation_time` datetime NULL DEFAULT NULL,
                           `update_time` datetime NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of router
-- ----------------------------
INSERT INTO `router` VALUES (1, 0, 'Home', '/home', 'home/home', 1, 1, '首页', 'index-icon', 0, 1, 0, '2023-02-24 15:11:51', '2023-02-24 15:22:42');
INSERT INTO `router` VALUES (2, 0, '', '/system', 'Layout', 2, 2, '系统管理', 'setting-icon', 0, 0, 0, '2023-02-24 15:11:55', '2023-02-24 15:12:14');
INSERT INTO `router` VALUES (3, 2, 'Menu', 'menu', 'system/menu/menu', 2, 2, '菜单管理', 'building-icon', 0, 1, 0, '2023-02-24 15:12:00', '2023-02-24 15:14:27');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `age` int(11) NOT NULL,
                         `gender` int(11) NOT NULL,
                         `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `role_id` int(11) NULL DEFAULT NULL,
                         `nation_id` int(11) NULL DEFAULT NULL,
                         `phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
                         `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK_fb2e442d14add3cefbdf33c4561`(`role_id`) USING BTREE,
                         INDEX `FK_f0bb76042ad22ee6710b8adddee`(`nation_id`) USING BTREE,
                         CONSTRAINT `FK_f0bb76042ad22ee6710b8adddee` FOREIGN KEY (`nation_id`) REFERENCES `nation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                         CONSTRAINT `FK_fb2e442d14add3cefbdf33c4561` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '6j68rb4i8jasoem3pkekvh3hectn8p1p', '卡罗尔', '/simple_oa_assets/1.jpg', 24, 1, '南京市江宁区上元大街171号', 1, 1, '13270261160', '18626042516@163.com', '2025-03-23 13:02:51', '2025-03-23 13:01:42');
INSERT INTO `user` VALUES (2, 'xiaolv', '6j68rb4i8jasoem3pkekvh3hectn8p1p', '小绿', '/simple_oa_assets/2.jpg', 23, 0, '上海', 2, 1, '13289898989', '18626042516@163.com', '2025-03-23 13:02:52', '2025-03-23 13:01:47');
INSERT INTO `user` VALUES (3, 'xiaolan', '6j68rb4i8jasoem3pkekvh3hectn8p1p', '小蓝', '/simple_oa_assets/3.jpg', 22, 0, '上海', 2, 1, '13289898989', '18626042516@163.com', '2025-03-23 13:02:53', '2025-03-23 13:01:51');
INSERT INTO `user` VALUES (4, 'xiaohong', '6j68rb4i8jasoem3pkekvh3hectn8p1p', '小红', '/simple_oa_assets/4.png', 20, 0, '上海', 2, 1, '13289898981', '18626042516@163.com', '2025-03-23 13:02:55', '2025-03-23 13:01:55');

SET FOREIGN_KEY_CHECKS = 1;
