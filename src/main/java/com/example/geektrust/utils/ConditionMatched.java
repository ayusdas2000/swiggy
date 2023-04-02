package com.example.geektrust.utils;

import com.example.geektrust.entities.Card;

public class ConditionMatched {
    public static boolean conditionMatched(Card cardA, Card cardB) {
        if (cardA.getRank() == cardB.getRank() || cardB.getSuit().equals(cardB.getSuit())) {
            return true;
        }
        return false;

    }

}
