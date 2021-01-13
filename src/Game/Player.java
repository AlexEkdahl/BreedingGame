package Game;

import java.util.*;

import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player {

   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon;
   private LinkedHashMap<String, Integer> playerFood;

   public Player(String name, int money) {
      this.name = name;
      this.money = money;
   }

   public int getMoney() {
      return money;
   }

   public void setMoney(int money) {
      this.money = money;
   }

   // Dont know yet if this is needed
   public void addPokemon(Pokemon newPokemon) {
      playerPokemon.add(newPokemon);
   }

   // creates and add pokemon to playerPokemon
   public void createAndAdd(Pokemon pokemon) {
      System.out.println("Nickname for your " + pokemon.getClass().getSimpleName() + ": ");
      pokemon.setName(GameHelper.input.nextLine());

      System.out.println("What is " + pokemon.getName() + " gender?");
      System.out.println("[1] female / [2] male");

      pokemon.setGender(GameHelper.getInt(GameHelper.input.nextLine(), 2, 1));
      playerPokemon.add(pokemon);
   }

   // if playerFood already contains that food, add up quantity
   public void addFood(Food food, int quantity) {
      if (playerFood.containsKey(food.getClass().getSimpleName())) {
         playerFood.put(food.getClass().getSimpleName(), playerFood.get(food.getClass().getSimpleName() + quantity));
      } else {
         playerFood.put(food.getClass().getSimpleName(), quantity);
      }
   }

   public void handlePurchase(int itemCost){
      money -= itemCost;
   }

}