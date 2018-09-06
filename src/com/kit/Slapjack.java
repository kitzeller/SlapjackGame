package com.kit;

import java.util.*;

public class Slapjack {

    /**
     * Game Settings
     */
    final private int playerCount;
    final private List<Player> players;
    private LinkedList<Player> playerOrder;
    private List<String> jacks;
    private Deck mainDeck;

    /**
     * Flags
     */
    private boolean gameOver;
    private boolean eliminated;
    private boolean timerFlag;
    private boolean AIMode;

    /**
     * Helper Variables
     */
    private Scanner scanner;
    private Player currentPlayer;
    private Card card;

    /**
     * @param playerCount the number of players
     * @param players     the list of players
     * @param jacks       the list of special 'Jacks'
     */
    public Slapjack(int playerCount, List<Player> players, List<String> jacks) {
        this.playerCount = playerCount;
        this.jacks = jacks;
        this.mainDeck = new Deck();
        this.gameOver = false;
        this.players = players;
        this.playerOrder = new LinkedList<>();
        this.scanner = new Scanner(System.in);

    }

    /**
     * Play the game
     *
     * @return game ended
     */
    public boolean play() {
        mainDeck.populate();
        System.out.println("Shuffling cards...");
        mainDeck.shuffle();
        System.out.println("Dealing cards...");
        dealCards();

        for (int i = 0; i < playerCount; i++) {
            playerOrder.add(players.get(i));
        }

        System.out.println("Please enter x to place a card and z to slap!");
        card = new Card("dummy", "dummy");
        String input = null;
        timerFlag = false;

        while (!gameOver) {
            while (!isAJack(card.getRank())) {
                if (checkWinningCondition()) return true;
                if (timerFlag) {
                    timerFlag = false;
                } else if (AIMode) {
                    // do nothing
                } else {
                    do {
                        input = scanner.nextLine();
                        if (!input.equals("x") && !input.equals("z")) {
                            System.out.println("Please enter x to place a card or z to snap. " + input);
                        }
                    } while (!input.equals("x") && !input.equals("z"));
                }

                if (input.equals("z")) {
                    if (!players.get(0).outOfCards()) {
                        if (currentPlayer.getId() == 0) {
                            System.out.println("That wasn't a slapjack! You must give a card to each player");
                            for (int i = 1; i < playerCount; i++) {
                                if (!players.get(i).outOfCards()) {
                                    Card toGive = currentPlayer.placeCard();
                                    players.get(i).getCard(toGive);
                                }
                            }
                        } else {
                            System.out.println("That wasn't a slapjack! You must give a card to " + currentPlayer.getName());
                            currentPlayer.getCard(players.get(0).placeCard());
                        }
                        System.out.println("Press x to continue");
                        scanner.nextLine();
                    } else {
                        System.out.println("That wasn't a slapjack! You have no more cards and are eliminated!");
                        if (!promptAIMode()) return true;
                    }
                }

                currentPlayer = getNextPlayer();
                card = currentPlayer.placeCard();

                if (card == null) {
                    System.out.println(currentPlayer.getName() + " ran out of cards!");
                    if (checkWinningCondition()) return true;
                    card = new Card("dummy", "dummy");
                    continue;
                }
                mainDeck.pushCard(card);
                System.out.println(currentPlayer.getName() + " placed " + card.getName());
            }

            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("Too Slow");
                    Random rand = new Random();
                    int n = rand.nextInt(playerCount - 1) + 1;
                    System.out.println(players.get(n).getName() + " took the " + card.getRank());
                    System.out.println(players.get(n).getName() + " gained " + mainDeck.getSize() + " cards");
                    transferMainStack(players.get(n).getId());
                    if (!playerOrder.contains(players.get(n))) {
                        playerOrder.add(players.get(n));
                    }
                    if (players.get(0).outOfCards()) {
                        System.out.println("You are out of cards and didn't manage to slap the jack! Game Over.");
                        eliminated = true;
                    } else {
                        System.out.println("Press x to continue");
                        timerFlag = true;
                    }
                }
            };

