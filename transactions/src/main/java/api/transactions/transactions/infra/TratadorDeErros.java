package api.transactions.transactions.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratandoErro400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors().stream().map(Detalhes400::new).toList();
        return ResponseEntity.badRequest().body(erro);
    }

    public record Detalhes400(
            String atributo,
            String mensagem
    ){
        public Detalhes400(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratandoErro500(){
        return ResponseEntity.notFound().build();
    }
}
