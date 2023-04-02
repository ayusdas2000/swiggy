package com.example.geektrust.repositories;

import java.util.List;

import com.example.geektrust.entities.Player;

public interface IPlayerRepository {
    Player savePlayer(Player player);

    List<Player> getAllPlayers();
}
