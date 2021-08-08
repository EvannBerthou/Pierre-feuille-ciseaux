package com.example.demo.model;

import lombok.Getter;

@Getter
public class Profil {
    private String username;

    public Profil(String _username) {
        this.username = _username;
    }
}
