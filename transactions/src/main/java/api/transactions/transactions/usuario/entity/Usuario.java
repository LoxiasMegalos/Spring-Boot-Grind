package api.transactions.transactions.usuario.entity;

import api.transactions.transactions.conta.entity.Conta;
import api.transactions.transactions.usuario.model.SignUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Conta conta;

    public Usuario(SignUsuarioDTO dados, Conta contaDoUsuario){
        this.username = dados.username();
        this.password = dados.password();
        this.conta = contaDoUsuario;
    }


}
