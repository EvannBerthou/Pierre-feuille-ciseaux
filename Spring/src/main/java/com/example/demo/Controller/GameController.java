package com.example.demo.Controller;

import com.example.demo.model.Game;
import com.example.demo.model.GameRepository;
import com.example.demo.model.UserService;
import com.example.demo.exception.InvalidPlayException;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.exception.InvalidGameIdException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class GameController {
    /**
     * Est utilisé afin de faire le lien entre MySQL et la partie Java.
     */
    @Autowired private GameRepository gameRepository;
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    
    @Autowired private UserService userService;

    public GameController() {}

    /**
     * Crée une nouvelle partie et la renvoie
     */
    @PostMapping("/game")
    Game newGame() {
        Game game = new Game();
        return gameRepository.save(game);
    }

    /**
     * Renvoie les informations d'une partie
     * @params id L'id de la partie
     */
    @GetMapping("/game/{id}")
    Game one(@PathVariable Long id) {
        Optional<Game> optGame = gameRepository.findById(id);
        if (optGame.isEmpty()) return null;
        return optGame.get();
    }

    /**
     * Joue un mouvement dans une partie
     * @params id L'id de la partie
     * @params player L'id du joueur en train de jouer son coup
     * @params play Doit être soit p(ierre), f(euille) ou c(iseaux)
     *
     * @throws InvalidPlayException Dans le cas où play n'est pas valide (différent de p,f et c)
     */
    @PostMapping("/game/{gameId}/{play}")
    Game play(HttpServletRequest request, @PathVariable Long gameId, @PathVariable char play) {
        Long playerId = userService.getUserIdFromRequest(request);
        if (playerId == -1L) return null;

        if (play != 'p' && play != 'f' && play != 'c') {
            log.info("Game : " + gameId.toString() + " play invalide : " + play);
            throw new InvalidPlayException(play);
        }

        Optional<Game> optGame = gameRepository.findById(gameId);
        if (!optGame.isPresent()) {
            throw new InvalidGameIdException(gameId);
        }

        Game game = optGame.get();
        game.addTour(playerId, play);
        game = gameRepository.save(game);
        return game;
    }

    /**
     * Renvoie une liste des parties en fonction d'un filtre donnée
     */
    @GetMapping("/game")
    public @ResponseBody Iterable<Game> getAllGames(@RequestParam(required = false) String filter) {
        if (filter == null)                return hideToursOnPlayingGames(gameRepository.findAll());
        if (filter.equals("ended"))        return gameRepository.findByIsEnded(true);
        else if (filter.equals("playing")) return gameRepository.findByIsEnded(false);
        else if (filter.equals("sort"))    return gameRepository.findByOrderByCreationDateAsc();
        return hideToursOnPlayingGames(gameRepository.findAll());
    }

    /**
     * Cache le coup des joueurs des parties non terminés. Cela permet d'éviter qu'un autre joueur puisse
     * savoir le coup à jouer à l'avance.
     */
    private Iterable<Game> hideToursOnPlayingGames(Iterable<Game> games) {
        for (Game game : games) {
            if (!game.isEnded()) {
                game.clearTours();
            }
        }
        return games;
    }

    @GetMapping("/games/{username}")
    public Iterable<Game> getGamesPlayedBy(@PathVariable String username) {
        List<Game> games = gameRepository.findAllPlayedBy(username);
        return hideToursOnPlayingGames(games);
    }
}
