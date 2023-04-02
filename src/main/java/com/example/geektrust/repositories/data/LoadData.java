package com.example.geektrust.repositories.data;

import com.example.geektrust.entities.Card;
import com.example.geektrust.repositories.ICardRepository;

public class LoadData implements IData {
    private final ICardRepository iCardRepository;

    public LoadData(ICardRepository iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    @Override
    public void loadData() {
        Card cardToBeInserted;

        // insert clubs
        for (int i = 0; i < 13; i++) {
            cardToBeInserted = new Card(i + 1, "clubs");
            iCardRepository.saveCard(cardToBeInserted);
        }

        // insert diamonds
        for (int i = 0; i < 13; i++) {
            cardToBeInserted = new Card(i + 1, "diamonds");
            iCardRepository.saveCard(cardToBeInserted);
        }

        // insert spades
        for (int i = 0; i < 13; i++) {
            cardToBeInserted = new Card(i + 1, "spades");
            iCardRepository.saveCard(cardToBeInserted);
        }

        // insert hearts
        for (int i = 0; i < 13; i++) {
            cardToBeInserted = new Card(i + 1, "hearts");
            iCardRepository.saveCard(cardToBeInserted);
        }

    }

}
