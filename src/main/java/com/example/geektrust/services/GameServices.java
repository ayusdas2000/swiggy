package com.example.geektrust.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.example.geektrust.entities.Card;
import com.example.geektrust.entities.Game;
import com.example.geektrust.entities.GameStatus;
import com.example.geektrust.entities.Player;
import com.example.geektrust.repositories.ICardRepository;
import com.example.geektrust.repositories.IGameRepository;
import com.example.geektrust.repositories.IPlayerRepository;

public class GameServices implements IGameServices {

    private final IGameRepository iGameRepository;
    private final IPlayerRepository iPlayerRepository;
    private final ICardRepository iCardRepository;

    public GameServices(IGameRepository iGameRepository, IPlayerRepository iPlayerRepository,
            ICardRepository iCardRepository) {
        this.iGameRepository = iGameRepository;
        this.iPlayerRepository = iPlayerRepository;
        this.iCardRepository = iCardRepository;
    }

    @Override
    public Game setupGame() {
        List<Player> players = iPlayerRepository.getAllPlayers();
        Game game = new Game(players);
        Game insertedGame = iGameRepository.saveGame(game);
        return insertedGame;
    }

    @Override
    public Game distributeCards() {
        Game game = iGameRepository.getFirstGame();
        List<Card> cards = iCardRepository.getAllCards();

        // Schuffle cards
        Collections.shuffle(cards);
        LinkedHashMap<Player, Stack<Card>> cardsBelongingToPlayer = game.getCardsBelongingToPlayer();

        // iterate the hashmap
        for (Map.Entry<Player, Stack<Card>> entry : cardsBelongingToPlayer.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            int counter = 1;
            Stack<Card> cardsToBeGiven = new Stack<Card>();
            while (counter <= 5) {
                cardsToBeGiven.push(cards.get(0));
                cards.remove(0);
                counter++;
            }
            cardsBelongingToPlayer.put(entry.getKey(), cardsToBeGiven);

        }

        // Schuffle the hashmap to get schuffled player order
        List<Player> list = new ArrayList<>(cardsBelongingToPlayer.keySet());
        Collections.shuffle(list);

        LinkedHashMap<Player, Stack<Card>> shuffleMap = new LinkedHashMap<>();
        list.forEach(k -> shuffleMap.put(k, cardsBelongingToPlayer.get(k)));

        game.setCardsBelongingToPlayer(shuffleMap);
        Stack<Card> drawPile = new Stack<Card>();
        drawPile.addAll(cards);
        game.setDrawPile(drawPile);

        game = iGameRepository.saveGame(game);

        return game;
    }

    @Override
    public Game playGame() {
        Game game = iGameRepository.getFirstGame();
        // set game status
        game.setGameStatus(GameStatus.RUNNING);

        // get cards belonging to player
        LinkedHashMap<Player, Stack<Card>> playerSequence = game.getCardsBelongingToPlayer();

        // iterate the hashmap
        for (Map.Entry<Player, Stack<Card>> entry : playerSequence.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

            // if player has no cards then he is winner
            if (entry.getValue().size() == 0) {
                game.setWinner(entry.getKey());
                game.setGameStatus(GameStatus.FINISHED);
                // game finished remove it now;
                iGameRepository.removeGame();
                return game;
            }
            // if discard pile is empty or condition don't match then draw a card from draw
            // pile
            if (game.getDisCardedPile().size() == 0
                    || !entry.getValue().peek().drawingCondition(game.getDisCardedPile().peek())) {

                // if draw pile is empty then game ends in draw
                if (game.getDrawPile().size() == 0) {
                    game.setGameStatus(GameStatus.FINISHED_DRAW);
                    // game finished therefore remove from repo
                    iGameRepository.removeGame();
                    return game;
                }

                // get the drawstack
                Stack<Card> drawPile = game.getDrawPile();
                Card drawCard = drawPile.pop();
                // set the drawpile
                game.setDrawPile(drawPile);

                // get the discarded pile
                Stack<Card> discardedPile = game.getDisCardedPile();
                // place the card on discarded pile
                discardedPile.add(drawCard);
                game.setDisCardedPile(discardedPile);
            } else {
                // get the players cards
                Stack<Card> cardsOfPlayer = entry.getValue();
                // draw the card
                Card drawCard = cardsOfPlayer.pop();
                // get the discarded pile
                Stack<Card> discardedPile = game.getDisCardedPile();
                // add the card in discarded pile
                discardedPile.add(drawCard);
                game.setDisCardedPile(discardedPile);
                playerSequence.put(entry.getKey(), cardsOfPlayer);
            }

        }
        return game;

    }

}
