package Game;

import java.util.*;

import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player {

   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon;
   //Food only contains one value, price. Therefore we store it as String and Price
   // TODO might change to arrayList with just food
   private LinkedHashMap<String, Integer> playerFood;

   public Player(String name, int money) {
      this.name = name;
      this.money = money;
      this.playerPokemon = new ArrayList<>();
      this.playerFood = new LinkedHashMap<>();
   }



   public ArrayList<Pokemon> getPlayerPokemon() {
      return playerPokemon;
   }

   public int getMoney() {
      return money;
   }

   public void setMoney(int money) {
      this.money = money;
   }


   public void addPokemon(Pokemon newPokemon) {
      playerPokemon.add(newPokemon);
   }

   // creates pokemon to playerPokemon
   public void createPokemon(Pokemon pokemon) {
      System.out.println("Nickname for your " + pokemon.getBreed() + ": ");
      pokemon.setName(GameHelper.input.nextLine());

      System.out.println("What is " + pokemon.getName() + " gender?");
      System.out.println("[1] female / [2] male");

      pokemon.setGender(GameHelper.getInt(GameHelper.input.nextLine(), 1, 2));
      addPokemon(pokemon);
   }

   // if playerFood already contains that food, add up quantity
   public void addFood(Food food, int quantity) {
      if (playerFood.containsKey(food.getClass().getSimpleName())) {
         playerFood.put(food.getType(), playerFood.get(food.getType() + quantity));
      } else {
         playerFood.put(food.getType(), quantity);
      }
   }

   public void handlePurchase(int itemCost){
      money -= itemCost;
   }
   //debugging
   public void print(){
      for (Pokemon pokemon : playerPokemon){
         System.out.println(pokemon.getName());
     }
   }



}