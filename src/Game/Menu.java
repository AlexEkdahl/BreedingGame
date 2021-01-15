package Game;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {

   public static void mainMenu() {
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("MAIN MENU");
      System.out.println("[1] New game");
      System.out.println("[2] How to play");
      System.out.println("\n[0]. Exit game");
      // TODO Load from saved file

      mainMenuChoice(GameHelper.getInt(true, 0, 2));
   }

   public static void gameMenu() {
      System.out.println("\n GAME MENU");
      System.out.println("[1] Buy Pokemon");
      System.out.println("[2] Buy food");
      System.out.println("[3] Feed Pokemon");
      System.out.println("[4] Breed Pokemon");
      System.out.println("\n[9] -Save Game- ");
      System.out.println("[0] - Finish round");
   }

   public static void playerDisplay() {
      System.out.println("PLAYERNAME");
      System.out.println("WWALLET");
      System.out.println("ROUND");
      System.out.println();
      System.out.println("POKEMON NAME");
      System.out.println("POKEMON BREED");
      System.out.println("POKEMON AGE");
      System.out.println("POKEMON HEALTH");
      System.out.println("POKEMON GENDER");
      System.out.println("\n".repeat(3));
   }

   public static void howToPlay() {
      System.out
            .println("===== HOW TO PLAY =====\n" + "* This is a turn based game were players take tuns on setting up\n"
                  + "* their Pokemon for success. Players choose from buying or selling\n"
                  + "* Pokemon, buying food or try to breed. Each Pokemon have a different\n"
                  + "* value. When the last turn is finished the players automatically sell\n"
                  + "* their Pokemon and the winner is the one with most money left.");
      mainMenu();
   }

   public static void exitGame() {
      System.out.println("Bye bye");
      System.exit(0);
   }

   public static void newGameMenu() {
      ArrayList<Player> players = new ArrayList<>();
      int numOfPlayers;
      int numOfRounds;
      int startingMoney;
      System.out.println("How many players: ");
      numOfPlayers = GameHelper.getInt(true, 1, 4);
      System.out.println("How many rounds: ");
      numOfRounds = GameHelper.getInt(true, 5, 35);
      System.out.println("Starting money: ");
      startingMoney = GameHelper.getInt(true, 100, 1000);
      System.out.println("[EASY][MEDIUM][HARD]");
      System.out.println("[$$$][$$][$]");

      for (int i = 0; i < numOfPlayers; i++) {
         System.out.println("Player 1 name: ");
         players.add(new Player(GameHelper.input.nextLine(), startingMoney));
      }

      // TODO transfer variables to game and create game :)
   }

   public static void mainMenuChoice(int choice) {
      switch (choice) {
         case 1 -> newGameMenu();
         case 2 -> howToPlay();
         case 0 -> exitGame();
         // TODO Load from saved file
      }
   }

   public static void clearScreen() {
      try {
         if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
         } else {
            System.out.print("\033\143");
         }
      } catch (IOException | InterruptedException ex) {
      }
   }

}
