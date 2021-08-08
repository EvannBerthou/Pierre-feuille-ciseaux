package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Profile {
    private String username;
    private int totalGame;
    private int winCount;
    private int loseCount;

    //TODO: Temporaire
    public Profile(String _username) {
        this.username = _username;
    }
}
