package api.transactions.transactions.usuario.model;

import api.transactions.transactions.conta.entity.Conta;

public record DadosListagemContas(
        Long accountId,
        Double balance
) {

    public DadosListagemContas(Conta conta){
        this(conta.getId(), conta.getBalance());
    }

}
