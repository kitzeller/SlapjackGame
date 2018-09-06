package com.kit;

public class Card {

    private String suit;
    private String rank;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return this.rank + " of " + this.suit;
    }

}
