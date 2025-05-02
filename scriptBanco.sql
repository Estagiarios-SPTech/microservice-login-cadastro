drop database if exists ordensof;
create database ordensof;

use ordensof;

create table users (
	id int primary key auto_increment,
    name varchar(120),
    email varchar(120),
    role varchar(50),
    password varchar(35)
);

describe users;
select * from users;

create table employees (
	id int primary key auto_increment,
    `user` int, 
    rt int, 
    manager int,
    `status` varchar(30),
    foreign key (`user`) references users(id),
    foreign key (rt) references users(id),
    foreign key (manager) references users(id)
);

select * from employees;