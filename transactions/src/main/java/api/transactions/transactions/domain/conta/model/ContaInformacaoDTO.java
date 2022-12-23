package api.transactions.transactions.domain.conta.model;

import api.transactions.transactions.domain.conta.entity.Conta;

public record ContaInformacaoDTO(
        Long id,
        Double balance//,
        //DadosListagemUsuarios user
) {
    public ContaInformacaoDTO(Conta conta){
        //, new DadosListagemUsuarios(conta.getUsuario())
        this(conta.getId(), conta.getBalance());
    }
}
