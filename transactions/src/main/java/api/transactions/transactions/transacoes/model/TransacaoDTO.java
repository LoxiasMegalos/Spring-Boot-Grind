package api.transactions.transactions.transacoes.model;

import api.transactions.transactions.transacoes.entity.Transacoes;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TransacaoDTO(


        @Min(value = 1)
        Double valor,

        @NotBlank
        String usuarioPagador,

        @NotBlank
        String usuarioReceptor
) {

        public TransacaoDTO(Transacoes dados){
                this(dados.getValor(), dados.getDebitedAccount().getUsuario().getUsername(), dados.getCreditedAccount().getUsuario().getUsername());
        }
}
