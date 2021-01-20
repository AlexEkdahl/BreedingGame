package Game;

import java.util.*;
import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player {
   public Game game;
   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon;
   private ArrayList<Food> playerFood;

   //TODO public
   private boolean canBuyPokemon = true;
   private boolean canSellPokemon = true;
   private boolean canBuyFood = true;
   private boolean canBreed = true;
   private boolean canFeedPokemon = true;
   private boolean roundDone = false;

   public Player(String name, int money) {
      this.name = name;
      this.money = money;
      this.playerPokemon = new ArrayList<>();
      this.playerFood = new ArrayList<>();
   }

   public boolean getRoundDone() {
      return roundDone;
   }

   public boolean canFeedPokemon() {
      return canFeedPokemon;
   }

   public boolean canBreed() {
      return canBreed;
   }

   public boolean canBuyFood() {
      return canBuyFood;
   }

   public boolean canBuyPokemon() {
      return canBuyPokemon;
   }

   public boolean canSellPokemon() {
      return canSellPokemon;
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

   public Food getFood(int index){
      return playerFood.get(index);
   }

   public ArrayList<Food> getPlayerFood() {
      return playerFood;
   }

   public Pokemon getPokemon(int index) {
      return playerPokemon.get(index);
   }

   public void setRoundDone(boolean roundDone) {
      this.roundDone = roundDone;
   }

   public void setCanBreed(boolean canBreed) {
      this.canBreed = canBreed;
   }

   public void setCanBuyFood(boolean canBuyFood) {
      this.canBuyFood = canBuyFood;
   }

   public void setCanBuyPokemon(boolean canBuyPokemon) {
      this.canBuyPokemon = canBuyPokemon;
   }

   public void setCanSellPokemon(boolean canSellPokemon) {
      this.canSellPokemon = canSellPokemon;
   }

   public void setCanFeedPokemon(boolean canFeedPokemon) {
      this.canFeedPokemon = canFeedPokemon;
   }

   public void setMoney(int money) {
      this.money = money;
   }

   public void handlePurchase(int itemCost) {
      money -= itemCost;
   }

   private void addPokemon(Pokemon newPokemon) {
      playerPokemon.add(newPokemon);
   }
   //TODO ev Animal
   // creates pokemon to playerPokemon
   public void createPokemon(Pokemon pokemon, boolean offspring) {
      if (offspring) {
         game.menu.playerDisplay(this);
         pokemon.setGender(Math.random() > 0.5 ? 1 : 2);
         System.out.println("You got a " + pokemon.getGenderString() + " " + pokemon.getBreed());
         GameHelper.waitMilliSeconds(1000);
      } else {
         game.menu.playerDisplay(this);
         System.out.println("What is " + pokemon.getBreed() + " gender?");
         System.out.println("[1] female / [2] male");
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