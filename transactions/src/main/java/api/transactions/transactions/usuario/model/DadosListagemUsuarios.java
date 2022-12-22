package api.transactions.transactions.usuario.model;

import api.transactions.transactions.conta.ContaRepository;
import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

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
