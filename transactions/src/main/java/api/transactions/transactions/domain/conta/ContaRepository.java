package api.transactions.transactions.domain.conta;

import api.transactions.transactions.domain.conta.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
