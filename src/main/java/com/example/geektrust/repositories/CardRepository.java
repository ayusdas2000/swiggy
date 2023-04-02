package com.example.geektrust.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.geektrust.entities.Card;

public class CardRepository implements ICardRepository {
    private HashMap<String, Card> cardMapping;
    private Integer autoIncrement = 0;

    public CardRepository() {
        this.cardMapping = new HashMap<String, Card>();
    }

    @Override
    public Card saveCard(Card card) {
        if (card.getId() == null) {
            autoIncrement++;
            Card newCard = new Card(Integer.toString(autoIncrement), card.getRank(), card.getSuit());
            cardMapping.put(Integer.toString(autoIncrement), newCard);
            return newCard;
        }
        cardMapping.put(card.getId(), card);
        return card;
    }

    @Override
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<Card>(cardMapping.values());
        return cards;
    }

}
