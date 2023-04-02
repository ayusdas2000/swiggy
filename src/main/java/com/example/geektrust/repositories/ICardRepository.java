package com.example.geektrust.repositories;

import java.util.List;

import com.example.geektrust.entities.Card;

public interface ICardRepository {
    Card saveCard(Card card);

    List<Card> getAllCards();
}
