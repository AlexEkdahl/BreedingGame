package Game;

import java.io.IOException;
import java.util.Scanner;

public class GameHelper {

   // Scanner used for entire project
   public static Scanner input = new Scanner(System.in);

   public static boolean validateString() {
      System.out.println("Enter here: ");
      String answer = GameHelper.input.nextLine();
      if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
         System.out.println("[y / n]");
         validateString();
      }
      return (answer.equalsIgnoreCase("y") ? true : false);
   }

   // To make sure user choice is in range of what the menu/question is
   public static int getInt(boolean useScanner, int min, int max) {
      int returnInt = -1;
      System.out.print("Enter here: ");
      String s = GameHelper.input.nextLine();
      if (useScanner) {
         do {
            try {
               returnInt = Integer.parseInt(s);
               if (returnInt > max || returnInt < min) {
                  System.out.println("Enter a number between " + min + "-" + max);
                  s = input.nextLine();
               }
            } catch (Exception e) {
               System.out.println("Enter an integer");
               s = input.nextLine();
            }
         } while (returnInt > max || returnInt < min);
      }
      return returnInt;
   }

   // Overloaded get Int
   public static int getInt(boolean useScanner, int min, int max, int save) {
      int returnInt = 0;
      System.out.println("Enter here: ");
      String s = GameHelper.input.nextLine();
      if (useScanner) {
         do {
            try {
               returnInt = Integer.parseInt(s);
               if (returnInt > max || returnInt < min && returnInt != save) {
                  System.out.println("Enter a number between " + min + "-" + max + ", or " + save);
                  s = input.nextLine();
               }
            } catch (Exception e) {
               System.out.println("Enter an integer");
               s = input.nextLine();
            }
         } while (returnInt > max || returnInt < min && returnInt != save);
      }
      return returnInt;
   }

   // "Sleeps" the terminal for int ms seconds
   public static void waitMilliSeconds(int ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   // Work on mac os, clear the terminal. Not just fill screen with \n
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
