package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosAtualizaEndereco;

public record DadosAtualizarMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,

        @Valid //outro DTO que também deve ser validado!
        DadosAtualizaEndereco endereco
) {
}
