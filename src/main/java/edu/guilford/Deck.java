package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Deck class represents a deck of playing cards.
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random rand = new Random();

    /**
     * Constructs a new deck of 52 cards.
     */
    public Deck() {
        build();
    }

    /**
     * Returns the deck of cards.
     * 
     * @return the deck of cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    /**
     * Clears the deck.
     */
    public void clear() {
        deck.clear();
    }

    /**
     * Builds a new standard deck of 52 cards.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    /**
     * Picks a card from the deck at the specified index.
     * @param i the index of the card to pick
     * @return the card picked
     */
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    /**
     * Deals the top card of the deck.
     * 
     * @return the card dealt
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Returns the number of cards remaining in the deck.
     * 
     * @return the number of cards remaining in the deck
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns a string representation of the deck.
     * 
     * @return a string representation of the deck
     */
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
