
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for account_business
-- ----------------------------
DROP TABLE IF EXISTS account_business;
CREATE TABLE account_business (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_code varchar(20) NOT NULL COMMENT '用户编码',
  account_code varchar(32) NOT NULL COMMENT '账户编码',
  account_name varchar(32) NOT NULL COMMENT '账户名称',
  money_count int(11) NOT NULL DEFAULT '0' COMMENT '动账金额 (单位 分)',
  type tinyint(4) NOT NULL DEFAULT '0' COMMENT '动账类型（0-收入 1-支出）',
  business_code varchar(64) NOT NULL COMMENT '交易码',
  remark varchar(64) DEFAULT NULL COMMENT '备注',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS activity;
CREATE TABLE activity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(32) NOT NULL COMMENT '活动编码',
  name varchar(32) NOT NULL COMMENT '活动名称',
  title varchar(32) NOT NULL COMMENT '活动标题',
  start_time bigint(20) NOT NULL COMMENT '开始时间',
  end_time bigint(20) NOT NULL COMMENT '结束时间',
  status tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-活动未开始 1-活动已上架 2-活动已下架）',
  send_result_msg tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否发送结果的短信通知（0-发送短息 1-不发送短信）',
  type tinyint(4) NOT NULL COMMENT '类型（0-报名活动，1-投票活动，2-问卷答题，3-抽奖活动，4-抢购活动）',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of activity
-- ----------------------------
BEGIN;
INSERT INTO activity VALUES ('1', 'v001', '科创杯报名比赛', '科创杯报名比赛', '1527717772000', '1546272000000', '0', '0', '0', '1527717772000', 'system', 'system', null, null, null, '0'), ('2', 'v002', '科创杯报名比赛', '科创杯报名比赛', '1527717772000', '1546272000000', '0', '0', '0', '1527950814531', 'system', 'system', null, null, null, '0'), ('5', 'v003', '科创杯报名比赛', '科创杯报名比赛', '1527717772000', '1546272000000', '0', '0', '0', '1527951891966', 'system', 'system', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for activity_enroll_field
-- ----------------------------
DROP TABLE IF EXISTS activity_enroll_field;
CREATE TABLE activity_enroll_field (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_code varchar(32) NOT NULL COMMENT '活动编码',
  label_name varchar(32) NOT NULL COMMENT '标签名称',
  filed_type tinyint(4) NOT NULL COMMENT '输入项类型(1-text，2-mobile，3-number，4-select，5-date，6-time，7-datetime)',
  dictionary_group_code varchar(32) DEFAULT NULL COMMENT '针对select的候选项分组',
  index int(11) NOT NULL COMMENT '显示顺序（从0开始越小越靠上）',
  is_secrecy tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否保密(0-不保密，1-保密)',
  is_key tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否作为唯一标识(0-不是，1-是)',
  is_required tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否必输项(0-不是，1-是)',
  min int(11) DEFAULT NULL COMMENT '最小值/长度',
  max int(11) DEFAULT NULL COMMENT '最大值/长度',
  pattern varchar(128) DEFAULT NULL COMMENT '正则表达式限制',
  error_tip varchar(64) DEFAULT NULL COMMENT '错误提示信息',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of activity_enroll_field
-- ----------------------------
BEGIN;
INSERT INTO activity_enroll_field VALUES ('1', 'v001', '用户名', '1', null, '1', '0', '0', '1', '2', '10', null, '用户名不能为空 2到10个字符之间', '1527717772000', 'system', 'system', null, null, null, '0'), ('2', 'v001', '联系电话', '2', null, '2', '1', '1', '1', '11', '11', null, '电话号码不合法', '1527717772000', 'system', 'system', null, null, null, '0'), ('3', 'v002', '用户名', '1', null, '1', '0', '0', '1', '2', '10', null, '用户名不能为空 2到10个字符之间', '1527950814586', 'system', 'system', null, null, null, '0'), ('4', 'v002', '联系电话', '2', null, '2', '1', '1', '1', '11', '11', null, '电话号码不合法', '1527950814596', 'system', 'system', null, null, null, '0'), ('5', 'v002', '用户地址', '2', null, '2', '1', '1', '1', '1', '50', null, '请输入用户地址 50个字符以内', '1527950814603', 'system', 'system', null, null, null, '0'), ('6', 'v003', '用户名', '1', null, '1', '0', '0', '1', '2', '10', null, '用户名不能为空 2到10个字符之间', '1527951892013', 'system', 'system', null, null, null, '0'), ('7', 'v003', '联系电话', '2', null, '2', '1', '1', '1', '11', '11', null, '电话号码不合法', '1527951892013', 'system', 'system', null, null, null, '0'), ('8', 'v003', '用户地址', '2', null, '2', '1', '1', '1', '1', '50', null, '请输入用户地址 50个字符以内', '1527951892013', 'system', 'system', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for activity_enroll_participate
-- ----------------------------
DROP TABLE IF EXISTS activity_enroll_participate;
CREATE TABLE activity_enroll_participate (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_code varchar(32) NOT NULL COMMENT '活动编码',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for activity_enroll_participate_field
-- ----------------------------
DROP TABLE IF EXISTS activity_enroll_participate_field;
CREATE TABLE activity_enroll_participate_field (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_participate_id bigint(20) NOT NULL COMMENT '参与的人',
  activity_field_id bigint(20) NOT NULL COMMENT '输入项ID',
  dictionary_code varchar(32) DEFAULT NULL COMMENT '字典编码',
  activity_field_value text NOT NULL COMMENT '输入的值',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS dictionary;
CREATE TABLE dictionary (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  group_code varchar(32) NOT NULL COMMENT '字典分组编码',
  group_name varchar(128) NOT NULL COMMENT '分组名',
  code varchar(32) NOT NULL COMMENT '字典项编码',
  value varchar(128) NOT NULL COMMENT '字典值',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of dictionary
-- ----------------------------
BEGIN;
INSERT INTO dictionary VALUES ('1', 'gender', '性别', 'gender_man', '男', '1', 'system', 'system', null, null, null, '0'), ('2', 'gender', '性别', 'gender_women', '女', '1', 'system', 'system', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS log;
CREATE TABLE log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  source_type varchar(20) NOT NULL COMMENT '日志类别(1-用户，2-活动)',
  source_key varchar(32) NOT NULL COMMENT '日志主体的Key',
  opt_type tinyint(8) NOT NULL COMMENT '操作类别（0-新增，1-修改，3-删除，4-上架，5下架）',
  content varchar(128) NOT NULL COMMENT '日志内容',
  remark varchar(128) DEFAULT NULL COMMENT '备注',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS test;
CREATE TABLE test (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(20) DEFAULT NULL COMMENT '姓名',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_code varchar(20) NOT NULL COMMENT '登录账号',
  password varchar(128) NOT NULL COMMENT '登录密码',
  user_name varchar(32) NOT NULL COMMENT '用户名成',
  mobile varchar(32) NOT NULL COMMENT '用户手机号',
  type tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户类型(0-超级管理员 1-普通管理员)',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of user
-- ----------------------------
BEGIN;
INSERT INTO user VALUES ('1', 'sadmin', 'aff6eaadab43c00e0d45923015cc982e', '超级管理员', '15210618066', '0', '1528293637298', 'system', 'system', null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS user_account;
CREATE TABLE user_account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_code varchar(20) NOT NULL COMMENT '用户编码',
  account_code varchar(32) NOT NULL COMMENT '账户编码',
  account_name varchar(32) NOT NULL COMMENT '账户名称',
  status tinyint(4) NOT NULL DEFAULT '0' COMMENT '账户状态(0-正常 1-暂停 3-废弃)',
  money_left int(11) NOT NULL DEFAULT '0' COMMENT '账户余额(单位 分)',
  create_at bigint(20) NOT NULL,
  create_by varchar(32) NOT NULL,
  create_name varchar(20) NOT NULL,
  update_at bigint(20) DEFAULT NULL,
  update_by varchar(32) DEFAULT NULL,
  update_name varchar(20) DEFAULT NULL,
  is_del tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
