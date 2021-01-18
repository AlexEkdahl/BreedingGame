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
   
   //Check if string is int
   private static int isInt(String s){
      int n = -1;
      try {
         return Integer.parseInt(s);
      } catch (Exception e) {
         System.out.println("Enter an integer");
         isInt(input.nextLine());
      }
      return n;
   }

   // To make sure user choice is in range of what the menu/question is
   public static int getInt(int min, int max){
      System.out.println("Enter here: ");
      int intReturn = isInt(input.nextLine());
      while (intReturn > max || intReturn < min){
         System.out.println("Enter a number between " + min + "-" + max);
         intReturn = isInt(input.nextLine());
      }
      return intReturn;
   }

      // Overloaded with save option
      public static int getInt(int min, int max, int save){
         System.out.println("Enter here: ");
         int intReturn = isInt(input.nextLine());
         while ((intReturn > max || intReturn < min) && intReturn !=save){
            System.out.println("Enter a number between " + min + "-" + max + ", or " + save);
            intReturn = isInt(input.nextLine());
         }
         return intReturn;
      }
   

   // "Sleeps" the terminal for int ms seconds
   public static void waitMilliSeconds(int ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public static void inputEnter(){
      System.out.println("\nPress Enter to continue...");
      try {
         System.in.read();
      } catch (IOException e) {
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
