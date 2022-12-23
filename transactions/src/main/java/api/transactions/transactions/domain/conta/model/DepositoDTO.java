package api.transactions.transactions.domain.conta.model;

import jakarta.validation.constraints.NotBlank;

public record DepositoDTO(

        Long id,

        Double valor
) {
}
