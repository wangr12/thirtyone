package edu.guilford;

import java.util.Queue;
import java.util.Stack;

/**
 * The Player class represents a player in the game.
 */
public class Player {
    private Hand hand;
    private int lives;
    private int playerNum;
    public final int knocklimit = 23; // knock if hand value is 23 or higher

    /**
     * Constructs a Player with the specified player number.
     * 
     * @param playerNum the player number
     */
    public Player(int playerNum) {
        hand = new Hand();
        lives = 3;
        this.playerNum = playerNum;
    }

    /**
     * Returns the hand of the player.
     * 
     * @return the hand of the player
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Returns the number of lives the player has.
     * 
     * @return the number of lives the player has
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns the player number.
     * 
     * @return the player number
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Sets the number of lives the player has.
     * 
     * @param lives the number of lives to set
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Adds a card to the player's hand.
     * 
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.addCard(card);
        
    }

    /**
     * Chooses a card to discard and draws a new card from the stock pile.
     * 
     * @param discard the discard pile
     * @param stock   the stock pile
     */
    public void chooseCard(Stack<Card> discard, Queue<Card> stock) {
        // calculate how many cards of matching suits there are
        int matches = 1;
        if (hand.getCard(0).getSuit() == hand.getCard(1).getSuit()) {
            matches++;
        }
        if (hand.getCard(0).getSuit() == hand.getCard(2).getSuit()) {
            matches++;
        }
        if (matches == 1) {
            if (hand.getCard(1).getSuit() == hand.getCard(2).getSuit()) { // if only 2nd and 3rd card match
                matches++;
            }
        }

        Hand newHand = new Hand();
        // if no cards match & top discard card matches a card in the hand, switch it for the lowest non-matching card
        // if no cards match & top discard card doesn't match any card in the hand, discard lowest card and draw from stock pile
        if (matches == 1) {
            Card.Suit matchingSuit = discard.peek().getSuit();
            if (matchingSuit == hand.getCard(0).getSuit() || matchingSuit == hand.getCard(1).getSuit() || matchingSuit == hand.getCard(2).getSuit()) {
                newHand.addCard(discard.pop());
                for (int i = 0; i < hand.size(); i++) {
                    if (hand.getCard(i).getSuit() == matchingSuit) {
                        newHand.addCard(hand.getCard(i));
                        hand.removeCard(hand.getCard(i));
                        break;
                    }
                }
                if (hand.getCard(0).compareTo(hand.getCard(1)) > 0) {
                    newHand.addCard(hand.getCard(0));
                    hand.removeCard(hand.getCard(0));
                } else {
                    newHand.addCard(hand.getCard(1));
                    hand.removeCard(hand.getCard(1));
                }
                discard.push(hand.getCard(0));
                hand = newHand;
                return;
            }
            else {
                Card lowestCard = hand.getCard(0);
                if (hand.getCard(1).compareTo(lowestCard) < 0) {
                    lowestCard = hand.getCard(1);
                }
                if (hand.getCard(2).compareTo(lowestCard) < 0) {
                    lowestCard = hand.getCard(2);
                }
                discard.push(lowestCard);
                hand.removeCard(lowestCard);
                hand.addCard(stock.poll());
                return;
            }
        }

        // 2 cards match: discard the non-matching card if value is less than sum of other 2 cards
        // if discard matches suit, pick up card. if not, draw from stock pile
        if (matches == 2) {
            Card removedCard = null;
            if (hand.getCard(0).getSuit() == hand.getCard(1).getSuit()) {
                removedCard = hand.getCard(2);
                hand.removeCard(removedCard);
            }
            else if (hand.getCard(0).getSuit() == hand.getCard(2).getSuit()) {
                removedCard = hand.getCard(1);
            }
            else {
                removedCard = hand.getCard(0);
            }
            hand.removeCard(removedCard);


            if (discard.peek().getSuit() == hand.getCard(0).getSuit()) {
                hand.addCard(discard.pop());
            }
            else {
                hand.addCard(stock.poll());
            }
            discard.push(removedCard);
            return;
        }


        // 3 cards match: take lowest card
        // if discard matches suit, pick up card. if not, draw from stock pile
        if (matches == 3) {
            Card lowestCard = hand.getCard(0);
            if (hand.getCard(1).compareTo(lowestCard) < 0) {
                lowestCard = hand.getCard(1);
            }
            if (hand.getCard(2).compareTo(lowestCard) < 0) {
                lowestCard = hand.getCard(2);
            }
            hand.removeCard(lowestCard);

            if (discard.peek().getSuit() == hand.getCard(0).getSuit()) {
                hand.addCard(discard.pop());
            }
            else {
                hand.addCard(stock.poll());
            }
            discard.push(lowestCard);
            return;
        }

        
    }

    /**
     * Returns a string representation of the player.
     * 
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        return "Player " + (playerNum + 1) + ":\n" + hand + "\tLives: " + lives + "\n\tScore: " + hand.getTotalValue();
    }

}
