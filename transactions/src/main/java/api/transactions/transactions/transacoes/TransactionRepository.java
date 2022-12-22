package api.transactions.transactions.transacoes;

import api.transactions.transactions.transacoes.entity.Transacoes;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transacoes, Long> {
    ArrayList<Transacoes> getReferenceByDebitedAccountId(Long id);

    ArrayList<Transacoes> getReferenceByCreditedAccountId(Long id);

    ArrayList<Transacoes> findAllByCreatedAtAndCreditedAccountId(String dataBuscada, Long id);

    ArrayList<Transacoes> findAllByCreatedAtAndDebitedAccountId(String dataBuscada, Long id);
}
