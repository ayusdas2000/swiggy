package com.example.geektrust.services;

import com.example.geektrust.entities.Game;

public interface IGameServices {
    Game setupGame();

    Game distributeCards();

    Game playGame();
}
