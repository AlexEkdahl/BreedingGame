package game;

import game.items.foods.Food;
import game.items.pokemons.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private final String name;
    private final ArrayList<Pokemon> playerPokemon;
    private final ArrayList<Food> playerFood;
    public boolean canBuyPokemon = true;
    public boolean canSellPokemon = true;
    public boolean canBuyFood = true;
    public boolean canBreed = true;
    public boolean canFeedPokemon = true;
    public boolean roundDone = false;
    protected Game game;
    private int money;

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

    public void setGame(Game game) {
        this.game = game;
    }

    // creates pokemon to playerPokemon
    public void createPokemon(Pokemon pokemon, boolean offspring) {
        if (offspring) {
            game.menu.playerDisplay(this);
            pokemon.setGender(Math.random() > 0.5 ? 1 : 2);
            Util.print("You got a " + pokemon.getGenderString() + " " + pokemon.getBreed(false));
            Util.waitMilliSeconds(1500);
        } else {
            game.menu.playerDisplay(this);
            Util.print("What is " + pokemon.getBreed(false) + " gender?");
            Util.print("[1] Female / [2] Male");
            pokemon.setGender(Util.getInt(1, 2));
        }
        game.menu.playerDisplay(this);
        Util.print("Nickname for your " + pokemon.getBreed(false) + ": ");
        pokemon.setName(Util.input.nextLine());
        addPokemon(pokemon);
    }

    public void addFood(Food food, int quantity) {
        if (!playerFood.contains(food)) {
            playerFood.add(food);
        }
        food.addFood(quantity);
    }

    public void printPlayerPokemonList(boolean condensed) {
        int i = 1;
        for (Pokemon pokemon : playerPokemon) {
            if (condensed) {
                Util.print("[" + i + "]" + pokemon.toString(false));
            } else {
                Util.print("[" + i + "]" + pokemon.getBreed(false) + ", "
                           + pokemon.getName() + " health: " + pokemon.getHealth());
            }
            i++;
        }
    }

    public void sellPokemon(int index) {
        Util.print(name + " " + playerPokemon.get(index).getValue() + " for selling " + getPokemon(index).getName());
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