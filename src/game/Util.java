package game;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Util implements Serializable {

    public static String askExitToMenu = "\n[0] Exit to game menu";
    public static String pShop = "===== Pokemon Shop =====\n";
    public static String fShop = "===== PokeFood Shop =====\n";

    // Scanner used for entire project
    public static Scanner input = new Scanner(System.in);

    public static boolean validateString() {
        Util.print(PrintColors.ANSI_YELLOW + "\nEnter here: " + PrintColors.ANSI_RESET);
        String answer = input.nextLine();
        if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
            Util.print("[y / n]");
            validateString();
        }
        return (answer.equalsIgnoreCase("y"));
    }

    // To make sure user choice is in range of what the menu/question is
    public static int getInt(int min, int max) {
        Util.print(PrintColors.ANSI_YELLOW + "\nEnter here: " + PrintColors.ANSI_RESET);
        int intReturn = -1;
        do {
            try {
                intReturn = Integer.parseInt(input.nextLine());
                if (intReturn > max || intReturn < min) {
                    Util.print("A number between " + min + "-" + max);
                }
            } catch (Exception e) {
                Util.print("An integer!");
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
        Util.print(PrintColors.ANSI_YELLOW + "\nPress Enter to continue" + PrintColors.ANSI_RESET);
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
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public static void print(String x) {
        if (!x.equals("")) {
            System.out.println(x);
        } else {
            System.out.println();
        }
    }

    public static void printAndWait(String s) {
        Util.print(s);
        inputEnter();
    }

}
