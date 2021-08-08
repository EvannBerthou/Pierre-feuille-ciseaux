package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Tour {
    /** 
     * L'id unique du tour
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    /**
     * L'id du player qui a joué ce tour
     */
    private Long player;
    /**
     * Son coup (peut être p,f ou c)
     * */
    private char coup;

    public Tour() {}

    public Tour(Long _player, char _coup) {
        this.player = _player;
        this.coup = _coup;
    }
}
