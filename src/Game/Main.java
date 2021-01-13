package Game;

import java.util.Scanner;

import Game.PokemonClasses.Pikachu;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu.mainMenu();

        System.out.println("Round: 1");
        System.out.println("\t".repeat(5) +"[ ]".repeat(5));
        System.out.println("\t".repeat(5) +"[ ]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[ ]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[X]" + "\t" + "    [ ]");
        System.out.println("\t".repeat(5) +"[ ]".repeat(5));

        Pikachu pika = new Pikachu();
        System.out.println(pika.getPrice());

        



    }
}
