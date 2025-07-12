package com.project.argoss.apresentation.exception;

public class InvalidateCredentialsException extends RuntimeException{

    public InvalidateCredentialsException(String mensagem){
        super(mensagem);
    }

    public InvalidateCredentialsException(){
        super("senha ou email incorreto");
    }
}