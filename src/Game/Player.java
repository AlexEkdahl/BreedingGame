package Game;

import java.util.*;

import Game.FoodClasses.Food;
import Game.PokemonClasses.*;

public class Player {

   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon; 
   private Map<String, Integer> playerFood;

   public Player(String name, int money){
      this.name = name;
      this.money = money;
   }



   public int getMoney() {
      return money;
   }
   public void setMoney(int money) {
      this.money = money;
   }

   public void addPokemon(Pokemon newPokemon){
      playerPokemon.add(newPokemon);
   }

   public void createAndAdd(Pokemon pokemon){
      System.out.println("Nickname for your " + pokemon.getClass().getSimpleName() + ": ");
      pokemon.setName(GameHelper.input.nextLine());
      System.out.println("Gender of your " + pokemon.getClass().getSimpleName());
      System.out.println("[1] female / [2] male");
      pokemon.setGender("MALE"); //! EDIT LATER
      playerPokemon.add(pokemon);
   }

   public void addFood(Food food, int quantity){
      //TODO look at this
      
   }




   
}