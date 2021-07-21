package com.example.demo.model;

public class Tour {
    private Long player;
    private char coup;

    public Tour(Long _player, char _coup) {
        this.player = _player;
        this.coup = _coup;
    }

    public Long getPlayer() { return this.player; }
    public char getCoup() { return this.coup; }
}
