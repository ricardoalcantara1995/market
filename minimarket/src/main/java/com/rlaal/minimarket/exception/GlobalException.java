package com.rlaal.minimarket.exception;

import com.rlaal.minimarket.dto.response.MessageResponseDTO;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageResponseDTO>handleEntityNotFound(RuntimeException ex){
        MessageResponseDTO messaje = new MessageResponseDTO(HttpStatus.NOT_FOUND,ex.getMessage());
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messaje);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MessageResponseDTO>handleTypeMismatch(MethodArgumentTypeMismatchException  ex){
        String messajeError = "El parametro ingresado es incorrecto";
        MessageResponseDTO messaje = new MessageResponseDTO(HttpStatus.BAD_REQUEST,messajeError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messaje);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<MessageResponseDTO>handleResourceDuplicate(DuplicateResourceException  ex){
        MessageResponseDTO messaje = new MessageResponseDTO(HttpStatus.CONFLICT,ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messaje);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<MessageResponseDTO>handleNotValid(HttpRequestMethodNotSupportedException ex){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO(HttpStatus.METHOD_NOT_ALLOWED,ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(messageResponseDTO);
    }

}
