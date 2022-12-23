package api.transactions.transactions.conta.controller;


import api.transactions.transactions.conta.ContaRepository;
import api.transactions.transactions.conta.model.ContaInformacaoDTO;
import api.transactions.transactions.conta.model.DepositoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

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
