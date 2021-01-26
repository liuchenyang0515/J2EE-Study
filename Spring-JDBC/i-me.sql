
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eno` int(11) NOT NULL,
  `ename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` float(10, 2) NOT NULL,
  `dname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hiredate` date NULL DEFAULT NULL,
  PRIMARY KEY (`eno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (3308, '张三', 6000.00, '研发部', '2011-05-08');
INSERT INTO `employee` VALUES (3420, '李四', 8700.00, '研发部', '2006-11-11');
INSERT INTO `employee` VALUES (3610, '王五', 4550.00, '市场部', '2009-10-01');

SET FOREIGN_KEY_CHECKS = 1;
