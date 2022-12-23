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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<DadosListagemUsuarios> cadastrarNovoUsuario(@RequestBody @Valid SignUsuarioDTO dados, UriComponentsBuilder urlBuilder) throws Exception{
        var verificaNome = userrepository.getReferenceByUsername(dados.username());

        if(verificaNome == null || !verificaNome.getUsername().equals(dados.username())){
            Conta contaDoUsuario = contaRepository.save(new Conta(100.0));
            var newUser = userrepository.save(new Usuario(dados, contaDoUsuario));
            var url = urlBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
            return ResponseEntity.created(url).body(new DadosListagemUsuarios(newUser));
        } else{
            throw new Exception("Username já em uso!");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemUsuarios> getUserById(@PathVariable Long id){
        var users = userrepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemUsuarios(users));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUsuarios>> getAllUsers(){
        var users = userrepository.findAll().stream().map(DadosListagemUsuarios::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/signin")
    public ResponseEntity<DadosListagemUsuarios> login(@RequestBody @Valid SignUsuarioDTO dados){

        var userLogin = userrepository.getReferenceByUsername(dados.username());
        DadosListagemContas contaDoUsuario = new DadosListagemContas(userLogin.getConta());

        if(userLogin.getPassword().equals(dados.password())){
            return ResponseEntity.ok(new DadosListagemUsuarios(userLogin, contaDoUsuario));
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaUser(@PathVariable Long id){

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
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
