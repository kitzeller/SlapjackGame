package com.kit;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> cardStack;

    public static final int DECK_SIZE = 52;
    public static final String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
    };

    public static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

    public Deck() {
        cardStack = new Stack<>();
    }

    public void shuffle() {
        Collections.shuffle(cardStack);
    }

    public int getSize() {
        return cardStack.size();
    }

    public boolean isEmpty() {
        return cardStack.isEmpty();
    }

    public void pushCard(Card card) {
        cardStack.push(card);
    }

    public Card popCard() {
        if (cardStack.empty()) {
            return null;
        }
        return cardStack.pop();
    }

    public Card peekCard() {
        if (cardStack.empty()) {
            return null;
        }
        return cardStack.peek();
    }

    public void populate() {
        int n = SUITS.length * RANKS.length;
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                Card card = new Card(RANKS[i], SUITS[j]);
                cardStack.push(card);
            }
        }
    }

    public void printDeck() {
        Stack<Card> tempStack = new Stack<>();
        tempStack.addAll(cardStack);

        for (int i = 0; i < tempStack.size(); i++) {
            System.out.println(tempStack.get(i).getName());
        }
    }

    public Stack<Card> getCardStack() {
        return cardStack;
    }

    public void setCardStack(Stack<Card> cardStack) {
        this.cardStack = cardStack;
    }
}
