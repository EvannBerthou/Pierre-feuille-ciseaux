package com.example.demo.Controller;

import com.example.demo.model.Game;
import com.example.demo.exception.InvalidPlayException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public GameController() {}

    @GetMapping("/game/{id}")
    Game one(@PathVariable Long id) {
        log.info("Game : " + id.toString());
        return new Game(id);
    }

    /**
     * play doit être soit p(ierre), f(euille) ou c(iseaux)
     */
    @GetMapping("/game/{id}/{play}")
    Game one(@PathVariable Long id, @PathVariable char play) {
        if (play != 'p' && play != 'f' && play != 'c') {
            throw new InvalidPlayException(play);
        }

        log.info("Game : " + id.toString() + " plays : " + play);
        return new Game(id);
    }
}
