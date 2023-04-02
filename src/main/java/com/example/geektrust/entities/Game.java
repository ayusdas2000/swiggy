package com.example.geektrust.entities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

public class Game extends BaseEntity {

    private Stack<Card> drawPile;
    private Stack<Card> disCardedPile;
    private LinkedHashMap<Player, Stack<Card>> cardsBelongingToPlayer;
    private GameStatus gameStatus;
    private Player winner;

    public Game(String id, List<Player> players) {
        this(players);
        this.id = id;
    }

    public Game(List<Player> players) {
        this.drawPile = new Stack<Card>();
        this.disCardedPile = new Stack<Card>();
        this.cardsBelongingToPlayer = new LinkedHashMap<Player, Stack<Card>>();
        for (Player player : players) {
            this.cardsBelongingToPlayer.put(player, new Stack<Card>());
        }
        this.gameStatus = GameStatus.NOT_STARTED;
        this.winner = null;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Stack<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(Stack<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public Stack<Card> getDisCardedPile() {
        return disCardedPile;
    }

    public void setDisCardedPile(Stack<Card> disCardedPile) {
        this.disCardedPile = disCardedPile;
    }

    public LinkedHashMap<Player, Stack<Card>> getCardsBelongingToPlayer() {
        return cardsBelongingToPlayer;
    }

    public void setCardsBelongingToPlayer(LinkedHashMap<Player, Stack<Card>> shuffleMap) {
        this.cardsBelongingToPlayer = shuffleMap;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
