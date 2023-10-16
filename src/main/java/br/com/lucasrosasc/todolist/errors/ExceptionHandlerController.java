package br.com.lucasrosasc.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*Anotação do Spring usada pra definir classes globais no momento do tratamento de exceções*/
@ControllerAdvice
public class ExceptionHandlerController {
    
    /*Annotation do Spring pra tratar exceção especificada no parenteses */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        /*Os métodos getMostSpecificCause aliado ao getMessage -> faz com que o retorno do erro seja 
         * exatamente a mensagem definida no throw do método que gera o erro
         */
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }
}
