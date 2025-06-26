drop database if exists ordensof;
create database ordensof;

use ordensof;
show tables;

create table `user`(
	id int primary key auto_increment,
    `name` varchar(120),
    email varchar(120),
    `role` varchar(50),
    `password` varchar(35)
);

create table employee (
	id int primary key auto_increment,
    `user` int,
    rt int,
    manager int,
    `status` varchar(30),
    foreign key (`user`) references `user`(id),
    foreign key (rt) references `user`(id),
    foreign key (manager) references `user`(id)
);

create table ordem_fornecimento (
	codigo int primary key auto_increment,
	`description` varchar(250),
	`status` varchar(30),
	created_at datetime,
	updated_at datetime
);

insert into `user` (`name`, email, `role`, `password`) values ('Rafael', 'rafael@stefanini.com', 'Admin', '123');
insert into `user` (`name`, email, `role`, `password`) values ('Shirley', 'shirley@stefanini.com', 'RT', '123');
insert into `user` (`name`, email, `role`, `password`) values ('Ezequiel', 'ezequiel@stefanini.com', 'Gerente', '123');
insert into `user` (`name`, email, `role`, `password`) values ('Teste', 'teste@stefanini.com', 'Colaborador', 'Teste123!');

select * from `user`;
select * from employee;
select * from ordem_fornecimento;