package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.entities.Player;
import com.example.geektrust.exceptions.PlayersFullException;
import com.example.geektrust.repositories.IPlayerRepository;

public class PlayerServices implements IPlayerServices {
    private final IPlayerRepository iPlayerRepository;

    public PlayerServices(IPlayerRepository iPlayerRepository) {
        this.iPlayerRepository = iPlayerRepository;
    }

    @Override
    public Player addPlayer(String name) {
        List<Player> players = iPlayerRepository.getAllPlayers();
        if (players.size() >= 4) {
            throw new PlayersFullException("Players already full");
        }
        Player player = new Player(name);
        Player insertedPlayer = iPlayerRepository.savePlayer(player);
        return insertedPlayer;
    }

}
