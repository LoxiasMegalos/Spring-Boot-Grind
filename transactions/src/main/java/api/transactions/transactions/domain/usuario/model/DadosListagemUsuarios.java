package api.transactions.transactions.domain.usuario.model;

import api.transactions.transactions.domain.usuario.entity.Usuario;

public record DadosListagemUsuarios(
        Long id,
        String username,
        String password,
        DadosListagemContas conta
        //Long idContaDoUsuario,
        //Double balance
) {

   // public DadosListagemUsuarios(Usuario usuario){
   //     this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), new DadosListagemContas(usuario.getConta()).accountId(), new DadosListagemContas(usuario.getConta()).balance());
   // }

    public DadosListagemUsuarios(Usuario usuario, DadosListagemContas conta){
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), conta);
    }

    public DadosListagemUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword(), new DadosListagemContas(usuario.getConta()));
    }
}
