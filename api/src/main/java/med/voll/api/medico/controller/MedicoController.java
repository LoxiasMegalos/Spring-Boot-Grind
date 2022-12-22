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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @GetMapping("/{id}")
    public DadosListagemMedico medicoPorCrm(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return new DadosListagemMedico(medico);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados){
        var medicoBuscado = repository.getReferenceById(dados.id());
        medicoBuscado.atualizarInformacoes(dados);
    }

    @DeleteMapping("/tradicional/{id}")
    @Transactional
    public void deletarTradicional(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarLogico(@PathVariable Long id){
        var medicoBuscado = repository.getReferenceById(id);
        medicoBuscado.excluir();
    }


}


