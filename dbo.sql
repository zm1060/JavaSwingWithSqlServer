/*
Navicat SQL Server Data Transfer

Source Server         : SQLSERVER
Source Server Version : 150000
Source Host           : localhost:1433
Source Database       : washstore
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 150000
File Encoding         : 65001

Date: 2021-12-29 16:57:47
*/


-- ----------------------------
-- Table structure for s_member
-- ----------------------------
DROP TABLE [dbo].[s_member]
GO
CREATE TABLE [dbo].[s_member] (
[memberid] varchar(255) NOT NULL ,
[membername] varchar(255) NULL ,
[membermoney] decimal(18) NULL 
)


GO

-- ----------------------------
-- Records of s_member
-- ----------------------------
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'1', N'张123', N'211')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'12', N'12', N'123')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'2', N'2', N'123')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'23', N'2', N'12')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'3', N'123', N'123')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'333', N'1123', N'123')
GO
GO
INSERT INTO [dbo].[s_member] ([memberid], [membername], [membermoney]) VALUES (N'4', N'111', N'11')
GO
GO

-- ----------------------------
-- Table structure for s_records
-- ----------------------------
DROP TABLE [dbo].[s_records]
GO
CREATE TABLE [dbo].[s_records] (
[recordid] varchar(255) NOT NULL ,
[clothingtype] varchar(255) NULL ,
[memberid] varchar(255) NULL ,
[washtype] varchar(255) NULL ,
[shouldmoney] varchar(255) NULL 
)


GO

-- ----------------------------
-- Records of s_records
-- ----------------------------
INSERT INTO [dbo].[s_records] ([recordid], [clothingtype], [memberid], [washtype], [shouldmoney]) VALUES (N'1', N'衬衫', N'1', N'洗鞋', N'20')
GO
GO
INSERT INTO [dbo].[s_records] ([recordid], [clothingtype], [memberid], [washtype], [shouldmoney]) VALUES (N'2', N'衬衫', N'1', N'洗鞋', N'20')
GO
GO
INSERT INTO [dbo].[s_records] ([recordid], [clothingtype], [memberid], [washtype], [shouldmoney]) VALUES (N'2323', N'棉袄', N'1', N'干洗', N'50')
GO
GO
INSERT INTO [dbo].[s_records] ([recordid], [clothingtype], [memberid], [washtype], [shouldmoney]) VALUES (N'4', N'毛衣', N'3', N'干洗', N'50')
GO
GO

-- ----------------------------
-- Table structure for s_state
-- ----------------------------
DROP TABLE [dbo].[s_state]
GO
CREATE TABLE [dbo].[s_state] (
[recordid] varchar(255) NOT NULL ,
[status] varchar(10) NULL DEFAULT ((0)) 
)


GO

-- ----------------------------
-- Records of s_state
-- ----------------------------
INSERT INTO [dbo].[s_state] ([recordid], [status]) VALUES (N'1', N'1')
GO
GO
INSERT INTO [dbo].[s_state] ([recordid], [status]) VALUES (N'2', N'0')
GO
GO
INSERT INTO [dbo].[s_state] ([recordid], [status]) VALUES (N'3', N'0')
GO
GO

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE [dbo].[s_user]
GO
CREATE TABLE [dbo].[s_user] (
[userid] varchar(255) NOT NULL ,
[username] varchar(255) NULL ,
[password] varchar(255) NULL ,
[level ] varchar(4) NULL 
)


GO

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO [dbo].[s_user] ([userid], [username], [password], [level ]) VALUES (N'1', N'admin', N'admin', N'1')
GO
GO
INSERT INTO [dbo].[s_user] ([userid], [username], [password], [level ]) VALUES (N'2', N'user', N'user', N'2')
GO
GO
INSERT INTO [dbo].[s_user] ([userid], [username], [password], [level ]) VALUES (N'3', N'3', N'3', N'3')
GO
GO
INSERT INTO [dbo].[s_user] ([userid], [username], [password], [level ]) VALUES (N'5', N'123', N'123', N'1')
GO
GO
INSERT INTO [dbo].[s_user] ([userid], [username], [password], [level ]) VALUES (N'55', N'55', N'55', N'55')
GO
GO

-- ----------------------------
-- Table structure for s_wash
-- ----------------------------
DROP TABLE [dbo].[s_wash]
GO
CREATE TABLE [dbo].[s_wash] (
[type] varchar(255) NULL ,
[money] decimal(18) NULL 
)


GO

-- ----------------------------
-- Records of s_wash
-- ----------------------------
INSERT INTO [dbo].[s_wash] ([type], [money]) VALUES (N'干洗', N'50')
GO
GO
INSERT INTO [dbo].[s_wash] ([type], [money]) VALUES (N'水洗', N'6')
GO
GO
INSERT INTO [dbo].[s_wash] ([type], [money]) VALUES (N'速洗', N'5')
GO
GO
INSERT INTO [dbo].[s_wash] ([type], [money]) VALUES (N'洗鞋', N'20')
GO
GO

-- ----------------------------
-- Indexes structure for table s_member
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table s_member
-- ----------------------------
ALTER TABLE [dbo].[s_member] ADD PRIMARY KEY ([memberid])
GO

-- ----------------------------
-- Indexes structure for table s_records
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table s_records
-- ----------------------------
ALTER TABLE [dbo].[s_records] ADD PRIMARY KEY ([recordid])
GO

-- ----------------------------
-- Indexes structure for table s_state
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table s_state
-- ----------------------------
ALTER TABLE [dbo].[s_state] ADD PRIMARY KEY ([recordid])
GO

-- ----------------------------
-- Indexes structure for table s_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table s_user
-- ----------------------------
ALTER TABLE [dbo].[s_user] ADD PRIMARY KEY ([userid])
GO
