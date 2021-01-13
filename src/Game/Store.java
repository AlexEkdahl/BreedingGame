package Game;

import java.util.LinkedHashMap;
import java.util.Map;

import Game.PokemonClasses.*;
import Game.FoodClasses.*;

public abstract class Store {

   LinkedHashMap<String, Pokemon> pokemonShelf;
   LinkedHashMap<String, Food> foodShelf;
   Player costumer;

   public Store(Player costumer, boolean isPokeshop) {
      this.costumer = costumer;

      if (isPokeshop) {
         pokemonShelf = new LinkedHashMap<>();
         fillPokeShelf(pokemonShelf);
      } else {
         foodShelf = new LinkedHashMap<>();
         fillFoodShelf(foodShelf);
      }

   }

   public void fillPokeShelf(LinkedHashMap<String, Pokemon> pokemonShelf) {
      pokemonShelf.put("Bulbasur", new Bulbasur());
      pokemonShelf.put("Charmander", new Charmander());
      pokemonShelf.put("Squirtle", new Squirtle());
      pokemonShelf.put("Pikachu", new Pikachu());
      pokemonShelf.put("Ditto", new Ditto());
   }

   public void fillFoodShelf(LinkedHashMap<String, Food> foodShelf) {
      foodShelf.put("Berry", new Berry());
      foodShelf.put("PokéBlock", new PokeBlock());
      foodShelf.put("PokéPuff", new PokePuff());
      foodShelf.put("Rare Candy", new RareCandy());
   }

   public void displayPokemon() {
      System.out.println();
   }

   public void displayFood() {
      for (Map.Entry<String, Food> entry : foodShelf.entrySet()) {
         String itemName = "";
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            itemName = entry.getKey() + "[too expensive]";
         }else {
            itemName = entry.getKey();
         }
         System.out.println(itemName + entry.getValue().getPrice());
      }
   }
}