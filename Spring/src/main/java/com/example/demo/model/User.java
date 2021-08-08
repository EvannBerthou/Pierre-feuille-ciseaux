package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    public User() {}

    public User(String _username, String _password) {
        this.username = _username;
        this.password = _password;
    }
}

