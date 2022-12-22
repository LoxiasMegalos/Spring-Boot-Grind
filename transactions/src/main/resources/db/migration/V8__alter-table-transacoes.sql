ALTER TABLE transacoes DROP COLUMN debitedAccount_id;
ALTER TABLE transacoes ADD  debited_account_id int not null unique references contas(id);

ALTER TABLE transacoes DROP COLUMN creditedAccount_id;
ALTER TABLE transacoes ADD  credited_account_id int not null unique references contas(id);