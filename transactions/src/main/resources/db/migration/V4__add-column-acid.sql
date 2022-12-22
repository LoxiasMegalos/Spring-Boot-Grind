ALTER TABLE usuarios
ADD account_id int not null unique references contas(id);