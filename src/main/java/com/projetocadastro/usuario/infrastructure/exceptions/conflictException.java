package com.projetocadastro.usuario.infrastructure.exceptions;

public class conflictException extends RuntimeException {
    public conflictException(String message) {
        super(message);
    }
    public conflictException(String message, Throwable cause) {
    super(message);}
}
