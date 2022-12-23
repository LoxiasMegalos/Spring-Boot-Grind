package api.transactions.transactions.domain.transacoes;

import api.transactions.transactions.domain.transacoes.entity.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TransactionRepository extends JpaRepository<Transacoes, Long> {
    ArrayList<Transacoes> getReferenceByDebitedAccountId(Long id);

    ArrayList<Transacoes> getReferenceByCreditedAccountId(Long id);

    ArrayList<Transacoes> findAllByCreatedAtAndCreditedAccountId(String dataBuscada, Long id);

    ArrayList<Transacoes> findAllByCreatedAtAndDebitedAccountId(String dataBuscada, Long id);
}
