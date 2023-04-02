package com.example.geektrust.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.geektrust.entities.Game;

public class GameRepository implements IGameRepository {
    private HashMap<String, Game> gameMapping;
    private Integer autoIncrement = 0;

    public GameRepository() {
        this.gameMapping = new HashMap<String, Game>();
    }

    @Override
    public Game saveGame(Game game) {
        if (game.getId() == null) {
            autoIncrement++;
            Game newGame = new Game(Integer.toString(autoIncrement),
                    game.getCardsBelongingToPlayer().keySet().stream().collect(Collectors.toList()));
            gameMapping.put(newGame.getId(), newGame);
            return newGame;

        }
        gameMapping.put(game.getId(), game);
        return game;

    }

    @Override
    public Game getFirstGame() {
        Map.Entry<String, Game> firstEntry = gameMapping.entrySet().iterator().next();
        Game firstValue = firstEntry.getValue();
        return firstValue;
    }

    @Override
    public void removeGame() {
        // only one game is there at one time in repo
        Map.Entry<String, Game> firstEntry = gameMapping.entrySet().iterator().next();
        String key = firstEntry.getKey();

        gameMapping.remove(key);
        return;
    }

}
