use master
go

create database [asmjava4]
go

use asmjava4
go

create table users
(
id int primary key identity,
username varchar(10) unique not null,
passwords varchar(10) not null,
email varchar(50) unique not null,
isAdmin bit not null default 0,
isActive bit not null default 1
)
go

create table video
(
id int primary key identity,
title nvarchar(255) not null,
href varchar(50) unique not null,
poster varchar(255) null,
[views] int not null default 0,
shares int not null default 0,
[description] nvarchar(255) not null,
isActive bit not null default 1
)
create table history
(
id int primary key identity,
userId int foreign key references users(id),
videoId int foreign key references video(id),
viewedData datetime not null default getDate(),
isLiked bit not null default 0,
likedDate datetime null
)
go


insert into users(username,passwords,email,isAdmin) values
('anh01', '001', 'anhtkps38987@gmail.com', 1),
('anh02', '002', '2019.2020.91.kyanh@gmail.com', 0),
('anh03', '003', 'anh03@example.com', 0),
('anh04', '004', 'anh04@example.com', 0),
('anh05', '005', 'anh05@example.com', 0),
('anh06', '006', 'anh06@example.com', 0),
('anh07', '007', 'anh07@example.com', 0),
('anh08', '008', 'anh08@example.com', 0),
('anh09', '009', 'anh09@example.com', 0),
('anh10', '010', 'anh10@example.com', 1),
('anh11', '011', 'anh11@example.com', 1),
('anh12', '012', 'anh12@example.com', 1),
('anh13', '013', 'anh13@example.com', 1),
('anh14', '014', 'anh14@example.com', 1),
('anh15', '015', 'anh15@example.com', 1),
('anh16', '016', 'anh16@example.com', 1),
('anh17', '017', 'anh17@example.com', 1);
go
insert into video(title,href,[description]) values
(N'Chờ Em Đến Bao Giờ','TBqaISRSQUI',N'Phong Max x Freak D'),
(N'NHƯ GIÓ VỚI MÂY','ZkPa9xA0YCQ',N'DICKSON NGUYEN'),
(N'Suy Nghĩ Trong Anh','jeFqLn7i1B0',N'Thành Đạt Cover x Trạm Xưa'),
(N' ĐỪNG LÀM TRÁI TIM ANH ĐAU','abPmZCZZrFA',N'SƠN TÙNG M-TP'),
(N' Đánh Đổi','vPz8ftK_4bk',N'Obito'),
(N'One Call Away','BxuY9FET9Y4',N'Charlie Puth'),
(N'We Dont Talk Anymore ','3AtDnEC4zak',N'Charlie Puth'),
(N'Highs & Lows','Es7hLsV0QkU',N'Prinz'),
(N'Back One Day','2QdPxdcMhFQ',N'TheFatRat'),
(N'Our Song','jVSv77XkXA',N'TheFatRat')
go
insert into history(userId,videoId,isLiked,likedDate) values
(2,1,1,getdate()),
(2,2,0,null)
go