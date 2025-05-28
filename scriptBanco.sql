drop database if exists ordensof;
create database ordensof;

use ordensof;

create table users (
	id int primary key auto_increment,
    `name` varchar(120),
    email varchar(120),
    `role` varchar(50),
    `password` varchar(35)
);

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

create table ordensFornecimento (
	codigo int primary key auto_increment,
	`description` varchar(250),
	`status` varchar(30),
	created_at datetime,
	updated_at datetime
);

insert into users (`name`, email, `role`, `password`) values ('Rafael', 'rafael@stefanini.com', 'Admin', '123');
insert into users (`name`, email, `role`, `password`) values ('Shirley', 'shirley@stefanini.com', 'RT', '123');
insert into users (`name`, email, `role`, `password`) values ('Ezequiel', 'ezequiel@stefanini.com', 'Gerente', '123');

select * from users;
select * from employees;
