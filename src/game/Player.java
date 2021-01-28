package game;

import game.foodclasses.Food;
import game.pokemonclasses.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private final String name;
    public boolean canBuyPokemon = true;
    public boolean canSellPokemon = true;
    public boolean canBuyFood = true;
    public boolean canBreed = true;
    public boolean canFeedPokemon = true;
    public boolean roundDone = false;
    protected Game game;
    private int money;
    private ArrayList<Pokemon> playerPokemon;
    private ArrayList<Food> playerFood;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.playerPokemon = new ArrayList<>();
        this.playerFood = new ArrayList<>();
    }

    public ArrayList<Pokemon> getPlayerPokemon() {
        return playerPokemon;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public Food getFood(int index) {
        return playerFood.get(index);
    }

    public ArrayList<Food> getPlayerFood() {
        return playerFood;
    }

    public Pokemon getPokemon(int index) {
        return playerPokemon.get(index);
    }

    public void handlePurchase(int itemCost) {
        money -= itemCost;
    }

    private void addPokemon(Pokemon newPokemon) {
        playerPokemon.add(newPokemon);
    }

    // creates pokemon to playerPokemon
    public void createPokemon(Pokemon pokemon, boolean offspring) {
        if (offspring) {
            game.menu.playerDisplay(this);
            pokemon.setGender(Math.random() > 0.5 ? 1 : 2);
            Helper.print("You got a " + pokemon.getGenderString() + " " + pokemon.getBreed(false));
            Helper.waitMilliSeconds(1500);
        } else {
            game.menu.playerDisplay(this);
            Helper.print("What is " + pokemon.getBreed(false) + " gender?");
            Helper.print("[1] Female / [2] Male");
            pokemon.setGender(Helper.getInt(1, 2));
        }
        game.menu.playerDisplay(this);
        Helper.print("Nickname for your " + pokemon.getBreed(false) + ": ");
        pokemon.setName(Helper.input.nextLine());
        addPokemon(pokemon);
    }

    public void addFood(Food food, int quantity) {
        if (!playerFood.contains(food)) {
            playerFood.add(food);
        }
        food.addFood(quantity);
    }

    public void printPokemonList(boolean condensed) {
        int i = 1;
        for (Pokemon pokemon : playerPokemon) {
            if (condensed) {
                Helper.print("[" + i + "]" + pokemon.toString(false));
            } else {
                Helper.print("[" + i + "]" + pokemon.getBreed(false) + ", " + pokemon.getName() + " health: " + pokemon.getHealth());
            }
            i++;
        }
    }

    public void sellPokemon(int index) {
        Helper.print(name + " " + playerPokemon.get(index).getValue() + " for selling " + getPokemon(index).getName());
        money += playerPokemon.get(index).getValue();
        playerPokemon.remove(index);
    }

    // Granting access to shops
    public void accessShops(boolean setAllTrue) {
        if (setAllTrue) {
            this.canBuyPokemon = true;
            this.canSellPokemon = true;
            this.canBuyFood = true;
            this.canBreed = true;
            this.canFeedPokemon = true;
        } else {
            this.canBuyPokemon = false;
            this.canSellPokemon = false;
            this.canBuyFood = false;
            this.canBreed = false;
            this.canFeedPokemon = false;
        }
    }

}