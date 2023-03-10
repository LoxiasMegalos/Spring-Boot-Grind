package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank(message = "O campo crm precisa ser preenchido!")
        @Pattern(regexp = "\\d{4,7}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid //outro DTO que também deve ser validado!
        DadosEndereco endereco
) {
}
