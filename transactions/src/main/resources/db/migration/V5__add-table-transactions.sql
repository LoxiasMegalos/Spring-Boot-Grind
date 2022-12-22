create table transacoes(
    id bigint not null auto_increment primary key,
    valor decimal not null,
    debitedAccount_id int not null unique references contas(id),
    creditedAccount_id int not null unique references contas(id),
    createdAt date not null
);