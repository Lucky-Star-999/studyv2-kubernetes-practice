create database kubernetespractice;

use kubernetespractice;

create table student (
	id bigint primary key auto_increment,
	name varchar(200),
	age int,
	major varchar(200)
)

-- Add some data
insert into student (id, name, age, major) values (null, 'Nguyễn Hoàng Linh', 23, 'Computer Science');
insert into student (id, name, age, major) values (null, 'Lucky', 23, 'Computer Science');