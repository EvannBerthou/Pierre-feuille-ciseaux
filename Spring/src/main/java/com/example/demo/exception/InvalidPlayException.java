package com.example.demo.exception;

public class InvalidPlayException extends RuntimeException {
    public InvalidPlayException(char play) {
        super("'" + play + "' n'est pas un caractères valide");
    }
}
