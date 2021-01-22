package Game;

import java.io.Serializable;
import java.util.ArrayList;


public class Game  implements Serializable{

   private static final long serialVersionUID = -5785154736259821702L;
   private ArrayList<Player> players;

   Menu menu = new Menu();
   Store store = new Store();
   Breed breed = new Breed();
   Feed feed = new Feed();

   private int numOfRounds;
   private int round = 1;
   private Player currentPlayer;

   public Game() {
      menu.setGame(this);
      store.setGame(this);
      breed.setGame(this);
      feed.setGame(this);
      menu.mainMenu();
      for (Player player : players) {
         player.game = this;
      }
      currentPlayer = players.get(0);
      newGame();
   }

   public void newGame(){
      while (round < numOfRounds) {
         menu.gameMenu(currentPlayer);
         if (currentPlayer.roundDone) {
            changePlayer();
         }
      }
      pokemonAgeOrSell(true);
      printAllScores();
      System.out.println("The winner is " + getWinner().getName());
   }

   public void setNumOfRounds(int numOfRounds) {
      this.numOfRounds = numOfRounds;
   }

   public void setPlayers(ArrayList<Player> players) {
      this.players = players;
   }

   public int getRound() {
      return round;
   }

   public int getNumOfRounds() {
      return numOfRounds;
   }

   private void playerLost() {
      for (int i = players.size() - 1; i > 0; i--) {
         if (players.get(i).getPlayerPokemon().size() == 0 && players.get(i).getMoney() < 500) {
            System.out.println(players.get(i).getName() + " ran out of Pokemon and money and is ELIMINATED");
            players.remove(i);
            GameHelper.inputEnter();
         }
      }
   }

   public void changePlayer() {
      GameHelper.clearScreen();
      System.out.println("Next player");
      GameHelper.inputEnter();
      // If the player is last in turn, then start over with player 1
      if (players.indexOf(currentPlayer) == players.size() - 1) {
         playerLost();
         currentPlayer = players.get(0);
         currentPlayer.accessShops(true);
         currentPlayer.roundDone = false;
         pokemonAgeOrSell(false);
         round++;
      } else {
         currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
         currentPlayer.accessShops(true);
         currentPlayer.roundDone = false;
      }
      getSick(currentPlayer);
   }

   // Reverse loop, needed to remove pokemon from it
   private void pokemonAgeOrSell(boolean sellAll) {
      for (Player player : players) {
         for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
            if (sellAll) {
               player.sellPokemon(i);
               GameHelper.inputEnter();
            } else {
               player.getPokemon(i).aging();
               player.getPokemon(i).reduceHealth();
               if (player.getPokemon(i).getAge() > player.getPokemon(i).getMaxAge()) {
                  System.out.println(player.getName() + "! " + player.getPokemon(i).getName() + " died of old age");
                  player.getPlayerPokemon().remove(i);
                  GameHelper.inputEnter();
               } else if (player.getPokemon(i).getHealth() <= 0) {
                  System.out.println(player.getName() + "! " + player.getPokemon(i).getName()
                        + " died because health dropped below 0");
                  player.getPlayerPokemon().remove(i);
                  GameHelper.inputEnter();
               }
            }
         }
      }
   }

   public void getSick(Player currentPlayer) {

      for (int i = currentPlayer.getPlayerPokemon().size() - 1; i > 0; i--) {
         if (Math.random() < 0.2) {
            currentPlayer.getPokemon(i).isSick = false;
            System.out
                  .println(currentPlayer.getPokemon(i).toString(false) + "Got sick, pay the vet or let Pokemon die?");
            System.out.println("Cost: 300");
            System.out.println("\n[y / n]");
            String s = GameHelper.validateString();
            if (s.equals("n")) {
               currentPlayer.getPlayerPokemon().remove(i);
               System.out.println("DIED");
               GameHelper.inputEnter();
            } else {
               currentPlayer.handlePurchase(300);
            }
         }
      }
   }

   public void printAllScores() {
      for (Player player : this.players) {
         System.out.println(player.getName() + "\t\tgot:" + player.getMoney());
      }
   }

   public Player getWinner() {
      int bestScore = 0;
      int bestIndex = 0;
      for (int i = 0; i < players.size(); i++) {
         if (players.get(i).getMoney() > bestScore) {
            bestScore = players.get(i).getMoney();
            bestIndex = i;
         }
      }
      return players.get(bestIndex);
   }



}