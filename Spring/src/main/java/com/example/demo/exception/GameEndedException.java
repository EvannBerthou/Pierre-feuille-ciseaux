package com.example.demo.exception;

public class GameEndedException extends RuntimeException {
    public GameEndedException(Long id) {
        super("La partie avec l'id : '" + id + "' est déjà terminée");
    }
}

