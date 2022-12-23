package med.voll.api.medico.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import med.voll.api.paciente.DadosCadastroPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @RequestMapping("/cadastrar")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = repository.save(new Medico(dados));

        var uri = uriBuilder.path("/medicos/cadastrar/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/cadastrar/{id}")
    public ResponseEntity<DadosListagemMedico> getMedicoPorId(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        var medicoBuscado = repository.getReferenceById(dados.id());
        medicoBuscado.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoBuscado));
    }

    @DeleteMapping("/tradicional/{id}")
    @Transactional
    public void deletarTradicional(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarLogico(@PathVariable Long id){
        var medicoBuscado = repository.getReferenceById(id);
        medicoBuscado.excluir();

        return ResponseEntity.noContent().build();
    }


}


