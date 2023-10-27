USE [master]
GO
/****** Object:  Database [tuithemngot]    Script Date: 27/10/2023 08:47:09 ******/
CREATE DATABASE [tuithemngot]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'tuithemngot_Data', FILENAME = N'c:\dzsqls\tuithemngot.mdf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 22528KB )
 LOG ON 
( NAME = N'tuithemngot_Logs', FILENAME = N'c:\dzsqls\tuithemngot.ldf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 22528KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [tuithemngot] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [tuithemngot].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [tuithemngot] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [tuithemngot] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [tuithemngot] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [tuithemngot] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [tuithemngot] SET ARITHABORT OFF 
GO
ALTER DATABASE [tuithemngot] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [tuithemngot] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [tuithemngot] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [tuithemngot] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [tuithemngot] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [tuithemngot] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [tuithemngot] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [tuithemngot] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [tuithemngot] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [tuithemngot] SET  ENABLE_BROKER 
GO
ALTER DATABASE [tuithemngot] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [tuithemngot] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [tuithemngot] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [tuithemngot] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [tuithemngot] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [tuithemngot] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [tuithemngot] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [tuithemngot] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [tuithemngot] SET  MULTI_USER 
GO
ALTER DATABASE [tuithemngot] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [tuithemngot] SET DB_CHAINING OFF 
GO
ALTER DATABASE [tuithemngot] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [tuithemngot] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [tuithemngot] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [tuithemngot] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [tuithemngot] SET QUERY_STORE = OFF
GO
USE [tuithemngot]
GO
/****** Object:  User [tuithemngot]    Script Date: 27/10/2023 08:47:13 ******/
CREATE USER [tuithemngot] FOR LOGIN [tuithemngot] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [tuithemngot]
GO
/****** Object:  Schema [tuithemngot]    Script Date: 27/10/2023 08:47:13 ******/
CREATE SCHEMA [tuithemngot]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admin](
	[id] [int] NOT NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK_admin_id] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customers]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customers](
	[cus_id] [bigint] IDENTITY(1,1) NOT NULL,
	[cus_name] [nvarchar](100) NOT NULL,
	[cus_birthday] [date] NULL,
	[cus_address] [nvarchar](max) NOT NULL,
	[cus_phone] [varchar](10) NOT NULL,
	[cus_email] [varchar](50) NOT NULL,
	[cus_gender] [nvarchar](10) NOT NULL,
	[cus_username] [varchar](10) NOT NULL,
	[cus_password] [varchar](50) NOT NULL,
	[cus_create_at] [datetime] NOT NULL,
 CONSTRAINT [PK_cus_id] PRIMARY KEY CLUSTERED 
(
	[cus_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[feedback]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedback](
	[feedback_id] [bigint] NOT NULL,
	[feedback_rate] [nvarchar](50) NULL,
	[feedback_create_at] [datetime] NULL,
	[feedback_content] [nvarchar](max) NULL,
	[order_id] [bigint] NULL,
 CONSTRAINT [PK_feedback_id] PRIMARY KEY CLUSTERED 
(
	[feedback_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[import]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[import](
	[import_id] [bigint] IDENTITY(1,1) NOT NULL,
	[import_date] [datetime] NOT NULL,
	[import_total] [float] NOT NULL,
 CONSTRAINT [PK_import_id] PRIMARY KEY CLUSTERED 
(
	[import_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[import_detail]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[import_detail](
	[import_detail_id] [bigint] IDENTITY(1,1) NOT NULL,
	[import_id] [bigint] NOT NULL,
	[pro_id] [bigint] NOT NULL,
	[import_price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[import_detail_total] [float] NOT NULL,
 CONSTRAINT [PK_import_detail_id] PRIMARY KEY CLUSTERED 
(
	[import_detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[detail_id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_id] [bigint] NOT NULL,
	[pro_id] [bigint] NOT NULL,
	[import_price] [float] NOT NULL,
	[pro_price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[detail_total] [float] NOT NULL,
 CONSTRAINT [PK_detail_id] PRIMARY KEY CLUSTERED 
(
	[detail_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[order_id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_date] [datetime] NULL,
	[cus_id] [bigint] NOT NULL,
	[order_total] [float] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[order_receiver] [nvarchar](50) NOT NULL,
	[order_delivery_address] [nvarchar](max) NOT NULL,
	[order_phone_receiver] [varchar](10) NOT NULL,
	[order_note] [nvarchar](50) NULL,
 CONSTRAINT [PK_orders_id] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[pro_id] [bigint] IDENTITY(1,1) NOT NULL,
	[pro_name] [nvarchar](max) NOT NULL,
	[pro_image] [varchar](50) NOT NULL,
	[import_price] [float] NOT NULL,
	[pro_price] [float] NOT NULL,
	[pro_spec] [ntext] NULL,
	[type_id] [int] NOT NULL,
	[pro_status] [bit] NULL,
 CONSTRAINT [PK_pro_id] PRIMARY KEY CLUSTERED 
(
	[pro_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type_product]    Script Date: 27/10/2023 08:47:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type_product](
	[type_id] [int] IDENTITY(1,1) NOT NULL,
	[type_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_type_id] PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[customers] ON 

INSERT [dbo].[customers] ([cus_id], [cus_name], [cus_birthday], [cus_address], [cus_phone], [cus_email], [cus_gender], [cus_username], [cus_password], [cus_create_at]) VALUES (5, N'Lê Minh Đức', CAST(N'1998-01-20' AS Date), N'Thành phố Hồ Chí Minh', N'0907420625', N'leduc20198@gmail.com', N'Nam', N'minhduc', N'123', CAST(N'2023-09-27T08:38:35.213' AS DateTime))
INSERT [dbo].[customers] ([cus_id], [cus_name], [cus_birthday], [cus_address], [cus_phone], [cus_email], [cus_gender], [cus_username], [cus_password], [cus_create_at]) VALUES (6, N'Minh Tâm', CAST(N'2000-07-18' AS Date), N'Thành phố Hồ Chí Minh', N'0977466497', N'minhtam@gmail.com', N'Nữ', N'minhtam', N'123', CAST(N'2023-09-27T08:38:35.213' AS DateTime))
SET IDENTITY_INSERT [dbo].[customers] OFF
GO
SET IDENTITY_INSERT [dbo].[import] ON 

INSERT [dbo].[import] ([import_id], [import_date], [import_total]) VALUES (1, CAST(N'2023-10-19T11:35:27.463' AS DateTime), 360000)
SET IDENTITY_INSERT [dbo].[import] OFF
GO
SET IDENTITY_INSERT [dbo].[import_detail] ON 

INSERT [dbo].[import_detail] ([import_detail_id], [import_id], [pro_id], [import_price], [quantity], [import_detail_total]) VALUES (1, 1, 1, 55000, 2, 110000)
INSERT [dbo].[import_detail] ([import_detail_id], [import_id], [pro_id], [import_price], [quantity], [import_detail_total]) VALUES (2, 1, 5, 65000, 1, 65000)
INSERT [dbo].[import_detail] ([import_detail_id], [import_id], [pro_id], [import_price], [quantity], [import_detail_total]) VALUES (3, 1, 11, 100000, 1, 100000)
INSERT [dbo].[import_detail] ([import_detail_id], [import_id], [pro_id], [import_price], [quantity], [import_detail_total]) VALUES (4, 1, 8, 85000, 1, 85000)
SET IDENTITY_INSERT [dbo].[import_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([detail_id], [order_id], [pro_id], [import_price], [pro_price], [quantity], [detail_total]) VALUES (1, 1, 1, 55000, 75000, 1, 75000)
INSERT [dbo].[order_detail] ([detail_id], [order_id], [pro_id], [import_price], [pro_price], [quantity], [detail_total]) VALUES (2, 2, 11, 100000, 160000, 1, 160000)
INSERT [dbo].[order_detail] ([detail_id], [order_id], [pro_id], [import_price], [pro_price], [quantity], [detail_total]) VALUES (3, 2, 8, 85000, 110000, 1, 110000)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([order_id], [order_date], [cus_id], [order_total], [status], [order_receiver], [order_delivery_address], [order_phone_receiver], [order_note]) VALUES (1, CAST(N'2023-10-19T20:46:30.313' AS DateTime), 5, 75000, N'Đang giao', N'Lê Minh Đức', N'Tân Bình, TPHCM', N'0907420625', NULL)
INSERT [dbo].[orders] ([order_id], [order_date], [cus_id], [order_total], [status], [order_receiver], [order_delivery_address], [order_phone_receiver], [order_note]) VALUES (2, CAST(N'2023-10-19T20:53:59.907' AS DateTime), 6, 270000, N'Đang giao', N'Tâm', N'Phú Nhuận', N'0977466497', NULL)
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (1, N'Tiramisu', N'ti-ra-mi-su.jpg', 55000, 75000, N'Bánh Tiramisu một loại bánh ngọt tráng miệng vị cà phê của nước Ý gồm các lớp bánh quy', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (2, N'Mousse chanh dây', N'mousse-chanh-day-1.jpg', 55000, 75000, N'Mousse chanh dây có kết cấu chắc chắn, mềm mịn, béo ngậy và hơi chua chua đặc trưng của chanh dây', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (3, N'Mousse phúc bồn tử', N'mousse-phuc-bon-tu.jpg', 55000, 75000, N'Với sắc hồng xinh xắn và vị chua ngọt tinh tế, Mousse Phúc Bồn Tử  luôn nằm top những chiếc bánh được yêu thích nhất', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (4, N'Mousse phúc bồn tử', N'oreo-matcha-1.jpg', 55000, 75000, N'Bánh cheesecake matcha mịn mát, béo ngọt sẽ giúp giải tỏa cơn buồn miệng', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (5, N'Red velvet', N'red-velvet.jpg', 65000, 85000, N'Theo truyền thống, red velvet là loại bánh chocolate nhiều lớp có màu đỏ, nâu đỏ, đỏ thẫm hoặc đỏ tươi, chia lớp bởi lớp kem phủ bằng cream cheese hoặc ermine trắng', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (6, N'Double Choco Cheese', N'double-choco-1.jpg', 65000, 85000, N'Cheesecake đã cực kỳ được các tình yêu yêu thích nhưng nay được thêm lớp socola và nó sẽ nâng tầm thành một món ăn có sức hấp dẫn hơn nữa cho team thích bánh ngọt', 1, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (7, N'Brownies Socola', N'brownies-socola-1.jpg', 65000, 85000, N'Bánh brownie socola còn được gọi với cái tên là bánh hạnh nhân - là một loại bánh nướng socola hình vuông hoặc hình chữ nhật', 2, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (8, N'Brownies Mix', N'brownies-mix.jpg', 85000, 110000, N'Brownies có nhiều dạng khác nhau và có thể béo hoặc xốp, tùy thuộc vào độ đậm đặc của chúng và đây là loại mix giữa socola và cheese', 2, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (9, N'Brownies Cheese', N'brownies-cheese.jpg', 90000, 120000, N'Brownie Cheesecake là một món tráng miệng sang trọng kết hợp giữa kết cấu béo ngậy của một chiếc bánh phô mai cổ điển với hương vị mê đắm của lớp đế bánh chocolate giòn rụm', 2, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (10, N'Chè khúc bạch', N'che-khuc-bach-14.jpg', 25000, 50000, N'Chè khúc bạch là một loại chè thiên hướng đến sự ngọt ngào kích thích vị giác , mang đến sự béo ngậy từ phô mai cũng như mềm dẻo từ khúc bạch', 3, NULL)
INSERT [dbo].[products] ([pro_id], [pro_name], [pro_image], [import_price], [pro_price], [pro_spec], [type_id], [pro_status]) VALUES (11, N'Bông lan trứng muối', N'bong-lan-TM-4.jpg', 100000, 160000, N'Bánh bông lan trứng muối được làm từ bột với nhân là trứng muối và phô mai, mang sự mặn ngậy từ trứng muối, thơm béo từ phô mai.', 4, NULL)
SET IDENTITY_INSERT [dbo].[products] OFF
GO
SET IDENTITY_INSERT [dbo].[type_product] ON 

INSERT [dbo].[type_product] ([type_id], [type_name]) VALUES (1, N'Bánh lạnh')
INSERT [dbo].[type_product] ([type_id], [type_name]) VALUES (2, N'Brownies')
INSERT [dbo].[type_product] ([type_id], [type_name]) VALUES (3, N'Chè')
INSERT [dbo].[type_product] ([type_id], [type_name]) VALUES (4, N'Bông lan')
SET IDENTITY_INSERT [dbo].[type_product] OFF
GO
ALTER TABLE [dbo].[customers] ADD  DEFAULT (getdate()) FOR [cus_create_at]
GO
ALTER TABLE [dbo].[feedback] ADD  DEFAULT (getdate()) FOR [feedback_create_at]
GO
ALTER TABLE [dbo].[import] ADD  DEFAULT (getdate()) FOR [import_date]
GO
ALTER TABLE [dbo].[orders] ADD  DEFAULT (getdate()) FOR [order_date]
GO
ALTER TABLE [dbo].[feedback]  WITH CHECK ADD  CONSTRAINT [FK_order_id_feedback] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[feedback] CHECK CONSTRAINT [FK_order_id_feedback]
GO
ALTER TABLE [dbo].[import_detail]  WITH CHECK ADD  CONSTRAINT [FK_import_id] FOREIGN KEY([import_id])
REFERENCES [dbo].[import] ([import_id])
GO
ALTER TABLE [dbo].[import_detail] CHECK CONSTRAINT [FK_import_id]
GO
ALTER TABLE [dbo].[import_detail]  WITH CHECK ADD  CONSTRAINT [FK_pro_id_import] FOREIGN KEY([pro_id])
REFERENCES [dbo].[products] ([pro_id])
GO
ALTER TABLE [dbo].[import_detail] CHECK CONSTRAINT [FK_pro_id_import]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_order_id] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([order_id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_order_id]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_pro_id] FOREIGN KEY([pro_id])
REFERENCES [dbo].[products] ([pro_id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_pro_id]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_cus_id] FOREIGN KEY([cus_id])
REFERENCES [dbo].[customers] ([cus_id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_cus_id]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_type_id] FOREIGN KEY([type_id])
REFERENCES [dbo].[type_product] ([type_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_type_id]
GO
/****** Object:  StoredProcedure [dbo].[sp_insert_type]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_insert_type] @name nvarchar(50)
as
insert into type_product ([type_name]) values (@name)
GO
/****** Object:  StoredProcedure [dbo].[sp_show_cus_by_name]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_cus_by_name] (@keyword nvarchar)
as
select ROW_NUMBER() over (order by cus_id) stt, * from customers where cus_name like N'%' +@keyword+'%'
GO
/****** Object:  StoredProcedure [dbo].[sp_show_customer]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_show_customer]
as 
select row_number() over(order by cus_id) stt, * from customers
GO
/****** Object:  StoredProcedure [dbo].[sp_show_import]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_show_import]
as
select row_number() over (order by import.import_id) as 'stt', import.import_id, import_detail.import_detail_id, products.pro_id, products.pro_name, import_detail.import_price, import_detail.quantity, import_detail.import_detail_total, import.import_total, convert(varchar, import.import_date, 105) 'import_date'
                    from import inner join import_detail on import.import_id = import_detail.import_id inner join products on import_detail.pro_id = products.pro_id
GO
/****** Object:  StoredProcedure [dbo].[sp_show_import_detail_by_id]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_show_import_detail_by_id] @id int
as
select row_number() over (order by d.import_detail_id) as 'stt', *
                    from import_detail d inner join products p on d.pro_id = p.pro_id
                    where d.import_id = @id
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create  proc [dbo].[sp_show_order]
as
select row_number() over (order by order_id) as 'stt', * from orders
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order_by_date]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_order_by_date] (@from datetime, @to datetime)
as
select row_number() over (order by order_id) as 'stt', * 
from orders o inner join customers c on o.cus_id = c.cus_id 
where cast(order_date as date) between @from  and @to
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order_by_id]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_order_by_id] (@id bigint)
as
select ROW_NUMBER() over (order by orders.order_id) as 'stt', orders.order_id, products.pro_name, products.import_price, products.pro_price, order_detail.quantity, order_detail.detail_total, orders.order_total
from orders inner join order_detail on orders.order_id = order_detail.order_id inner join products on order_detail.pro_id = products.pro_id
					where orders.order_id = @id
GO
/****** Object:  StoredProcedure [dbo].[sp_show_orders]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_orders]
as
select row_number() over (order by orders.order_id) as 'stt', orders.order_id, customers.cus_name, products.pro_name, order_detail.quantity, products.import_price, products.pro_price, order_detail.detail_total, orders.order_total, orders.order_receiver, orders.order_delivery_address, orders.order_phone_receiver, convert(varchar, orders.order_date, 100) as 'order_date', orders.[status]
from orders inner join customers on orders.cus_id = customers.cus_id inner join order_detail on orders.order_id = order_detail.order_id inner join products on order_detail.pro_id = products.pro_id 
GO
/****** Object:  StoredProcedure [dbo].[sp_show_type]    Script Date: 27/10/2023 08:47:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_show_type] 
as
select row_number() over(order by type_id) as 'stt', * from type_product
GO
USE [master]
GO
ALTER DATABASE [tuithemngot] SET  READ_WRITE 
GO
