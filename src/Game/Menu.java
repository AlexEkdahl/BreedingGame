package Game;

import java.io.IOException;

public class Menu {

   public static void mainMenu() {
      System.out.println("====== Welcome to THE POKEMON BREEDERS RACE ======");
      System.out.println("MAIN MENU");
      System.out.println("[1] New game");
      System.out.println("[2] How to play");
      System.out.println("\n[0]. Exit game");
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
