package api.transactions.transactions.usuario.controller;


import api.transactions.transactions.conta.ContaRepository;
import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.usuario.UserRepository;
import api.transactions.transactions.usuario.entity.Usuario;
import api.transactions.transactions.usuario.model.DadosListagemContas;
import api.transactions.transactions.usuario.model.DadosListagemUsuarios;
import api.transactions.transactions.usuario.model.SignUsuarioDTO;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UsuarioController {


    @Autowired
    private UserRepository userrepository;

    @Autowired
    private ContaRepository contaRepository;

    @RequestMapping("/signup")
    @PostMapping
    @Transactional
    public void cadastrarNovoUsuario(@RequestBody @Valid SignUsuarioDTO dados) throws Exception{
        var verificaNome = userrepository.getReferenceByUsername(dados.username());

        if(verificaNome == null || !verificaNome.getUsername().equals(dados.username())){
            Conta contaDoUsuario = contaRepository.save(new Conta(100.0));
            userrepository.save(new Usuario(dados, contaDoUsuario));
        } else{
            throw new Exception("Username já em uso!");
        }

    }

    @GetMapping
    public List<DadosListagemUsuarios> getAllUsers(){
        var users = userrepository.findAll();
        return users.stream().map(DadosListagemUsuarios::new).toList();
    }

    @GetMapping("/signin")
    public DadosListagemUsuarios login(@RequestBody @Valid SignUsuarioDTO dados){

        var userLogin = userrepository.getReferenceByUsername(dados.username());
        DadosListagemContas contaDoUsuario = new DadosListagemContas(userLogin.getConta());

        if(userLogin.getPassword().equals(dados.password())){
            return new DadosListagemUsuarios(userLogin, contaDoUsuario);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String deletaUser(@PathVariable Long id){

        Boolean usuarioDeletado = false;

        var users = userrepository.findAll();

        for(Usuario user : users){
            if(user.getId().equals(id)){
                userrepository.deleteById(id);
                usuarioDeletado = true;
                break;
            }
        }

        if(usuarioDeletado){
            return "Usuário id: "+id+" Deletado com sucesso!";
        }
        return "Usuário id: "+id+" Não existe no banco";
    }

}
