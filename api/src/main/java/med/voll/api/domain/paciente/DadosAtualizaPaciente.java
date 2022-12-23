package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.DadosAtualizaEndereco;

public record DadosAtualizaPaciente(

        Long id,

        String nome,

        String email,

        String telefone,

        @Valid
        DadosAtualizaEndereco endereco

) {
}
