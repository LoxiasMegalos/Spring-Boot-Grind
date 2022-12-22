package api.transactions.transactions.conta;

import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
