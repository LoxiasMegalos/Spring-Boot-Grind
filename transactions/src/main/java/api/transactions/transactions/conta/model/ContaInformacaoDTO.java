package api.transactions.transactions.conta.model;

import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.usuario.entity.Usuario;
import api.transactions.transactions.usuario.model.DadosListagemUsuarios;

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
