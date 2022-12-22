package api.transactions.transactions.usuario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignUsuarioDTO(

        @NotBlank
        @Length(min = 3)
        String username,

        @NotBlank
        @Pattern(regexp = "(.*\\d.*)")
        @Pattern(regexp = "(.*[A-Z].*)")
        @Length(min = 8)
        String password
) {}
