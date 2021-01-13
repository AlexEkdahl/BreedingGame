package Game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

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
      String pokemonBreed = "";
      for (Map.Entry<String, Pokemon> entry : pokemonShelf.entrySet()) {
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            pokemonBreed = entry.getKey() + "[too expensive]";
         } else {
            pokemonBreed = entry.getKey();
         }
         System.out.println(pokemonBreed + " " + entry.getValue().getPrice());
      }
   }

   public void displayFood() {
      String foodName = "";
      for (Map.Entry<String, Food> entry : foodShelf.entrySet()) {
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            foodName = entry.getKey() + "[too expensive]";
         } else {
            foodName = entry.getKey();
         }
         System.out.println(foodName + " " + entry.getValue().getPrice());
      }
   }

   public void buyPokemon(Pokemon pokemon) {
      if (enoughMoney(pokemon, costumer)) {
         System.out.println("Would you like to buy a " + pokemon.getBreed() + "for " + pokemon.getPrice());
         System.out.println("[y / n]");
         if (GameHelper.validateYesOrNo(GameHelper.input.nextLine())){
            costumer.createAndAdd(pokemon);
         } else {
            displayPokemon();
         }
      } else {
         System.out.println("Not enough money");
      }
   }

   public void buyFood(Food food) {
      if (enoughMoney(food, costumer)) {
         // TODO make method
      } else {
         System.out.println("Not enough money");
      }

   }

   public boolean enoughMoney(Pokemon pokemon, Player costumer) {
      return (costumer.getMoney() > pokemon.getPrice() ? true : false);
   }

   public boolean enoughMoney(Food food, Player costumer) {
      return (costumer.getMoney() > food.getPrice() ? true : false);
   }

}