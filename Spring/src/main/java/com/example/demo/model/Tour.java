package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tour {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long player;
    private char coup;

    public Tour() {}

    public Tour(Long _player, char _coup) {
        this.player = _player;
        this.coup = _coup;
    }

    public Long getId() { return this.id; }
    public Long getPlayer() { return this.player; }
    public char getCoup() { return this.coup; }
}
