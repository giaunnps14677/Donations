create database project
go
--use master
--drop database project
--drop table ACCOUNT
--drop table DONATIONS
--drop table INFORMATION_CONTRIBUTORS
--select * from ACCOUNT
--select * from INFORMATION_CONTRIBUTORS
--select * from DONATIONS
--select * from DONATIONS
--select * from PASSWORD_RESET_TOKEN

go
use project
go
create table ACCOUNT(
ID_ACCOUNT int identity primary key,
EMAIL varchar(50),
USERNAME varchar(20),
PASSWORD varchar(200),
ROLE int
)
 --update Account set role =1 where id_account =13
 go
ALTER TABLE account 
ALTER COLUMN password varchar(200)

go
create table PASSWORD_RESET_TOKEN(
ID int identity primary key,
TOKEN varchar(500),
ACCOUNT_ID int,
EXPIRY_DATE date
)
go

create table DONATIONS(
ID_DONATION int identity primary key,
IMAGES varchar(200),
TITLE nvarchar(100),
MONEY_DONATIONS float,
NUMBER_DONATIONS int
)
--update DONATIONS set IMAGES = 'backgroundFlower.png' where ID_DONATION = 21
--delete from  DONATIONS where ID_DONATION in (19, 20)
go
create table INFORMATION_CONTRIBUTORS(
ID_ACCOUNT int,
ID_DONATION int,
ID_INFORMATION_CONTRIBUTORS int identity primary key,
NAME nvarchar(20),
MONEY_DONATION float,
DATE_DONATION date,
CONSTRAINT fk_informationcontrinutor_account FOREIGN KEY (ID_ACCOUNT) REFERENCES account(ID_ACCOUNT),
CONSTRAINT fk_informationcontributor_donations FOREIGN KEY (ID_DONATION) REFERENCES DONATIONS(ID_DONATION)
)
go
alter table INFORMATION_CONTRIBUTORS
add DA_QG BIT

go
alter table INFORMATION_CONTRIBUTORS
add XAC_NHAN_DA_QG BIT

go
alter table INFORMATION_CONTRIBUTORS
add HINH_THUC_QG varchar(200)

go
insert into account values
('admin@gmail.com', 'admin', '123', 1),
('user@gmail.com', 'user', '12', 0),
('nguyenngocgiau@gmail.com', 'ngocgiau', '12', 0),
('tranthanhcong@gmail.com', 'thanhcong', '12', 0),
('nguyenmayman@gmail.com', 'mayman', '12', 1),
('hoangtailoc@gmail.com', 'tailoc', '12', 0),
('lehaingoai@gmail.com', 'haingoai', '12', 0),
('thanhvuive@gmail.com', 'vuive', '12', 0),
('vuonguocmo@gmail.com', 'uocmo', '12', 0),
('thanhhienthuc@gmail.com', 'hienthuc', '12', 0),
('leankhang@gmail.com', 'ankhang', '12', 0),
('nguyentyphu@gmail.com', 'typhu', '12', 1),
('huuthongminh@gmail.com', 'thongminh', '12', 0),
('huutronghieu@gmail.com', 'tronghieu', '12', 0),
('lethanhduc@gmail.com', 'thanhduc', '12', 0)
go

insert into DONATIONS values
('anhSangChoDoiMatE.png', N'Phủ xanh đồi trọc', 8000000, 1000),
('thapSangUocMo.png', N'Thắp sáng ước mơ các em nhỏ vùng Cao Nguyên', 7000000, 950),
('anhSangChoDoiMatE.png', N'Mang ánh sáng cho đôi mắt em', 20000000, 2000),
('nhipDapChoTraiTimE.png', N'Mang nhịp đập cho trái tim em', 50000000, 5000),
('goiComNghiaTinh.png', N'Góp gói cơm nghĩa tình cho các em vùng xa', 10000000, 2500),
('nguoiGiaNeoDon.png', N'Hỗ trợ bữa cơm thêm ấm lòng các cụ già neo đơn', 15000000, 1500),
('nguoiKhuyetTat.png', N'Hỗ trợ kinh phí việc làm cho các em khuyết tật', 15000000, 1500),
('tuSuaTruong.png', N'Hỗ trợ tu sửa ngôi trường cho các em vùng sâu', 20000000, 2000),
('nhaTinhThuong.png', N'Xây nhà tình thương cho người nghèo', 100000000, 15000),
('xeDapChoCacE.png', N'Hỗ trợ xe đạp cho các em đến trường', 30000000, 4000),
('mangXanhChoMT.png', N'Chung tay xây dựng mảng xanh cho môi trường', 35000000, 5000),
('xeLanChoNguoiKhuyetTat.png', N'Hỗ trợ kinh phí mua xe lăn cho người khuyết tật', 50000000, 6000),
('hoHamEch.png', N'Hỗ trợ kinh phí cho các em hở hàm ếch', 300000000, 10000),
('tiepSucDenTruong.png', N'Tiêp sức cho các em khó khăn đến trường', 15000000, 2500),
('nguonNuocSach.png', N'Chung tay tạo nguồn nước sạch cho vùng nông thôn khó khăn', 20000000, 4000),

('trangThietBiTre.png', N'Hỗ trợ chi phí cho thiết bị chăm sóc trẻ sơ sinh', 500000000, 15000),
('phongChongXamHai.png', N'Chung tay tặng học bổng và kỹ năng phòng chống xâm hại trẻ em', 15000000, 1200)
go

insert into INFORMATION_CONTRIBUTORS values
(1, 1, N'Nguyễn Minh Thông', 500000, convert(datetime, '01/06/2022', 103)),
(2, 2, N'Nguyễn Đức Minh', 200000, convert(datetime,'02/06/2022',103)),
(3, 3, N'Lê Hữu Trí', 100000, convert(datetime,'03/06/2022',103)),
(4,4, N'Lê Minh Tuệ', 50000, convert(datetime,'04/06/2022',103)),
(4, 5, N'Trần Văn Lộc', 150000, convert(datetime,'05/06/2022',103)),
(4, 6,  N'Trần Hữu Tài', 250000, convert(datetime,'06/06/2022',103)),
(4,7,  N'Nguyễn Ngọc Giàu', 300000, convert(datetime,'07/06/2022',103)),


(8, 8, N'Linh Tú Mai', 40000, convert(datetime,'08/06/2022',103)),
(9, 9, N'Linh Gia Mắn', 30000, convert(datetime,'09/06/2022',103)),
(10, 10, N'Trần Thanh Thành', 20000, convert(datetime,'10/06/2022',103)),
(11, 11, N'Trần Hữu Công', 60000, convert(datetime,'11/06/2022',103)),
(12, 12,  N'Lê Gia Hân', 70000, convert(datetime, '12/06/2022', 103)),
(13, 13, N'Lê Gia Hạnh', 10000, convert(datetime,'13/06/2022', 103)),
(14, 14, N'Lê Ngọc Bảo Giang', 35000, convert(datetime, '14/06/2022', 103)),
(15, 15, N'Trần Thanh Thảo', 55000, convert(datetime, '15/06/2022', 103))

