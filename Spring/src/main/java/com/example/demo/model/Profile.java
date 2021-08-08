package com.example.demo.model;

import lombok.Getter;

@Getter
public class Profile {
    private String username;

    public Profile(String _username) {
        this.username = _username;
    }
}
