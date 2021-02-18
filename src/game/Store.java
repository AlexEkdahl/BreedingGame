package game;

import game.items.Item;
import game.items.foods.*;
import game.items.pokemons.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {

    private final ArrayList<Item> pokemonShelf = new ArrayList<>();
    private final ArrayList<Item> foodShelf = new ArrayList<>();
    private Game game;

    public Store() {
        fillFoodShelf();
        fillPokeShelf();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // Pokemon have random max offsprings, so that player can buy different object
    // each time
    private void fillPokeShelf() {
        pokemonShelf.clear();
        pokemonShelf.add(new Bulbasur());
        pokemonShelf.add(new Charmander());
        pokemonShelf.add(new Squirtle());
        pokemonShelf.add(new Pikachu());
        pokemonShelf.add(new Ditto());
    }

    private void fillFoodShelf() {
        foodShelf.clear();
        foodShelf.add(new Berry());
        foodShelf.add(new PokeBlock());
        foodShelf.add(new PokePuff());
        foodShelf.add(new RareCandy());
    }

    public void buyPokemon(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            fillPokeShelf();
            printItemToBuy(customer, pokemonShelf, Util.pShop);
            Util.print(Util.askExitToMenu);
            int answer = Util.getInt(0, 5);
            if (answer == 0) {
                break;
            }
            confirmPokemonPurchase((Pokemon) pokemonShelf.get(answer - 1), customer);
        }
    }

    public void confirmPokemonPurchase(Pokemon pokemon, Player customer) {
        if (enoughMoney(pokemon, customer)) {
            game.menu.playerDisplay(customer);
            Util.print(pokemon.toString(true));
            Util.print("\nWould you like to buy a " + pokemon.getBreed(false)
                       + " for " + pokemon.getPrice() + "$?\n[y / n]");
            boolean accessToBuyPokemon = Util.validateString() && customer.canBuyPokemon;
            if (accessToBuyPokemon) {
                successfulPokemonPurchase(pokemon, customer);
            }
        } else {
            Util.printAndWait("Not enough money");
        }
    }

    private void printItemToBuy(Player customer, ArrayList<Item> list, String s) {
        int n = 1;
        Util.print(s);
        for (Item item : list) {
            if (customer.getMoney() < item.getPrice()) {
                System.out.printf("[%-1.2s]%-10.10s\t%3.4s$\t[to expensive]\n", n,
                        item.getClassName(), item.getPrice());
            } else {
                System.out.printf("[%-1.2s]%-10.10s\t%3.4s$%n", n,
                        item.getClassName(), item.getPrice());
                if (item instanceof Food){
                    Util.print(whichPokemonCanEatThis((Food) item));
                }
            }
            n++;
        }
    }

    private void successfulPokemonPurchase(Pokemon pokemon, Player customer) {
        customer.createPokemon(pokemon, false);
        customer.handlePurchase(pokemon.getPrice());
        customer.accessShops(false);
        customer.canBuyPokemon = true;
    }

    public void buyFood(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            printItemToBuy(customer, foodShelf, Util.fShop);
            Util.print(Util.askExitToMenu);
            int answer = Util.getInt(0, 4);
            if (answer == 0) {
                break;
            }
            confirmFoodPurchase((Food) foodShelf.get(answer - 1), customer);
        }
    }

    public void confirmFoodPurchase(Food food, Player customer) {
        game.menu.playerDisplay(customer);
        Util.print("===== PokeFood Shop =====\n");
        Util.print("Max " + food.getType() + " you can buy: " + maxFood(food, customer) + "\nHow many: ");
        int amount = Util.getInt(0, maxFood(food, customer));
        if (amount != 0 && customer.canBuyFood) {
            successfulFoodShopping(food, customer, amount);
        }
    }



    private String whichPokemonCanEatThis(Food food) {
        fillPokeShelf();
        StringBuilder s = new StringBuilder();
        for (Item pokemon : pokemonShelf) {
            if (game.feed.canPokemonEatThisFood((Pokemon) pokemon, food)) {
                s.append(pokemon.getClassName()).append(", ");
            }
        }
        return s.toString().trim().replaceFirst(".$", "");
    }

    private void successfulFoodShopping(Food food, Player customer, int amount) {
        customer.addFood(food, amount);
        customer.handlePurchase((food.getPrice() * amount));
        customer.accessShops(false);
        customer.canBuyFood = true;
    }

    public void sellPokemon(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            Util.print("====== Sell your POKEMON ======");
            if (customer.getPlayerPokemon().size() != 0) {
                customer.printPlayerPokemonList(false);
                Util.print(Util.askExitToMenu);
                int playerChoice = Util.getInt(0, customer.getPlayerPokemon().size());
                if (playerChoice == 0) {
                    break;
                }
                confirmPokemonToSell(customer, playerChoice);
            } else {
                Util.printAndWait("You have no Pokemon");
                break;
            }
        }
    }

    private void confirmPokemonToSell(Player customer, int index) {
        game.menu.playerDisplay(customer);
        Util.print("Do you want to sell " + customer.getPokemon(index - 1).getName()
                   + " for " + customer.getPokemon(index - 1).getValue() + "$?" + "\n[y / n]");
        if (Util.validateString()) {
            successfulSellPokemon(customer, index);
        }
    }

    private void successfulSellPokemon(Player customer, int index) {
        customer.sellPokemon(index - 1);
        customer.accessShops(false);
        customer.canSellPokemon = true;
    }

    public boolean enoughMoney(Pokemon pokemon, Player costumer) {
        return (costumer.getMoney() >= pokemon.getPrice());
    }

    public int maxFood(Food food, Player customer) {
        return customer.getMoney() / food.getPrice();
    }

}
