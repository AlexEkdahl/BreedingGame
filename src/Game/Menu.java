package Game;

import java.util.ArrayList;

public class Menu {

   Game game;

   public void setGame(Game game) {
      this.game = game;
   }

   public void mainMenu() {
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("MAIN MENU");
      System.out.println("[1] New game");
      System.out.println("[2] How to play");
      System.out.println("\n[0]. Exit game");
      // TODO Load from saved file

      mainMenuChoice(GameHelper.getInt(0, 2));
   }

   public void gameMenu(Player player) {
      playerDisplay(player);
      System.out.println("\tGAME MENU");
      System.out.println("[1] Buy Pokemon");
      System.out.println("[2] Buy food");
      System.out.println("[3] Feed Pokemon");
      System.out.println("[4] Breed Pokemon");
      System.out.println("[5] Sell Pokemon");
      System.out.println("\n[9] -Save Game- ");
      System.out.println("[0] - Finish round");
      gameMenuChoice(GameHelper.getInt(0, 5), player);
   }

   // TODO
   public void playerDisplay(Player player) {
      GameHelper.clearScreen();
      System.out.println(player.getName());
      System.out.println("Money: " + player.getMoney());
      System.out.println("Round: ");
      System.out.println();
      System.out.println("POKEMON");
      System.out.println("POKEMON AGE");
      System.out.println("POKEMON HEALTH");
      System.out.println();

      System.out.println("\n".repeat(3));
   }

   public void feedPokemon(Player player) {
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

   public void howToPlay() {
      GameHelper.clearScreen();
      System.out
            .println("===== HOW TO PLAY =====\n" + "* This is a turn based game were players take tuns on setting up\n"
                  + "* their Pokemon for success. Players choose from buying or selling\n"
                  + "* Pokemon, buying food or try to breed. Each Pokemon have a different\n"
                  + "* value. When the last turn is finished the players automatically sell\n"
                  + "* their Pokemon and the winner is the one with most money left.");
      GameHelper.inputEnter();
      mainMenu();
   }

   public void exitGame() {
      System.out.println("Bye bye");
      System.exit(0);
   }

   // TODO refactor
   public void newGameMenu() {
      ArrayList<Player> players = new ArrayList<>();
      int numOfPlayers;
      int numOfRounds;
      int startingMoney;
      System.out.println("How many players: ");
      numOfPlayers = GameHelper.getInt(1, 4);
      System.out.println("How many rounds: ");
      numOfRounds = GameHelper.getInt(5, 35);
      System.out.println("Starting money: ");
      System.out.println("[EASY][MEDIUM][HARD]");
      System.out.println("[$$$][$$][$]");
      startingMoney = GameHelper.getInt(100, 5000);

      for (int i = 1; i <= numOfPlayers; i++) {
         System.out.printf("Player %d name:\n", i);
         players.add(new Player(GameHelper.input.nextLine(), startingMoney));
      }
      game.setNumOfRounds(numOfRounds);
      game.setPlayers(players);
   }

   public void mainMenuChoice(int choice) {
      switch (choice) {
         case 1 -> newGameMenu();
         case 2 -> howToPlay();
         case 0 -> exitGame();
         // TODO Load from saved file
      }
   }

   // !
   public void gameMenuChoice(int choice, Player player) {
      switch (choice) {
         case 1 -> game.store.displayPokemon(player);
         case 2 -> game.store.displayFood(player);
         case 3 -> game.feed.feedPokemon(player);
         case 4 -> game.breed.printAvailablePokemon(player);
         case 5 -> game.store.sellPokemon(player);
         // case 9 -> //save game
         // case 0 -> //finish round
      }
   }

   public void accessThisStore(String storeName, Player player) {
      switch (storeName) {
         case "buyPokemon":
            if (!player.canBuyPokemon()) {
               choiceMade(player);
            }
            break;
         case "buyFood":
            if (!player.canBuyFood()) {
               choiceMade(player);
            }
            break;
         case "breedPokemon":
            if (!player.canBreed()) {
               choiceMade(player);
            }
            break;
         case "sellPokemon":
            if (!player.canSellPokemon()) {
               choiceMade(player);
            }
            break;
         case "feedPokemon":
            choiceMade(player);
      }
   }

   private void choiceMade(Player player) {
      if (player.canBreed()) {
         System.out.println("You made your choice of breeding this round");
         GameHelper.inputEnter();
         //!
         gameMenu(player);
      } else if (player.canBuyFood()) {
         System.out.println("You made your choice, you can only buy food this round");
         GameHelper.inputEnter();
         //!
         gameMenu(player);
      } else if (player.canBuyPokemon()) {
         System.out.println("You made your choice, you can only buy Pokemon this round");
         GameHelper.inputEnter();
         //!
         gameMenu(player);
      } else if (player.canSellPokemon()) {
         System.out.println("You made your choice, you can only sell Pokemon this round");
         GameHelper.inputEnter();
         //!
         gameMenu(player);
      } else if (player.canFeedPokemon()) {
         System.out.println("You made your choice, you can only feed your Pokemon this round");
         GameHelper.inputEnter();
         //!
         gameMenu(player);
      }
   }

}
