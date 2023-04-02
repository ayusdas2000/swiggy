package com.example.geektrust.repositories;

import com.example.geektrust.entities.Game;

public interface IGameRepository {
    Game saveGame(Game game);

    Game getFirstGame();

    void removeGame();
}
