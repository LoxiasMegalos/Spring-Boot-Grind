package api.transactions.transactions.transacoes.model;

import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.transacoes.entity.Transacoes;

public record DetalhesTransacaoDTO(
        Long id,

        Double valor,

        Conta contaDebitada,

        Conta contraCreditada,

        String data
) {

    public DetalhesTransacaoDTO(Transacoes transacao){
        this(transacao.getId(), transacao.getValor(), transacao.getDebitedAccount(), transacao.getCreditedAccount(), transacao.getCreatedAt());
    }

}
