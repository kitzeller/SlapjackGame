/*
 * Karitta Christina Zellerbach
 * September 6th 2018
 *
 * Custom "Slapjack"
 *
 *
 */

package com.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Slapjack!");
        System.out.print("How many players? (2 to 4): ");
        int players = scanner.nextInt();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i));
        }

        System.out.println("In this special version of Slapjack, you can choose up to 3 cards to be jacks!");
        System.out.println("The ranks are Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, and King.");
        List<String> jacks = new ArrayList<>();
        String response;
        boolean added;

        do {
            added = false;
            System.out.print("Please type a Rank or 'No':");
            response = scanner.next();
            for (int i = 0; i < RANKS.length; i++) {
                if (response.equals(RANKS[i])) {
                    jacks.add(response);
                    System.out.println(response + " added!");
                    added = true;
                }
            }
            if (!added) System.out.println("Please check the spelling! ");

        } while (!response.equals("No") && jacks.size() < 3);

        if (response.equals("No") && jacks.isEmpty()) {
            jacks.add("Jack");
        }

        Slapjack game = new Slapjack(players, playerList, jacks);
        System.out.println("Begin!");
        game.play();

    }
}
