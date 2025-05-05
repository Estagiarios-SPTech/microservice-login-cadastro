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
    `user` int, foreign key (`user`) references users(id), 
    rt int, foreign key (rt) references users(id),
    manager int, foreign key (manager) references users(id)
    
);

create table ordensFornecimento (
	codigo int primary key auto_increment,
	`description` varchar(250),
	`status` varchar(30),
	created_at datetime, 
	updated_at datetime
)