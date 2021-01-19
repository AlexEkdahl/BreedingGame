package Game;

import java.util.*;
import Game.FoodClasses.Food;
import Game.PokemonClasses.Pokemon;

public class Menu {

   Game game;

   public void setGame(Game game) {
      this.game = game;
   }

   protected void mainMenu() {
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("\n[1] New game");
      System.out.println("[2] How to play");
      System.out.println("\n[0]. Exit game");
      // TODO Load from saved file

      mainMenuChoice(GameHelper.getInt(0, 2));
   }

   protected void gameMenu(Player player) {
      playerDisplay(player);
      System.out.println("===== GAME MENU =====");
      System.out.println("[1] Buy Pokemon");
      System.out.println("[2] Buy food");
      System.out.println("[3] Feed Pokemon");
      System.out.println("[4] Breed Pokemon");
      System.out.println("[5] Sell Pokemon");
      System.out.println("\n[9] -Save Game- ");
      System.out.println("[0] - Finish round");
      gameMenuChoice(GameHelper.getInt(0, 5), player);
   }

   protected void playerDisplay(Player player) {
      GameHelper.clearScreen();
      System.out.println(player.getName());
      System.out.println("Money: " + player.getMoney());
      System.out.println("Round: " + game.getRound());
      System.out.println();
      if (player.getPlayerPokemon().size() != 0) {
         pokeDisplay(player);
      }
      if (player.getPlayerFood().size() != 0) {
         foodDisplay(player);
      }
      System.out.println("\n".repeat(3));
   }

   protected void feedPokemon(Player player) {
      playerDisplay(player);
      int choice = 0;
      System.out.println("===== Feed your Pokemon =====");
      player.printPokemonList();
      System.out.println("\n[0] Exit to game menu");
      choice = GameHelper.getInt(0, player.getPlayerPokemon().size());
      if (choice == 0) {
         gameMenu(player);
      } else {
         playerDisplay(player);
         player.feedPokemon(choice);
      }

   }

   private void howToPlay() {
      GameHelper.clearScreen();
      System.out.println(
            "===== HOW TO PLAY =====\n\n" + "* This is a turn based game were players take tuns on setting up\n"
                  + "* their Pokemon for success. Players choose from buying or selling\n"
                  + "* Pokemon, buying food or try to breed. Each Pokemon have a different\n"
                  + "* value. When the last turn is finished the players automatically sell\n"
                  + "* their Pokemon and the winner is the one with most money left.");
      GameHelper.inputEnter();
      mainMenu();
   }

   private void exitGame() {
      System.out.println("Bye bye");
      System.exit(0);
   }

   private void newGameMenu() {
      ArrayList<Player> players = new ArrayList<>();
      int numOfPlayers;
      int numOfRounds;
      int startingMoney;
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("\nHow many players:");
      numOfPlayers = GameHelper.getInt(1, 4);
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("\nHow many rounds: ");
      numOfRounds = GameHelper.getInt(5, 35);
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("\nStarting money");
      System.out.println("\n  [EASY]   [MEDIUM]    [HARD]");
      System.out.println("[5 0 0 0]  [2 5 0 0]  [1 1 0 0]");
      startingMoney = GameHelper.getInt(1100, 5000);
      for (int i = 1; i <= numOfPlayers; i++) {
         GameHelper.clearScreen();
         System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
         System.out.printf("\nPlayer %d name:\n", i);
         players.add(new Player(GameHelper.input.nextLine(), startingMoney));
      }
      game.setNumOfRounds(numOfRounds);
      game.setPlayers(players);
   }

   private void mainMenuChoice(int choice) {
      switch (choice) {
         case 1 -> newGameMenu();
         case 2 -> howToPlay();
         case 0 -> exitGame();
         // TODO Load from saved file
      }
   }

   private void gameMenuChoice(int choice, Player player) {
      switch (choice) {
         case 1 -> game.store.displayPokemon(player);
         case 2 -> game.store.displayFood(player);
         case 3 -> game.feed.feedPokemon(player);
         case 4 -> {
            if (player.canBreed())
               game.breed.printAvailablePokemon(player);
            else choiceMade(player);
         }
         case 5 -> game.store.sellPokemon(player);
         // case 9 -> //save game
         case 0 -> player.setRoundDone(true);

      }
   }

   public void choiceMade(Player player) {
      if (player.canBuyFood()) {
         System.out.println("You made your choice, you can only buy food this round");
         GameHelper.inputEnter();
      } else if (player.canBuyPokemon()) {
         System.out.println("You made your choice, you can only buy Pokemon this round");
         GameHelper.inputEnter();
      } else if (player.canSellPokemon()) {
         System.out.println("You made your choice, you can only sell Pokemon this round");
         GameHelper.inputEnter();
      } else if (player.canFeedPokemon()) {
         System.out.println("You made your choice, you can only feed your Pokemon this round");
         GameHelper.inputEnter();
      } else {
         System.out.println("You made your choice, you can only try or succeed breeding your Pokemon once per round");
         GameHelper.inputEnter();
      }
   }

   private void pokeDisplay(Player player) {
      System.out.println("===== POKEMON =====");
      for (Pokemon pokemon : player.getPlayerPokemon()) {
         System.out
               .println(pokemon.getName() + "\tage: " + pokemon.getAge() + "\thealth: " + pokemon.getHealth() + "%");
      }
   }

   private void foodDisplay(Player player) {
      System.out.println("===== FOOD =====");
      for (Map.Entry<Food, Integer> entry : player.getPlayerFood().entrySet()) {
         System.out.print(entry.getKey().getType() + ": " + entry.getValue() + "st\t");

      }
   }

}
