package com.kit;

public class Player {

    private Deck hand;
    private final int id;
    boolean eliminated;

    public Player(int id){
        this.id = id;
        this.hand = new Deck();
        this.eliminated = false;
    }

    public void getCard(Card card){
        hand.pushCard(card);
    }

    public Card placeCard(){
        return hand.popCard();
    }

    public int getId() {
        return id;
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public boolean outOfCards() {
        return hand.isEmpty();
    }

    public boolean hasAllCards() {
        return hand.getSize() == 52;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }


    public String getName() {
        switch(id){
            case 0:
                return "You";
            case 1:
                return "Two";
            case 2:
                return "Three";
            case 3:
                return "Four";
            default:
                return "";
        }
    }

    public void shuffleHand(){
        hand.shuffle();
    }
}
