package com.example.demo.exception;

public class SamePlayerException extends RuntimeException {
    public SamePlayerException(Long id) {
        super("Le joueur '" + id + "' a déjà joué dans cette partie.");
    }
}
