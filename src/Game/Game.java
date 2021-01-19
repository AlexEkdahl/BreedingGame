package Game;

import java.util.ArrayList;


public class Game {

   private ArrayList<Player> players;

   Menu menu = new Menu();
   Store store = new Store();
   Breed breed = new Breed();
   Feed feed = new Feed();

   private int numOfRounds;
   private int round = 1;
   private Player currentPlayer;

   public Game() {

   }

   public void newGame() {
      menu.setGame(this);
      store.setGame(this);
      breed.setGame(this);
      feed.setGame(this);

      menu.mainMenu();
      for (Player player : players) {
         player.game = this;
      }
      currentPlayer = players.get(0);
      while (round < numOfRounds) {
         menu.gameMenu(currentPlayer);
         if (currentPlayer.getRoundDone()) {
            changePlayer();
         }
      }
      pokemonAgeOrSell(true);
      printAllScores();
      System.out.println("Winner is " + getWinner().getName());

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

   private boolean playerLost() {
      return (currentPlayer.getPlayerPokemon() == null && currentPlayer.getMoney() < 500) ? true : false;

   }

   public void changePlayer() {
      GameHelper.clearScreen();
      System.out.println("Next player");
      GameHelper.inputEnter();
      // If the player is last in turn, then start over with player 1
      if (players.indexOf(currentPlayer) == players.size() - 1) {
         currentPlayer = players.get(0);
         currentPlayer.accessShops(true);
         currentPlayer.setRoundDone(false);
         pokemonAgeOrSell(false);
         round++;
      } else {
         currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
         currentPlayer.accessShops(true);
         currentPlayer.setRoundDone(false);
      }
   }

   // TODO add sickness
   // Reverse loop, needed to remove pokemon from it
   // Ageing and health reduce
   private void pokemonAgeOrSell(boolean sellAll) {
      for (Player player : players) {
         for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
            if (sellAll) {
               player.sellPokemon(i);
               GameHelper.inputEnter();
            } else {
               player.getPokemon(i).aging();
               player.getPokemon(i).reduceHealth();
               if (player.getPokemon(i).getAge() > player.getPokemon(i).getMaxAge()
                     || player.getPokemon(i).getHealth() <= 0) {
                  System.out.println(player.getName() + "! " + player.getPokemon(i).getName() + " died");
                  player.getPlayerPokemon().remove(i);
                  GameHelper.inputEnter();
               }
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