ALTER TABLE transacoes DROP COLUMN debited_account_id;
ALTER TABLE transacoes ADD  debited_account_id int not null references contas(id);

ALTER TABLE transacoes DROP COLUMN credited_account_id;
ALTER TABLE transacoes ADD  credited_account_id int not null references contas(id);