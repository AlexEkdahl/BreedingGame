package Game;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import Game.FoodClasses.Food;
import Game.PokemonClasses.Pokemon;

public class Menu implements Serializable {
   public boolean readHowTo = false;
   Game game;

   public void setGame(Game game) {
      this.game = game;
   }

   protected void mainMenu() {
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      if (readHowTo) {
         System.out.println("\n[" + PrintColors.ANSI_GREEN + 1 + PrintColors.ANSI_RESET + "]" + PrintColors.ANSI_GREEN
               + " New game" + PrintColors.ANSI_RESET);
      } else {
         System.out.println("\n[1] New game");
      }
      if (readHowTo) {
         System.out.println("[2] How to play");

      } else {
         System.out.println("[" + PrintColors.ANSI_YELLOW + 2 + PrintColors.ANSI_RESET + "]" + PrintColors.ANSI_YELLOW
               + " How to play" + PrintColors.ANSI_RESET);
      }
      System.out.println("\n[3] Load game");
      System.out.println("\n[0] Exit game");
      mainMenuChoice(GameHelper.getInt(0, 3));
   }

   protected void gameMenu(Player player) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      playerDisplay(player);
      System.out.println("===== GAME MENU =====");
      System.out.println("[1] Buy Pokemon");
      System.out.println("[2] Buy food");
      System.out.println("[3] Feed Pokemon");
      System.out.println("[4] Breed Pokemon");
      System.out.println("[5] Sell Pokemon");
      System.out.println("\n[8] Turn off/on music");
      System.out.println("[9] Save Game");
      System.out.println("[0] - Finish round");
      gameMenuChoice(GameHelper.getInt(0, 9), player);
   }

   protected void playerDisplay(Player player) {
      GameHelper.clearScreen();
      System.out.println(PrintColors.ANSI_RED + player.getName() + PrintColors.ANSI_RESET);
      System.out.println("Money: " + player.getMoney());
      System.out.println("Round: " + game.getRound() + "/" + game.getNumOfRounds());
      System.out.println();
      if (player.getPlayerFood().size() != 0) {
         foodDisplay(player);
      }
      if (player.getPlayerPokemon().size() != 0) {
         pokeDisplay(player);
      }
      System.out.println();
   }

   private void howToPlay() {
      GameHelper.clearScreen();
      readHowTo = true;
      System.out.println(
            "===== HOW TO PLAY =====\n\n" + "* This is a turn based game were players take tuns on setting up\n"
                  + "* their Pokemon for success. Players choose from buying or selling\n"
                  + "* Pokemon, buying food or try to breed. Each Pokemon have a different\n"
                  + "* value. Dont forget that different Pokemon eat different food.\n"
                  + "* When the last turn is finished the players automatically sell\n"
                  + "* their Pokemon and the winner is the one with most money left.\n\n"
                  + "* A player can only make a action in one of the categories per round.\n"
                  + "* But is able eg. to buy multiple Pokemon per round.\n\n"
                  + "* A player that is unable to buy any Pokemon due to shortage of money and\n"
                  + "* dont have any pokemon in their party is eliminated.\n"
                  + "* Each round your Pokemon get 1 year older and is loosing a percentage\n"
                  + "* of health. If you are unlucky one of your Pokemon get sick....");
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
      System.out.println("\nHow many players (1-4):");
      numOfPlayers = GameHelper.getInt(1, 4);
      GameHelper.clearScreen();
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("\nHow many rounds (5-35): ");
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
         case 3 -> loadSavedFile();
         case 0 -> exitGame();
      }
   }

   private void gameMenuChoice(int choice, Player player)
         throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      switch (choice) {
         case 1 -> {
            if (player.canBuyPokemon)
               game.store.displayPokemon(player);
            else {
               playerDisplay(player);
               choiceMade(player);
            }
         }
         case 2 -> {
            if (player.canBuyFood)
               game.store.displayFood(player);
            else {
               playerDisplay(player);
               choiceMade(player);
            }
         }
         case 3 -> {
            if (player.canFeedPokemon)
               game.feed.feedPokemon(player);
            else {
               playerDisplay(player);
               choiceMade(player);
            }
         }
         case 4 -> {
            if (player.canBreed)
               game.breed.printAvailablePokemon(player);
            else {
               playerDisplay(player);
               choiceMade(player);
            }
         }
         case 5 -> {
            if (player.canSellPokemon)
               game.store.sellPokemon(player);
            else {
               playerDisplay(player);
               choiceMade(player);
            }
         }
         case 8 -> Audio.dummyMethod();
         case 9 -> newSaveFile();
         case 0 -> player.roundDone = true;
      }
   }

   private void newSaveFile() {
      File f = new File("SaveFiles/");
      System.out.println("Enter name on savefile:");
      String fileName = GameHelper.input.nextLine() + ".ser";
      if (!Files.exists(Paths.get("SaveFile/" + fileName))) {
         if (!f.exists()) {
            f.mkdir();
         }
         Serializer.serialize("SaveFiles/" + fileName, game);
      } else {
         System.out.println("Filename already exist.");
         System.out.println("[1] Overwrite existing savefile\n" + "[2] Create a new one");
         if (GameHelper.getInt(1, 2) == 1) {
            Serializer.serialize("SaveFile/" + fileName, game);
         }
      }
   }

   public void loadSavedFile() {
      String[] saveFiles;
      File f = new File("SaveFiles/");
      FilenameFilter filter = new FilenameFilter() {
         @Override
         public boolean accept(File f, String name) {
            return name.endsWith(".ser");
         }
      };
      saveFiles = f.list(filter);
      if (saveFiles.length != 0) {
         GameHelper.clearScreen();
         System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
         System.out.println("\nSaved files\n");
         int n = 1;
         for (String save : saveFiles) {
            System.out.println("[" + n + "] " + save.replaceAll(".ser", ""));
            n++;
         }
         int choice = GameHelper.getInt(1, saveFiles.length);
         String saveString = "SaveFiles/" + saveFiles[choice - 1];
         try {
            this.game = (Game) Serializer.deserialize(saveString);
            game.newGame();
         } catch (Exception error) {
            System.out.println(error);
         }
      }
      System.out.println("No save files");
      GameHelper.inputEnter();
      mainMenu();
   }

   protected void choiceMade(Player player)
         throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      if (player.canBuyFood) {
         System.out.println("You made your choice, you can only buy food this round");
      } else if (player.canBuyPokemon) {
         System.out.println("You made your choice, you can only buy Pokemon this round");
      } else if (player.canSellPokemon) {
         System.out.println("You made your choice, you can only sell Pokemon this round");
      } else if (player.canFeedPokemon) {
         System.out.println("You made your choice, you can only feed your Pokemon this round");
      } else {
         System.out.println("You made your choice, you can only try or succeed breeding your Pokemon once per round");
      }
      Audio.soundEffect("audio/ahem.wav");
      GameHelper.inputEnter();
   }

   private void pokeDisplay(Player player) {
      System.out.println("===== POKEMON =====");
      for (Pokemon pokemon : player.getPlayerPokemon()) {
         // Cant get the padding right
         System.out.printf("%s%s %-10s %s%s/%-3s %s%s%s\n", pokemon.getBreed(), pokemon.genderSymbol(),
               pokemon.getName(), "Age: ", pokemon.getAge(), pokemon.getMaxAge(), "Health: ", pokemon.getHealth(), "%");
      }
   }

   private void foodDisplay(Player player) {
      // remove from display if amount is 0
      for (int i = player.getPlayerFood().size() - 1; i > 0; i--) {
         if (player.getPlayerFood().get(i).getAmount() == 0) {
            player.getPlayerFood().remove(i);
         }
      }
      System.out.println("===== FOOD =====");
      for (Food food : player.getPlayerFood()) {
         System.out.println(food.getType() + ": " + food.getAmount() + "kg");

      }
   }

}
