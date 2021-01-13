package Game;

import java.util.Scanner;

public class GameHelper {

   public static Scanner input = new Scanner(System.in);

   public static boolean validateYesOrNo(String answer) {
      if (!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
         answer = input.nextLine();
         validateYesOrNo(answer);
      }
      return (answer.equalsIgnoreCase("y") ? true : false);
   }

   // To make sure user choice is in range of what the menu is 
   public static int getInt(String s, int max, int min) {
      int returnInt;
      do {
         try {
            returnInt = Integer.parseInt(input.nextLine());
            if (returnInt > max || returnInt < min) {
               System.out.println("Please enter a number between " + min + "-" + max);
            }
         } catch (Exception e) {
            System.out.println("Please enter an integer!");
            returnInt = -1;
         }
      } while (returnInt == -1 || returnInt > max || returnInt < min);
      return returnInt;
   }




}
