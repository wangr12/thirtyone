package edu.guilford;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * The ThirtyOne class represents the game of Thirty-One.
 */
public class ThirtyOne {
    private Player[] players;
    private Deck deck;
    private Stack<Card> discardPile;
    private Queue<Card> stockPile = new LinkedList();
    private Random rand;

    /**
     * Constructs a new game of Thirty-One with the specified number of players.
     * 
     * @param numPlayers the number of players
     */
    public ThirtyOne(int nPlayers) {
        players = new Player[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            players[i] = new Player(i);
        }
        build();
    }

    /**
     * Constructs a new game of Thirty-One with the specified array of players.
     * 
     * @param players
     */
    public ThirtyOne(Player[] players) {
        this.players = players;
        for (Player player : players) {
            player.getHand().reset();
        }
        build();
    }

    /**
     * Initializes a new deck, discardPile, and stockPile, shuffles the deck, and
     * deals cards to the players.
     */
    public void build() {
        deck = new Deck();
        discardPile = new Stack<Card>();
        stockPile = new LinkedList<Card>();
        deck.shuffle();
        deal();
        discardPile.add(stockPile.poll());
    }
    
    /**
     * Returns the players in the game.
     * @return the players in the game
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Returns the deck of cards.
     * @return the deck of cards
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Returns the discard pile.
     * @return the discard pile
     */
    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    /**
     * Returns the stock pile.
     * @return the stock pile
     */
    public Queue<Card> getStockPile() {
        return stockPile;
    }

    /**
     * Removes a player from the game.
     * @param index the index of the player to remove
     */
    public void removePlayer(int index) {
        Player[] newPlayers = new Player[players.length - 1];
        for (int i = 0; i < index; i++) {
            newPlayers[i] = players[i];
        }
        for (int i = index; i < newPlayers.length; i++) {
            newPlayers[i] = players[i + 1];
        }
        players = newPlayers;
    }

    /**
     * Deals cards to the players and sets up the stock piles.
     */
    public void deal() {
        for (int i = 0; i < 3; i++) {
            for (Player player : players) {
                player.getHand().addCard(deck.deal());
            }
        }
        // add rest of cards to stock pile
        while (deck.size() > 0) {
            stockPile.add(deck.deal());
        }
    }

    /**
     * Plays a turn for the specified player.
     * @param player the player whose turn it is
     * @param firstTurn true if it is the first turn, false otherwise
     * @return true if the player knocks, false otherwise
     */
    public boolean turn(Player player, boolean firstTurn) {
        if (stockPile.isEmpty()) {
            while (!discardPile.isEmpty()) {
                stockPile.add(discardPile.pop());
            }
            discardPile.add(stockPile.poll());
        }
        // check if player has 31
        if (player.getHand().getTotalValue() == 31) {
            return true;
        }

        // decide if player knocks
        if (!firstTurn && player.getHand().getTotalValue() >= player.knocklimit) {
            return true;
        }
        else {
            player.chooseCard(discardPile, stockPile);
        }
        return false;
    }

    /**
     * Ends the round by giving each player one more turn and determining the player with the lowest score.
     * @param knocked the player number of the player who knocked
     */
    public void endRound(int knocked) {
        // each player gets 1 more turn
        for (Player player : getPlayers()) {
            if (player.getPlayerNum() != knocked) {
                turn(player, true);
            }
        }

        int lowestScore = getPlayers()[0].getHand().getTotalValue();
        for (int i = 1; i < players.length; i++) {
          int score = getPlayers()[i].getHand().getTotalValue();
          if (score < lowestScore) {
            lowestScore = score;
          }
        }

        for (Player player : getPlayers()) {
            if (player.getHand().getTotalValue() == lowestScore) {
              System.out.println("Player " + (player.getPlayerNum() + 1) + " loses a life");
              if (player.getPlayerNum() != knocked) {
                player.setLives(player.getLives() - 1);
              } else {
                player.setLives(player.getLives() - 2);
              }
            }
          }
    }
}
