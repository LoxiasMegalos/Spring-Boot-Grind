package api.transactions.transactions.domain.transacoes.entity;

import api.transactions.transactions.domain.conta.entity.Conta;
import api.transactions.transactions.domain.transacoes.model.TransacaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "transacoes")
@Entity(name = "Transacoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "creditedAccount_id", nullable = false)
    private Conta  debitedAccount;

    @ManyToOne
    @JoinColumn(name = "debitedAccount_id", nullable = false)
    private Conta creditedAccount;

    private String createdAt;

    public Transacoes(TransacaoDTO dados, Conta contaPagadora, Conta contaReceptora) {
        this.valor = dados.valor();
        this.debitedAccount = contaPagadora;
        this.creditedAccount = contaReceptora;
        Date data = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        this.createdAt = format.format(data);
    }
}
