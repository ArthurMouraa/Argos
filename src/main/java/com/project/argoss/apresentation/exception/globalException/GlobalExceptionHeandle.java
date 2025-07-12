package com.project.argoss.apresentation.exception.globalException;


import com.project.argoss.apresentation.exception.InvalidateCredentialsException;
import com.project.argoss.apresentation.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHeandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidateCredentialsException.class)
    public ResponseEntity heandleCredentialsInvalid(InvalidateCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity headleDuplicatedEmail(){
        String mensagem = "Email já cadastrado";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagem);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity handleRecursoNaoEncontrado(RecursoNaoEncontradoException e){
        String mensagem = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);

    }





}