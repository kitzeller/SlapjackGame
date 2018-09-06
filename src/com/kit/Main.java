package com.kit;

/*
 * Custom "Slapjack"
 *
 * A "standard" deck of playing cards consists of 52 Cards in each of the 4 suits of Spades, Hearts, Diamonds, and Clubs.
  * Each suit contains 13 cards: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King.
 *
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

    public static void main(String[] args) {
        // Custom Slapjack

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Slapjack!");
        System.out.print("How many players? (2 to 4): ");
        int players = scanner.nextInt();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i));
        }

        System.out.println("In this special version of Slapjack, you can choose up to 3 cards to be jacks!");
        List<String> jacks = new ArrayList<>();
        String response;

        do {
            System.out.print("Please type a Rank or 'No' :");

            response = scanner.next();
            for (int i = 0; i < RANKS.length; i++) {
                if (response.equals(RANKS[i])) {
                    jacks.add(response);
                    System.out.println(response + " added!");
                }
            }

        } while (!response.equals("No") && jacks.size() < 3);

        if (response.equals("No") && jacks.isEmpty()) {
            jacks.add("Jack");
        }

        Slapjack game = new Slapjack(players, playerList, jacks);


        System.out.println("Begin!");

        game.play();


    }
}
