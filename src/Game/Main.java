package Game;

import Game.PokemonClasses.Pikachu;

public class Main {
    public static void main(String[] args) throws Exception {

        // just for debugging methods
        Menu.mainMenu();

        System.out.println("How many players: ");
        System.out.println("1");
        System.out.println("Name: ");
        String name = GameHelper.input.nextLine();
        System.out.println("How many monetaassss: ");
        int money = 1000;
        Player player1 = new Player(name, money);
        while (true) {
            Store store = new Store(player1, true);
            //Menu.clearScreen();
            Menu.playerDisplay();
            store.displayPokemon();
            System.out.println("which pokemon: ");
            store.buyPokemon(store.pokemonToBuy(GameHelper.getInt(GameHelper.input.nextLine(), 1, 5)));
            player1.print();
            System.out.println();
            Store storeFood = new Store(player1, false);
            Menu.playerDisplay();
            storeFood.displayFood();
            System.out.println("which food: ");
            store.buyFood(store.foodToBuy(GameHelper.getInt(GameHelper.input.nextLine(), 1, 4)));

        }

    }
}
