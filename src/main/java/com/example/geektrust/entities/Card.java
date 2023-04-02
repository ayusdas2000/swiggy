package com.example.geektrust.entities;

public class Card extends BaseEntity {
    private Integer rank;
    private String suit;

    public Card(String id, Integer rank, String suit) {
        this(rank, suit);
        this.id = id;
    }

    public Card(Integer rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public boolean drawingCondition(Object obj) {
        Card other = (Card) obj;
        if (other.rank == this.rank || other.suit.equals(this.suit)) {
            return true;
        }
        return false;
    }
}
