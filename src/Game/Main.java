package Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu.mainMenu();

        System.out.println("Round: 1");
        System.out.println("\t".repeat(5) +"[ ]".repeat(5));
        System.out.println("\t".repeat(5) +"[ ]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[ ]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[X]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[ ]".repeat(5));

        



    }
}