            if (!AIMode) {
                Timer timer = new Timer();
                timer.schedule(task, 3 * 1000);
                input = scanner.nextLine();
                timer.cancel();
            }
            if (checkWinningCondition()) return true;
            if (eliminated) {
                if (!promptAIMode()) return true;
            }

            if (AIMode) {
                Random rand = new Random();
                int n = rand.nextInt(playerCount - 1) + 1;
                System.out.println(players.get(n).getName() + " took the " + card.getRank());
                System.out.println(players.get(n).getName() + " gained " + mainDeck.getSize() + " cards");
                transferMainStack(players.get(n).getId());
                if (!playerOrder.contains(players.get(n))) {
                    playerOrder.add(players.get(n));
                }
                if (checkWinningCondition()) return true;
            } else if (timerFlag) {
                // do nothing
            } else {
                if (!input.equals("z")) {
                    Random rand = new Random();
                    int n = rand.nextInt(playerCount - 1) + 1;
                    System.out.println(players.get(n).getName() + " took the " + card.getRank());
                    System.out.println(players.get(n).getName() + " gained " + mainDeck.getSize() + " cards");
                    transferMainStack(players.get(n).getId());
                    if (!playerOrder.contains(players.get(n))) {
                        playerOrder.add(players.get(n));
                    }
                    if (checkWinningCondition()) return true;

                    if (players.get(0).outOfCards()) {
                        System.out.println("You are out of cards and didn't manage to slap the jack! Game Over.");
                        if (!promptAIMode()) return true;
                    }

                } else {
                    System.out.println("You took the " + card.getRank());
                    System.out.println("You gained " + mainDeck.getSize() + " cards");
                    transferMainStack(0);
                    if (checkWinningCondition()) return true;

                }
                System.out.println("Enter x to continue");
            }
            card = new Card("dummy", "dummy");
        }
        return true;
    }


    /**
     * @param rank card rank
     * @return whether or not it is a special card
     */
    private boolean isAJack(String rank) {
        for (int i = 0; i < jacks.size(); i++) {
            if (rank.equals(jacks.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deal the cards
     */
    private void dealCards() {
        int count = 0;
        while (!mainDeck.isEmpty()) {
            Card card = mainDeck.popCard();
            players.get(count).getCard(card);
            count++;
            if (count == playerCount) {
                count = 0;
            }

        }
    }

    /**
     * Prompt User with AI Mode (watch the game after they lose)
     *
     * @return AI Mode enabled
     */
    private boolean promptAIMode() {
        System.out.println("Watch the AI finish the game? Enter 'yes' or 'no'!");
        String input;

        do {
            input = scanner.nextLine();
            if (!input.equals("yes") && !input.equals("no")) {
                System.out.println("Enter 'yes' or 'no'!");
            }
        } while (!input.equals("yes") && !input.equals("no"));

        if (input.equals("yes")) {
            AIMode = true;
            return true;
        } else {
            System.out.println("Thanks for playing. Bye now!");
            return false;
        }
    }

    /**
     * Check if any players have all cards
     *
     * @return if a player has won
     */
    public boolean checkWinningCondition() {
        for (int i = 0; i < playerCount; i++) {
            if (players.get(i).hasAllCards()) {
                System.out.println(players.get(i).getName() + " won!");
                return true;
            }
        }
        return false;
    }

    /**
     * Transfer the contents of the main stack to a player
     *
     * @param playerId the winning player
     */
    private void transferMainStack(int playerId) {
        Player player = players.get(playerId);
        while (!mainDeck.isEmpty()) {
            Card card = mainDeck.popCard();
            player.getCard(card);
        }
        player.shuffleHand();
    }

    /**
     * @return next player in line
     */
    private Player getNextPlayer() {
        Player next = playerOrder.remove();
        if (!next.outOfCards()) {
            playerOrder.add(next);
        }
        return next;
    }
}
