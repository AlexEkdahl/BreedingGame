package Game;

import Game.PokemonClasses.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // just for debugging methods
        Menu.clearScreen();
        Menu.mainMenu();

        System.out.println("How many players: ");
        System.out.println("1");
        System.out.println("Name: ");
        String name = GameHelper.input.nextLine();
        int money = 1000;
        Player player1 = new Player(name, money);

        Pokemon pika = new Pikachu();
        pika.setGender(1);
        pika.setName("Pika");

        Pokemon pika3 = new Pikachu();
        pika3.setGender(2);
        pika3.setName("Chu");

        Pokemon ditto = new Ditto();
        ditto.setGender(1);
        ditto.setName("Hej");
        Pokemon ditto1 = new Ditto();
        ditto1.setGender(2);
        ditto1.setName("Hej1");
        
        player1.addPokemon(pika);
        player1.addPokemon(pika3);
        player1.addPokemon(ditto);
        player1.addPokemon(ditto1);

        ditto.loveMakeing(player1, ditto1);

        Breeding breed = new Breeding(player1);
        breed.printAvailablePokemon();

        System.out.println("What pokemon do you want to breed?");
        String in = GameHelper.input.nextLine();
        int int1 = GameHelper.getInt(in, 1, player1.getPlayerPokemon().size());

        breed.setPokemon1(player1.getPlayerPokemon().get(int1-1));
        System.out.println();
        System.out.println();
        breed.printSuitableMate(true, player1.getPlayerPokemon().get(int1-1));


        while (true) {
            Store store = new Store(player1);
            Menu.clearScreen();
            Menu.playerDisplay();
            store.displayPokemon();
            System.out.println("which pokemon: ");
            store.buyPokemon(store.pokemonToBuy(GameHelper.getInt(GameHelper.input.nextLine(), 1, 5)));
            player1.print();
            System.out.println();
            Store storeFood = new Store(player1);
            Menu.clearScreen();
            Menu.playerDisplay();
            storeFood.displayFood();
            System.out.println("which food: ");
            store.buyFood(store.foodToBuy(GameHelper.getInt(GameHelper.input.nextLine(), 1, 4)));

        }

    }
}
