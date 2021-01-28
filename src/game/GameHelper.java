package game;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class GameHelper implements Serializable {

    // Scanner used for entire project
    public static Scanner input = new Scanner(System.in);

    public static String validateString() {
        System.out.println("\nEnter here: ");
        String answer = GameHelper.input.nextLine();
        if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            System.out.println("[y / n]");
            validateString();
        }
        return (answer.equalsIgnoreCase("y") ? "y" : "n");
    }

    // To make sure user choice is in range of what the menu/question is
    public static int getInt(int min, int max) {
        System.out.println("\nEnter here: ");
        int intReturn = -1;
        do {
            try {
                intReturn = Integer.parseInt(input.nextLine());
                if (intReturn > max || intReturn < min) {
                    System.out.println("A number between " + min + "-" + max);
                }
            } catch (Exception e) {
                System.out.println("An integer!");

            }
        } while (intReturn == -1 || intReturn > max || intReturn < min);
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

    public static void inputEnter() {
        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Faster way of doing "\n".repeat(60)
    public static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(80));
    }

    // Work on terminal not in ide such as Intellij unless you active escape codes,
    // clear the terminal. Not just fill screen with \n
    public static void clearScreen1() {
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
