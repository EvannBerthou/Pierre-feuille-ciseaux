package com.example.demo.exception;

public class InvalidGameIdException extends RuntimeException{

    public InvalidGameIdException(Long id) {
        super("Aucune partie avec l'id '" + id + "'");
    }
}

