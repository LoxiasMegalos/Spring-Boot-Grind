package api.transactions.transactions.conta.controller;


import api.transactions.transactions.conta.ContaRepository;
import api.transactions.transactions.conta.model.DepositoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @PutMapping("/deposito")
    @Transactional
    public String deposita(@RequestBody @Valid DepositoDTO dadosDeposito){
        var contaBuscada = contaRepository.getReferenceById(dadosDeposito.id());
        return contaBuscada.atualizaConta(dadosDeposito);
    }

}
