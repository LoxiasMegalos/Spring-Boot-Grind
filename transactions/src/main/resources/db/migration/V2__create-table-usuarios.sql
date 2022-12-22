create table usuarios(
    id bigint not null auto_increment primary key,
    username varchar(100) not null,
    password varchar(100) not null,
    accountId int not null unique references contas(id)
);