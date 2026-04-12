package com.kaiquerafael.AprendendoSpring.infrastructure.exceptions;

public class conflictException extends RuntimeException {
    public conflictException(String message) {
        super(message);
    }
    public conflictException(String message, Throwable cause) {
    super(message);}
}
