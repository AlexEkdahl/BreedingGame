package Game;

import Game.FoodClasses.*;
import Game.PokemonClasses.*;
import java.util.*;

public class Feed {

   Game game;

   public Feed() {

   }

   public void setGame(Game game) {
      this.game = game;
   }

   //Sparar food i en LinkedHashedMap<Food, Integer>
   //Printar ut available food, enbart

   public void feedPokemon(Player player) {
      game.menu.playerDisplay(player);
      System.out.println("===== Feed your Pokemon =====");
      player.printPokemonList();
      System.out.println("\n[0] Exit to game menu" + "\nWhich Pokemon will you feed: ");
      int pokeIndex = GameHelper.getInt(0, player.getPlayerPokemon().size());
      if (pokeIndex != 0) {
         game.menu.playerDisplay(player);

         if (gotRightFood(player.getPokemon(pokeIndex - 1), player)) {
            System.out.println("===== Available Food for "+player.getPokemon(pokeIndex - 1).getBreed()+" =====");
            int numberOffFoods = printRightFood(player.getPokemon(pokeIndex - 1), player);
            System.out.println("\nWhat food: ");
            int choice = GameHelper.getInt(0, numberOffFoods);

         }

         game.menu.playerDisplay(player);

      } else {
         System.out.println("You have no suitable food for " + player.getPokemon(pokeIndex - 1).getName());
         GameHelper.inputEnter();
      }
   }

   // Only print food this pokemon can eat that the player got
   private int printRightFood(Pokemon pokemon, Player player) {
      int n = 1;
      for (Map.Entry<Food, Integer> entry : player.getPlayerFood().entrySet()) {
         if (isRightFood(pokemon, entry.getKey())) {
            System.out.println("[" + n + "]" + entry.getKey().getType() + "\t" + entry.getValue());
            n++;
         }
      }
      return n - 1;
   }

   private boolean gotRightFood(Pokemon pokemon, Player player) {
      for (Map.Entry<Food, Integer> entry : player.getPlayerFood().entrySet()) {
         for (Food food : pokemon.getCanEatFood()) {
            if (entry.getKey().getClass().equals(food.getClass())) {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isRightFood(Pokemon pokemon, Food targetFood) {
      for (Food food : pokemon.getCanEatFood()) {
         if (food.getClass().equals(food.getClass())) {
            return true;
         }
      }
      return false;
   }

}
