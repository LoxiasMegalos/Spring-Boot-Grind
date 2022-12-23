package med.voll.api.paciente.controller;


import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.URIParameter;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @RequestMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastroPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {

        var cadastro = repository.save(new Paciente(dados));
        var uri = uriBuilder.path("/paciente/cadastrar/{id}").buildAndExpand(cadastro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemPaciente(cadastro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var pacientes = repository.findAll(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemPaciente> getPacienteById(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemPaciente(paciente));
    }

    @GetMapping("cadastrar/{id}")
    public ResponseEntity<DadosListagemPaciente> getPacienteByIdCadastro(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemPaciente(paciente));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizaPaciente(@RequestBody @Valid DadosAtualizaPaciente dados){
        var pacienteAtualizado = repository.getReferenceById(dados.id());
        pacienteAtualizado.atualizaInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemPaciente(pacienteAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        repository.deleteById(paciente.getId());
        return ResponseEntity.noContent().build();
    }
}
