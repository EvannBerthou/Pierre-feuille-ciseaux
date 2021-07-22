package com.example.demo.Controller;

import com.example.demo.model.Game;
import com.example.demo.model.Games;
import com.example.demo.exception.InvalidPlayException;
import com.example.demo.exception.GameEndedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public GameController() {}

    /**
     * Renvoie les informations d'une partie
     * @params id L'id de la partie
     */
    @GetMapping("/game/{id}")
    Game one(@PathVariable Long id) {
        log.info("Game : " + id.toString());
        return Games.byId(id);
    }

    /**
     * Joue un mouvement dans une partie
     * @params id L'id de la partie
     * @params player L'id du joueur en train de jouer son coup
     * @params play Doit être soit p(ierre), f(euille) ou c(iseaux)
     *
     * @throws InvalidPlayException Dans le cas où play n'est pas valide (différent de p,f et c)
     */
    @PostMapping("/game/{id}/{player}/{play}")
    Game one(@PathVariable Long id, @PathVariable Long player, @PathVariable char play) {
        if (play != 'p' && play != 'f' && play != 'c') {
            log.info("Game : " + id.toString() + " play invalide : " + play);
            throw new InvalidPlayException(play);
        }
        log.info("Game : " + id.toString() + " plays : " + play);
        Game game = Games.getOrCreate(id);
        if (game.getHasEnded()) {
            throw new GameEndedException(id);
        }
        game.addTour(player, play);
        return game;
    }
}
