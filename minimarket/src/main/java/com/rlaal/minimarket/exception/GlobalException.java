package com.rlaal.minimarket.exception;

import com.rlaal.minimarket.dto.response.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        MessageResponseDTO messaje = new MessageResponseDTO(HttpStatus.BAD_REQUEST,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messaje);
    }
}
