package api.transactions.transactions.domain.conta.controller;


import api.transactions.transactions.domain.conta.ContaRepository;
import api.transactions.transactions.domain.conta.model.ContaInformacaoDTO;
import api.transactions.transactions.domain.conta.model.DepositoDTO;
import api.transactions.transactions.infra.TratadorDeErros;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController extends TratadorDeErros {

    @Autowired
    private ContaRepository contaRepository;

    @PutMapping("/deposito")
    @Transactional
    public ResponseEntity<ContaInformacaoDTO> deposita(@RequestBody @Valid DepositoDTO dadosDeposito) throws Exception {
        var contaBuscada = contaRepository.getReferenceById(dadosDeposito.id());
        contaBuscada.atualizaConta(dadosDeposito);
        return ResponseEntity.ok(new ContaInformacaoDTO(contaBuscada));
    }

}
