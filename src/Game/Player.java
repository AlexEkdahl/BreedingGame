package Game;

import java.util.*;

import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player {
   public Game game;
   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon;
   private LinkedHashMap<Food, Integer> playerFood;

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
      this.playerFood = new LinkedHashMap<>();
   }

   public void setRoundDone(boolean roundDone) {
      this.roundDone = roundDone;
   }

   public boolean getRoundDone(){
      return roundDone;
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

   public boolean canFeedPokemon(){
      return canFeedPokemon;
   }

   public boolean canBreed(){
      return canBreed;
   }

   public boolean canBuyFood(){
      return canBuyFood;
   }

   public boolean canBuyPokemon(){
      return canBuyPokemon;
   }

   public boolean canSellPokemon(){
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

   public void setMoney(int money) {
      this.money = money;
   }

   public void addPokemon(Pokemon newPokemon) {
      playerPokemon.add(newPokemon);
   }

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

   // if playerFood already contains that food, add up quantity
   public void addFood(Food food, int quantity) {
      if (playerFood.containsKey(food)) {
         playerFood.put(food, playerFood.get(food) + quantity);
      } else {
         playerFood.put(food, quantity);
      }
   }

   public void handlePurchase(int itemCost) {
      money -= itemCost;
   }

   public void printPokemonList() {
      int i = 1;
      for (Pokemon pokemon : playerPokemon) {
         System.out.println("[" + i + "]" + pokemon.toString(false));
         i++;
      }
   }

   public LinkedHashMap<Food, Integer> getPlayerFood() {
      return playerFood;
   }

   public void printPlayerFood() {
      int n = 1;
      for (Map.Entry<Food, Integer> entry : playerFood.entrySet()) {
         System.out.println("[" + n + "]" + entry.getKey().getType() + "\t" + entry.getValue());
         n++;
      }
   }

   public Pokemon getPokemon(int index) {
      return playerPokemon.get(index);
   }

   // !
   public void feedPokemon(int index) {
      System.out.println("Choose food to feed: ");
      getPokemon(index - 1);
   }

   public void sellPokemon(int index) {
      System.out
            .println("You got " + playerPokemon.get(index).getValue() + " for selling " + getPokemon(index).getName());
      money += playerPokemon.get(index).getValue();
      playerPokemon.remove(index);
   }

   //Granting access to shops
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