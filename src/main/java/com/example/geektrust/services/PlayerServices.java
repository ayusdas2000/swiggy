package com.example.geektrust.services;

import com.example.geektrust.entities.Player;
import com.example.geektrust.repositories.IPlayerRepository;

public class PlayerServices implements IPlayerServices {
    private final IPlayerRepository iPlayerRepository;

    public PlayerServices(IPlayerRepository iPlayerRepository) {
        this.iPlayerRepository = iPlayerRepository;
    }

    @Override
    public Player addPlayer(String name) {
        Player player = new Player(name);
        Player insertedPlayer = iPlayerRepository.savePlayer(player);
        return insertedPlayer;
    }

}
