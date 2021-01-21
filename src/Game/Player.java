package Game;

import java.io.Serializable;
import java.util.*;
import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player implements Serializable{
   public Game game;
   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon;
   private ArrayList<Food> playerFood;

   public boolean canBuyPokemon = true;
   public boolean canSellPokemon = true;
   public boolean canBuyFood = true;
   public boolean canBreed = true;
   public boolean canFeedPokemon = true;
   public boolean roundDone = false;

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
         System.out.println("You got a " + pokemon.getGenderString() + " " + pokemon.getBreed());
         GameHelper.waitMilliSeconds(1500);
      } else {
         game.menu.playerDisplay(this);
         System.out.println("What is " + pokemon.getBreed() + " gender?");
         System.out.println("[1] Female / [2] Male");
         pokemon.setGender(GameHelper.getInt(1, 2));
      }
      game.menu.playerDisplay(this);
      System.out.println("Nickname for your " + pokemon.getBreed() + ": ");
      pokemon.setName(GameHelper.input.nextLine());
      addPokemon(pokemon);
   }

   public void addFood(Food food, int quantity) {
      if (playerFood.contains(food)) {
         food.addFood(quantity);
      } else {
         playerFood.add(food);
         food.addFood(quantity);
      }
   }

   public void printPokemonList(boolean condensed) {
      int i = 1;
      for (Pokemon pokemon : playerPokemon) {
         if (condensed) {
            System.out.println("[" + i + "]" + pokemon.toString(false));
            i++;
         } else {
            System.out.println(
                  "[" + i + "]" + pokemon.getBreed() + ", " + pokemon.getName() + " health: " + pokemon.getHealth());
                  i++;
         }
      }
   }

   public void sellPokemon(int index) {
      System.out
            .println(name + " " + playerPokemon.get(index).getValue() + " for selling " + getPokemon(index).getName());
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