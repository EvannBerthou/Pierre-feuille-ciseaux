package com.example.demo.model;

import lombok.Getter;

@Getter
public class Game {
    private Long id;

    public Game(Long id2) {
        this.id = id2;
    }
}
