package Game;

import java.util.LinkedHashMap;
import java.util.Map;

import Game.PokemonClasses.*;
import Game.FoodClasses.*;

public class Store {

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

   public void clearPokeShelf() {
      if (pokemonShelf != null) {
         pokemonShelf.clear();
      }
   }

   public void fillFoodShelf(LinkedHashMap<String, Food> foodShelf) {
      foodShelf.put("Berry", new Berry());
      foodShelf.put("PokéBlock", new PokeBlock());
      foodShelf.put("PokéPuff", new PokePuff());
      foodShelf.put("Rare Candy", new RareCandy());
   }

   public void clearFoodShelf() {
      if (foodShelf != null) {
         foodShelf.clear();
      }
   }

   public void displayPokemon() {
      int n = 1;
      for (Map.Entry<String, Pokemon> entry : pokemonShelf.entrySet()) {
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            System.out
                  .println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t[to expensive]");
            n++;
         } else {
            System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice());
            n++;
         }
      }
   }

   public void displayFood() {

      int n = 1;
      for (Map.Entry<String, Food> entry : foodShelf.entrySet()) {
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            System.out
                  .println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t [too expensive]");
            n++;
         } else {
            System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice());
            n++;
         }
      }
   }

   public void buyPokemon(Pokemon pokemon) {
      if (enoughMoney(pokemon, costumer)) {
         System.out.println("Would you like to buy a " + pokemon.getBreed() + "for " + pokemon.getPrice());
         System.out.println("[y / n]");
         if (GameHelper.validateString(GameHelper.input.nextLine())) {
            costumer.createPokemon(pokemon);
            costumer.handlePurchase(pokemon.getPrice());
            clearPokeShelf();
            fillPokeShelf(pokemonShelf);
            // TODO go back to store

         } else {
            // TODO go back to store

         }
      } else {
         // TODO go back to store
         System.out.println("Not enough money");
         displayPokemon();
      }
   }

   // ! Refactoring later, messy code unnecessary if statements
   public void buyFood(Food food) {
      System.out.println("Max item you can buy: " + maxFood(food));
      System.out.println("How much: ");
      //TODO if 0 go back to store
      String quantity = GameHelper.input.nextLine();
      int number = GameHelper.getInt(quantity, 1, maxFood(food));
      if (enoughMoney(food, number, costumer)) {
         costumer.addFood(food, number);
         clearFoodShelf();
         // TODO go back to store
      } else {
         // TODO go back to store
         System.out.println("Not enough money");
         displayFood();
      }
   }

   public boolean enoughMoney(Pokemon pokemon, Player costumer) {
      return (costumer.getMoney() >= pokemon.getPrice() ? true : false);
   }

   public boolean enoughMoney(Food food, int quantity, Player costumer) {

      return (costumer.getMoney() >= (food.getPrice() * quantity)) ? true : false;
   }

   public Pokemon pokemonToBuy(int number) {
      // needed a reachable return statement
      Pokemon pokemon = new Ditto();
      switch (number) {
         case 1:
            return new Bulbasur();
         case 2:
            return new Charmander();
         case 3:
            return new Squirtle();
         case 4:
            return new Pikachu();
         case 5:
            return new Ditto();
      }
      return pokemon;
   }

   public Food foodToBuy(int number) {
      // needed a reachable return statement
      Food food = new Berry();
      switch (number) {
         case 1:
            return new Berry();
         case 2:
            return new PokeBlock();
         case 3:
            return new PokePuff();
         case 4:
            return new RareCandy();
      }
      return food;
   }

   public int maxFood(Food food) {
      return costumer.getMoney() / food.getPrice();
   }
}