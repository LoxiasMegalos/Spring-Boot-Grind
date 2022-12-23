package api.transactions.transactions.domain.usuario.model;

import api.transactions.transactions.domain.conta.entity.Conta;

public record DadosListagemContas(
        Long accountId,
        Double balance
) {

    public DadosListagemContas(Conta conta){
        this(conta.getId(), conta.getBalance());
    }

}
