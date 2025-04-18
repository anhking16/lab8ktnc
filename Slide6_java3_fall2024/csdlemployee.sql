USE [master]
GO
/****** Object:  Database [lab6_java3]    Script Date: 10/2/2024 7:07:53 PM ******/
CREATE DATABASE [lab6_java3]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'lab6_java3', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\lab6_java3.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'lab6_java3_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\lab6_java3_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [lab6_java3] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [lab6_java3].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [lab6_java3] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [lab6_java3] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [lab6_java3] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [lab6_java3] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [lab6_java3] SET ARITHABORT OFF 
GO
ALTER DATABASE [lab6_java3] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [lab6_java3] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [lab6_java3] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [lab6_java3] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [lab6_java3] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [lab6_java3] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [lab6_java3] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [lab6_java3] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [lab6_java3] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [lab6_java3] SET  ENABLE_BROKER 
GO
ALTER DATABASE [lab6_java3] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [lab6_java3] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [lab6_java3] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [lab6_java3] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [lab6_java3] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [lab6_java3] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [lab6_java3] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [lab6_java3] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [lab6_java3] SET  MULTI_USER 
GO
ALTER DATABASE [lab6_java3] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [lab6_java3] SET DB_CHAINING OFF 
GO
ALTER DATABASE [lab6_java3] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [lab6_java3] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [lab6_java3] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [lab6_java3] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [lab6_java3] SET QUERY_STORE = ON
GO
ALTER DATABASE [lab6_java3] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [lab6_java3]
GO
/****** Object:  Table [dbo].[Departments]    Script Date: 10/2/2024 7:07:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Departments](
	[id] [char](3) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Description] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employees]    Script Date: 10/2/2024 7:07:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employees](
	[Id] [varchar](20) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Photo] [nvarchar](50) NOT NULL,
	[Gender] [bit] NOT NULL,
	[Birthday] [date] NOT NULL,
	[Salary] [float] NOT NULL,
	[DepartmentId] [char](3) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'001', N'KINH DOANH', N'PHÒNG KINH DOANH')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'002', N'KẾ TOÁN', N'PHÒNG KẾ TOÁN')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'003', N'GIÁM ĐÔC', N'PHÒNG GIÁM ĐÔC')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'D01', N'HR', N'Human Resources Department')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'D02', N'IT', N'Information Technology Department')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'D03', N'FIN', N'Finance Department')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'D04', N'MKT', N'Marketing Department')
INSERT [dbo].[Departments] ([id], [Name], [Description]) VALUES (N'D05', N'SALES', N'Sales Department')
GO
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E001', N'pass123', N'John Doe', N'john.jpg', 1, CAST(N'1990-05-15' AS Date), 50000, N'D01')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E002', N'pass234', N'Jane Smith', N'jane.jpg', 0, CAST(N'1988-07-23' AS Date), 60000, N'D02')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E003', N'pass345', N'Michael Johnson', N'michael.jpg', 1, CAST(N'1985-02-12' AS Date), 55000, N'D03')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E004', N'pass456', N'Emily Davis', N'emily.jpg', 0, CAST(N'1992-09-01' AS Date), 48000, N'D04')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E005', N'pass567', N'Robert Brown', N'robert.jpg', 1, CAST(N'1994-11-20' AS Date), 53000, N'D05')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E006', N'pass678', N'Lisa White', N'lisa.jpg', 0, CAST(N'1987-08-19' AS Date), 58000, N'D01')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E007', N'pass789', N'James Wilson', N'james.jpg', 1, CAST(N'1990-12-30' AS Date), 61000, N'D02')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E008', N'pass890', N'Sarah Taylor', N'sarah.jpg', 0, CAST(N'1995-04-15' AS Date), 52000, N'D03')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E009', N'pass901', N'David Lee', N'david.jpg', 1, CAST(N'1991-06-22' AS Date), 49000, N'D04')
INSERT [dbo].[Employees] ([Id], [Password], [Fullname], [Photo], [Gender], [Birthday], [Salary], [DepartmentId]) VALUES (N'E010', N'pass012', N'Sophia Moore', N'sophia.jpg', 0, CAST(N'1993-10-11' AS Date), 62000, N'D05')
GO
ALTER TABLE [dbo].[Employees]  WITH CHECK ADD FOREIGN KEY([DepartmentId])
REFERENCES [dbo].[Departments] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
USE [master]
GO
ALTER DATABASE [lab6_java3] SET  READ_WRITE 
GO
