# Slapjack Game Design Document


**1. Instructions for running your code and any tests you may have written**

To run the code, simply run the main() function and follow the terminal prompts!
I also created some basic JUnit tests to run a couple scenarios and assert that the game ends properly. These can be run in the SlapjackTest class.

**2. Rules for your card game, if not one of the three listed above**

The player to the left of the dealer goes first and play continues clockwise from there. In turn, each player takes the top card from his pile and places it, face up, in the middle of the table.

When moving a card from his pile to the middle of the table, each player should do so by turning the card away from himself. This ensures that the player does not see the card before his opponents have a chance to do the same. (The player should also turn the card quickly so that he’s not giving himself a disadvantage.)

When the card placed in the middle of the table is a Jack, players race to be the first to slap a hand on top of the Jack. The first player to do so wins the entire pile of cards. That player collects the cards and adds them to his pile. He then shuffles the entire pile and sets it down in front of him.

There are a number of specific rules related to slapping:

- When more than one player slaps at a Jack, the player whose hand is lowest on the pile wins.
- When a player slaps any card other than a Jack, he gives one card from his own supply, face down, to the opponent who played the card that fooled him.
- When a player runs out of cards, he remains in the game until the next Jack is revealed. If the player with no cards is the first to slap the Jack, he wins the pile of cards and remains in the game. If he is not first, however, he is eliminated from the game.

(https://www.thesprucecrafts.com/slapjack-rules-card-game-411142)

**3. A brief explanation of your design choices and any data structures or algorithms that you implemented**

I decided to develop Slapjack, one of my favroite card games growing up. I used object-orientated design principles to create eat class, splitting the game into Card, Deck, Player, Slapjack objects and then the Main game runner.

For the implemetenting the Deck, a Stack structure was naturally fitting, as cards are dealt to each player from the top of the 'Main Deck' stack, and players place cards in the middle from the top of their own hands.
For managing player turns, I implemeted a Linked List as a Queue. 

**4. Choice of tooling (language, libraries, test runner, etc.) and rationale behind those choices**

I decided to implement the game in Java, as it is one of my favorite languages and the language I have the most experience with. I didn't use any libraries besides the standard java.util.* collections framework.
I used JUnit for testing, as it is the testing framework I am most familiar with.

If I had more than 3 hours I would have liked to implement a better Input/Output method that would allow for cleaner JUnit testing, rather than my quick fix of redirecting the input stream. I would also implement an algorithm for AI reaction time to make the game easier or more difficult depending on the user's reaction time.

