package api.transactions.transactions.usuario;

import api.transactions.transactions.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario getReferenceByUsername(String username);
}
