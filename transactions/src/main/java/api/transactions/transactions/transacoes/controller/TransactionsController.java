package api.transactions.transactions.transacoes.controller;

import api.transactions.transactions.conta.ContaRepository;
import api.transactions.transactions.transacoes.TransactionRepository;
import api.transactions.transactions.transacoes.entity.Transacoes;
import api.transactions.transactions.transacoes.model.DataTransacaoDTO;
import api.transactions.transactions.transacoes.model.TransacaoDTO;
import api.transactions.transactions.usuario.UserRepository;
import api.transactions.transactions.usuario.model.DadosListagemUsuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    UserRepository repository;
    @Autowired
    ContaRepository contarepository;
    @Autowired
    TransactionRepository transactionrepository;

    @PostMapping
    @Transactional
    public void transaction(@RequestBody @Valid TransacaoDTO dados) throws Exception {
        if(!dados.usuarioPagador().equals(dados.usuarioReceptor())){
            var buscaPagador = repository.getReferenceByUsername(dados.usuarioPagador());
            var buscaReceptor = repository.getReferenceByUsername(dados.usuarioReceptor());

            if(buscaPagador == null){
                throw new Exception("Nome Pagador Incorreto");
            }

            if(buscaReceptor == null){
                throw new Exception("Nome Receptor Incorreto");
            }

            if(buscaPagador.getConta().getBalance() - dados.valor() < 0){
                throw new Exception("Transação Invalida, balance insuficiente");
            }

            var contaPagadora = contarepository.getReferenceById(buscaPagador.getConta().getId());
            var contaReceptora = contarepository.getReferenceById(buscaReceptor.getConta().getId());

            contaPagadora.atualizaBalance((-1 * dados.valor()));
            contaReceptora.atualizaBalance(dados.valor());

            transactionrepository.save(new Transacoes(dados, contaPagadora, contaReceptora));

        } else{
            throw new Exception("Transação Invalida, Impossível transferir para si mesmo");
        }
    }

    @GetMapping("/{id}")
    public List<TransacaoDTO> getTransactionsById(@PathVariable Long id){
        var transacoesDebitadas = transactionrepository.getReferenceByDebitedAccountId(id);
        var transacoesCreditadas = transactionrepository.getReferenceByCreditedAccountId(id);

        List<TransacaoDTO> creditadas = transacoesCreditadas.stream().map(TransacaoDTO::new).toList();
        List<TransacaoDTO> debitadas = transacoesDebitadas.stream().map(TransacaoDTO::new).toList();

        ArrayList<TransacaoDTO> todas = new ArrayList<TransacaoDTO>(creditadas);
        todas.addAll(debitadas);

        return todas;
    }

    @GetMapping
    public List<TransacaoDTO> getTransactionsByDate(@RequestBody @Valid DataTransacaoDTO data){
        //DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String dataBuscada = data.dataBuscada();//format.format(data.dataBuscada());
        var transacoesCreditadasDaData = transactionrepository.findAllByCreatedAtAndCreditedAccountId(dataBuscada, data.id());
        var transacoesDebitadasDaData = transactionrepository.findAllByCreatedAtAndDebitedAccountId(dataBuscada, data.id());

        List<TransacaoDTO> creditadas = transacoesCreditadasDaData.stream().map(TransacaoDTO::new).toList();
        List<TransacaoDTO> debitadas = transacoesDebitadasDaData.stream().map(TransacaoDTO::new).toList();

        ArrayList<TransacaoDTO> todas = new ArrayList<TransacaoDTO>(creditadas);
        todas.addAll(debitadas);

        return todas;
    }

    @GetMapping("credited/{id}")
    public List<TransacaoDTO> getCreditedTransactionsById(@PathVariable Long id){
        var creditadas = transactionrepository.getReferenceByCreditedAccountId(id);

        List<TransacaoDTO> creditadasFormatada = creditadas.stream().map(TransacaoDTO::new).toList();

        return creditadasFormatada;
    }

    @GetMapping("debited/{id}")
    public List<TransacaoDTO> getDebitedTransactionsById(@PathVariable Long id){
        var debitadas = transactionrepository.getReferenceByDebitedAccountId(id);

        List<TransacaoDTO> debitadasFormatada = debitadas.stream().map(TransacaoDTO::new).toList();

        return debitadasFormatada;
    }
}
