create database ordensof;

use ordensof;

create table users (
	id int primary key auto_increment,
    name varchar(120),
    email varchar(120),
    role varchar(50),
    password varchar(35)
);