package com.example.demo.Controller;

import com.example.demo.model.Game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class GameController {

    public GameController() {}

    @GetMapping("/game/{id}")
    Game one(@PathVariable Long id) {
        return new Game(id);
    }
}
