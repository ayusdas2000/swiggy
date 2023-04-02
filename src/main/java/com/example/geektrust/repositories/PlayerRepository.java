package com.example.geektrust.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.geektrust.entities.Player;

public class PlayerRepository implements IPlayerRepository {
    private HashMap<String, Player> playerMapping;
    private Integer autoIncrement = 0;

    public PlayerRepository() {
        this.playerMapping = new HashMap<String, Player>();
    }

    @Override
    public Player savePlayer(Player player) {
        if (player.getId() == null) {
            autoIncrement++;
            Player newPlayer = new Player(Integer.toString(autoIncrement), player.getName());
            playerMapping.put(Integer.toString(autoIncrement), newPlayer);
            return newPlayer;
        }
        playerMapping.put(player.getId(), player);
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<Player>(playerMapping.values());
        return players;
    }
}
