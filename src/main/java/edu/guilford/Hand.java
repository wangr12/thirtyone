package edu.guilford;

import java.util.ArrayList;

/**
 * The Hand class represents a hand of playing cards.
 */
public class Hand {
    private ArrayList<Card> hand;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Returns the hand of cards.
     * 
     * @return the hand of cards
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     * 
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Resets the hand by removing all cards
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Returns the number of cards in the hand.
     * 
     * @return the number of cards in the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * Returns the card at the specified index.
     * 
     * @param index the index of the card to return
     * @return the card at the specified index
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Calculates hte total value of the hand.
     * 
     * @return the total value of the cards in the hand
     */
    public int getTotalValue() {
        int maxValue = 0;
        int[] values = new int[Card.Suit.values().length];
        for (Card.Suit suit : Card.Suit.values()) {
            values[suit.ordinal()] = 0;
            for (Card card : hand) {
                if (card.getSuit() == suit) {
                    // add the value of the card to the value of the suit
                    switch (card.getRank()) {
                        case ACE:
                            values[suit.ordinal()] += 11;
                            break;
                        case TWO:
                            values[suit.ordinal()] += 2;
                            break;
                        case THREE:
                            values[suit.ordinal()] += 3;
                            break;
                        case FOUR:
                            values[suit.ordinal()] += 4;
                            break;
                        case FIVE:
                            values[suit.ordinal()] += 5;
                            break;
                        case SIX:
                            values[suit.ordinal()] += 6;
                            break;
                        case SEVEN:
                            values[suit.ordinal()] += 7;
                            break;
                        case EIGHT:
                            values[suit.ordinal()] += 8;
                            break;
                        case NINE:
                            values[suit.ordinal()] += 9;
                            break;
                        case TEN:
                        case JACK:
                        case QUEEN:
                        case KING:
                            values[suit.ordinal()] += 10;
                            break;
                    }

                }
            }
        }
        maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * Returns a string representation of the hand.
     * 
     * @return a string representation of the hand
     */
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += "\t" + card.toString() + "\n";
        }
        return handString;
    }

}
