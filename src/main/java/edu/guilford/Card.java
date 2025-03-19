package edu.guilford;

import java.util.Random;

/**
 * The Card class represents a playing card with a suit and rank.
 */
public class Card implements Comparable<Card> {
    /**
     * The Suit enum represents the four suits of a standard deck of cards.
     */
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    /**
     * The Rank enum represents the thirteen ranks of a standard deck of cards.
     */
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING, ACE

    }

    // instance variables
    private Suit suit;
    private Rank rank;

    public static final int SORT_BY_SUIT = 1;
    public static final int SORT_BY_RANK = 2;
    private static int sortMethod = SORT_BY_RANK;

    /**
     * Constructs a Card with the specified suit and rank.
     * 
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Constructs a random Card.
     */
    public Card() {
        // random Card
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Returns the suit of the card.
     * 
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of the card.
     * 
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the sort method for comparing cards.
     * 
     * @param sortMethod the sort method to set (SORT_BY_SUIT or SORT_BY_RANK)
     */
    public static void setSortMethod(int sortMethod) {
        Card.sortMethod = sortMethod;
    }

    /**
     * Returns a string representation of the card.
     * 
     * @return a string representation of the card
     */
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Compares this card with another card for order.
     * 
     * @param otherCard the other card to be compared
     * @return -1 if this card is less than the other card, 0 if they are equal,
     *         1 if this card is greater than the other card
     */
    @Override
    public int compareTo(Card otherCard) {
        if (sortMethod == SORT_BY_SUIT) {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            } else {
                if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                    return 1;
                } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                    return -1;
                }
            }
        } else {
            if (this.rank.ordinal() > otherCard.rank.ordinal()) {
                return 1;
            } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
                return -1;
            } else {
                if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                    return 1;
                } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                    return -1;
                }
            }
        }

        return 0;
    }

}