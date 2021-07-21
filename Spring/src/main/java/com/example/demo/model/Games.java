package com.example.demo.model;

import java.util.ArrayList;

public class Games {
    static ArrayList<Game> games = new ArrayList<Game>();
    
    public static Game byId(Long id) {
        for (Game game : games) {
            if (game.getId() == id) return game;
        }
        return null;
    }

    public static Game newGame(Long id) {
        Game game = new Game(id, null, null);
        games.add(game);
        return game;
    }

    public static Game getOrCreate(Long id) {
        Game game = byId(id);
        if (game != null) return game;
        return newGame(id);
    }
}
