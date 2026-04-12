package com.projetocadastro.usuario.infrastructure.exceptions;

public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException(String message) {
        super(message);
    }
        public ResourseNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
