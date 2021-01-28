package game;

import game.foodclasses.*;
import game.pokemonclasses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {

    private Game game;
    private ArrayList<Pokemon> pokemonShelf = new ArrayList<>();
    private ArrayList<Food> foodShelf = new ArrayList<>();

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

    public void displayPokemon(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            fillPokeShelf();
            int n = 1;
            System.out.println("===== Pokemon Shop =====\n");
            for (Pokemon pokemon : pokemonShelf) {
                if (customer.getMoney() < pokemon.getPrice()) {
                    System.out.printf("[%-1.2s]%-10.10s\t%3.4s$\t[to expensive]", n, pokemon.getBreed(false),
                            pokemon.getPrice());
                } else {
                    System.out.printf("[%-1.2s]%-10.10s\t%3.4s$%n", n, pokemon.getBreed(false), pokemon.getPrice());
                }
                n++;
            }
            System.out.println("\n[0] Exit shop");
            int answer = GameHelper.getInt(0, 5);
            if (answer == 0) {
                break;
            }
            buyPokemon(pokemonShelf.get(answer - 1), customer);
        }
    }

    public void buyPokemon(Pokemon pokemon, Player customer) {
        if (enoughMoney(pokemon, customer)) {
            game.menu.playerDisplay(customer);
            System.out.println(pokemon.toString(true));
            System.out.println("\nWould you like to buy a " + pokemon.getBreed(false) + " for " + pokemon.getPrice() + "$?\n[y / n]");
            String answer = GameHelper.validateString();
            if (answer.equals("y") && customer.canBuyPokemon) {
                customer.createPokemon(pokemon, false);
                customer.handlePurchase(pokemon.getPrice());
                customer.accessShops(false);
                customer.canBuyPokemon = true;
            }
        } else {
            System.out.println("Not enough money");
            GameHelper.inputEnter();
        }
    }

    public void displayFood(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            int n = 1;
            System.out.println("===== PokeFood Shop =====\n");
            for (Food food : foodShelf) {
                if (customer.getMoney() < food.getPrice()) {
                    System.out.println("[" + n + "]" + food.getType() + "\t" + food.getPrice() + "$\t\t [too " +
                            "expensive]");
                } else {
                    System.out.println("[" + n + "]" + food.getType() + "\t" + food.getPrice() + "$");
                }
                System.out.println("-" + whichPokemon(food) + "\n");
                n++;
            }
            System.out.println("\n[0] Exit shop");
            int answer = GameHelper.getInt(0, 4);
            if (answer == 0) {
                break;
            }
            buyFood(foodShelf.get(answer - 1), customer);
        }
    }

    public void buyFood(Food food, Player customer) {
        game.menu.playerDisplay(customer);
        System.out.println("===== PokeFood Shop =====\n");
        System.out.println("Max " + food.getType() + " you can buy: " + maxFood(food, customer));
        System.out.println("How many: ");
        int number = GameHelper.getInt(0, maxFood(food, customer));
        if (number != 0 && customer.canBuyFood) {
            customer.addFood(food, number);
            customer.handlePurchase((food.getPrice() * number));
            customer.accessShops(false);
            customer.canBuyFood = true;
        }
    }

    public boolean enoughMoney(Pokemon pokemon, Player costumer) {
        return (costumer.getMoney() >= pokemon.getPrice());
    }

    public int maxFood(Food food, Player customer) {
        return customer.getMoney() / food.getPrice();
    }

    public void sellPokemon(Player customer) {
        while (true) {
            game.menu.playerDisplay(customer);
            System.out.println("====== Sell your POKEMON ======");
            if (customer.getPlayerPokemon().size() != 0) {
                customer.printPokemonList(false);
                System.out.println("\n[0] Exit to game menu");
                int index = GameHelper.getInt(0, customer.getPlayerPokemon().size());
                if (index == 0) {
                    break;
                }
                game.menu.playerDisplay(customer);
                System.out.println("Do you want to sell " + customer.getPokemon(index - 1).getName() + " for " + customer.getPokemon(index - 1).getValue() + "$?" + "\n[y / n]");
                String answer = GameHelper.validateString();
                if (answer.equals("y") && customer.canSellPokemon) {
                    customer.sellPokemon(index - 1);
                    customer.accessShops(false);
                    customer.canSellPokemon = true;
                }
            } else {
                System.out.println("You have no Pokemon");
                GameHelper.inputEnter();
                break;
            }
        }
    }

    private String whichPokemon(Food food) {
        fillPokeShelf();
        StringBuilder s = new StringBuilder();
        for (Pokemon pokemon : pokemonShelf) {
            if (game.feed.isRightFood(pokemon, food)) {
                s.append(pokemon.getBreed(false)).append(", ");
            }
        }
        return s.toString().trim().replaceFirst(".$", "");
    }

}