package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

public record DetalhesErro400DTO(
        String atributo,
        String code,
        String defaultMessage
) {
    public DetalhesErro400DTO(FieldError ex){
        this(ex.getField(), ex.getCode(), ex.getDefaultMessage());
    }
}
