package edu.guilford;

public class ThirtyOneDriver {
  public static void main(String[] args) {
    int nplayers = 4;
    ThirtyOne game = new ThirtyOne(nplayers);

    printGameState(game);
    for (Player player : game.getPlayers()) {
      game.turn(player, true);
    }

    boolean knocked = false;
    boolean gameOver = false;
    while (!gameOver) {
      while (!knocked) {
        printGameState(game);
        for (Player player : game.getPlayers()) {
          knocked = game.turn(player, false);
          if (knocked) {
            System.out.println("Player " + (player.getPlayerNum() + 1) + " knocked!");
            game.endRound(player.getPlayerNum());
            break;
          }
        }
      }
      printGameState(game);

      // remove any players with 0 lives
      int i = 0;  
      while (i < nplayers) {  
        if (game.getPlayers()[i].getLives() <= 0) {
          System.out.println("Player " + (game.getPlayers()[i].getPlayerNum() + 1) + " has been eliminated");
          nplayers--;
          game.removePlayer(i);
        } else {  
          i++;  
        }
      }
      if (nplayers == 1) {
        System.out.println("Player " + (game.getPlayers()[0].getPlayerNum() + 1) + " wins!");
        gameOver = true;
        break;
      }
      if (nplayers == 0) {
        System.out.println("All players have been eliminated!");
        gameOver = true;
        break;
      }

      if (!gameOver) {
        game = new ThirtyOne(game.getPlayers());
        knocked = false;
      }
    }

  }

  // method to print the game state
  public static void printGameState(ThirtyOne game) {
    for (Player player : game.getPlayers()) {
      System.out.println(player);
    }
    System.out.println("Discard Pile: " + game.getDiscardPile());
    System.out.println("Stock Pile: " + game.getStockPile() + "\n");
  }
}