package Game;

import java.io.IOException;

public class Menu {

   public static void mainMenu() {
      System.out.println("\n- - - - Welcome to THE FARMERS RACE - - - -");
      System.out.println("MAIN MENU");
      System.out.println("[1] New game");
      System.out.println("[2] How to play");
      System.out.println("\n[0]. Exit game");
   }

   public static void gameMenu() {
      System.out.println("\n GAME MENU");
      System.out.println("[1] Buy dragons");
      System.out.println("[2] Buy food");
      System.out.println("[3] Feed dragon");
      System.out.println("[4] Breed dragon");
      System.out.println("\n[9] -Save Game- ");
      System.out.println("[0] - Finish round");
   }

   public static void playerDisplay() {


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
