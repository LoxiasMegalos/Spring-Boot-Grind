package api.transactions.transactions.conta.entity;

import api.transactions.transactions.conta.model.DepositoDTO;
import api.transactions.transactions.transacoes.entity.Transacoes;
import api.transactions.transactions.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @OneToOne(mappedBy = "conta")
    private Usuario usuario;

    public Conta(Double saldoInicial){
        this.balance = saldoInicial;
    }

    @OneToMany(mappedBy="debitedAccount")
    private List<Transacoes> debited;

    @OneToMany(mappedBy="creditedAccount")
    private List<Transacoes> credited;

    public String atualizaConta(DepositoDTO dadosDeposito) throws Exception {

        if(dadosDeposito.valor() > 0){
            this.balance += dadosDeposito.valor();
            return "Depósito realizado com sucesso";
        }

        throw new Exception("Quantia Inválida");
    }

    public void atualizaBalance(Double valor) {
        this.balance += valor;
    }
}
