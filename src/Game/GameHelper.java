package Game;

import java.util.Scanner;

public class GameHelper {

   public static Scanner input = new Scanner(System.in);

   public static boolean validateString(String answer) {
      if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
         System.out.println("[y / n]");
         answer = input.nextLine();
         validateString(answer);
      }
      return (answer.equalsIgnoreCase("y") ? true : false);
   }

   // To make sure user choice is in range of what the menu/question is
   public static int getInt(String s, int min, int max) {
      int returnInt = -1;
      do {
         try {
            returnInt = Integer.parseInt(s);
            if (returnInt > max || returnInt < min) {
               System.out.println("Please enter a number between " + min + "-" + max);
               s = input.nextLine();
            }
         } catch (Exception e) {
            System.out.println("Please enter an integer");
            s = input.nextLine();

         }
      } while (returnInt > max || returnInt < min);
      return returnInt;
   }

}
