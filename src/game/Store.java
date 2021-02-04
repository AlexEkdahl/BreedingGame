package game;

import game.foodclasses.*;
import game.pokemonclasses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {

    private final ArrayList<Pokemon> pokemonShelf = new ArrayList<>();
    private final ArrayList<Food> foodShelf = new ArrayList<>();
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
            printPokemonToBuy(customer);
            Helper.print(Helper.askExitToMenu);
            int answer = Helper.getInt(0, 5);
            if (answer == 0) {
                break;
            }
            confirmPokemonPurchase(pokemonShelf.get(answer - 1), customer);
        }
    }

    public void confirmPokemonPurchase(Pokemon pokemon, Player customer) {
        if (enoughMoney(pokemon, customer)) {
            game.menu.playerDisplay(customer);
            Helper.print(pokemon.toString(true));
            Helper.print("\nWould you like to buy a " + pokemon.getBreed(false)
                         + " for " + pokemon.getPrice() + "$?\n[y / n]");
            boolean accessToBuyPokemon = Helper.validateString() && customer.canBuyPokemon;
            if (accessToBuyPokemon) {
                successfulPokemonPurchase(pokemon, customer);
            }
        } else {
            Helper.printAndWait("Not enough money");
        }
    }

    private void printPokemonToBuy(Player customer) {
        int n = 1;
        Helper.print("===== Pokemon Shop =====\n");
        for (Pokemon pokemon : pokemonShelf) {
            if (customer.getMoney() < pokemon.getPrice()) {
                System.out.printf("[%-1.2s]%-10.10s\t%3.4s$\t[to expensive]", n,
                        pokemon.getBreed(false), pokemon.getPrice());
            } else {
                System.out.printf("[%-1.2s]%-10.10s\t%3.4s$%n", n,
                        pokemon.getBreed(false), pokemon.getPrice());
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
            printFoodToBuy(customer);
            Helper.print(Helper.askExitToMenu);
            int answer = Helper.getInt(0, 4);
            if (answer == 0) {
                break;
            }
            confirmFoodPurchase(foodShelf.get(answer - 1), customer);
        }
    }

    public void confirmFoodPurchase(Food food, Player customer) {
        game.menu.playerDisplay(customer);
        Helper.print("===== PokeFood Shop =====\n");
        Helper.print("Max " + food.getType() + " you can buy: " + maxFood(food, customer) + "\nHow many: ");
        int amount = Helper.getInt(0, maxFood(food, customer));
        if (amount != 0 && customer.canBuyFood) {
            successfulFoodShopping(food, customer, amount);
        }
    }

    private void printFoodToBuy(Player customer) {
        int n = 1;
        Helper.print("===== PokeFood Shop =====\n");
        for (Food food : foodShelf) {
            if (customer.getMoney() < food.getPrice()) {
                Helper.print("[" + n + "]" + food.getType() + "\t" + food.getPrice() + "$\t\t [too " + "expensive]");
            } else {
                Helper.print("[" + n + "]" + food.getType() + "\t" + food.getPrice() + "$");
            }
            Helper.print(whichPokemonCanEatThis(food));
            n++;
        }
    }

    private String whichPokemonCanEatThis(Food food) {
        fillPokeShelf();
        StringBuilder s = new StringBuilder();
        for (Pokemon pokemon : pokemonShelf) {
            if (game.feed.canPokemonEatThisFood(pokemon, food)) {
                s.append(pokemon.getBreed(false)).append(", ");
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
            Helper.print("====== Sell your POKEMON ======");
            if (customer.getPlayerPokemon().size() != 0) {
                customer.printPlayerPokemonList(false);
                Helper.print(Helper.askExitToMenu);
                int playerChoice = Helper.getInt(0, customer.getPlayerPokemon().size());
                if (playerChoice == 0) {
                    break;
                }
                confirmPokemonToSell(customer, playerChoice);
            } else {
                Helper.printAndWait("You have no Pokemon");
                break;
            }
        }
    }

    private void confirmPokemonToSell(Player customer, int index) {
        game.menu.playerDisplay(customer);
        Helper.print("Do you want to sell " + customer.getPokemon(index - 1).getName()
                     + " for " + customer.getPokemon(index - 1).getValue() + "$?" + "\n[y / n]");
        if (Helper.validateString()) {
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
