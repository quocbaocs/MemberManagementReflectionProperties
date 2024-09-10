CREATE TABLE members (
	member_id int NOT NULL primary key auto_increment ,
	member_login varchar (50) NULL ,
	member_password varchar (50) NULL ,
	first_name nvarchar (50) NULL ,
	last_name nvarchar (50) NULL ,
	email nvarchar (50) NULL ,
	phone nvarchar (50) NULL ,
	address nvarchar (50) NULL,
    CRE_DATE DATETIME NOT NULL COMMENT 'Ngày đăng ký',
    MOD_DATE DATETIME NOT NULL COMMENT 'Ngày thay đổi mật khẩu cuối cùng'
    )