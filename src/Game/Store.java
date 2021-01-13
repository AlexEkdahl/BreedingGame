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

   public void fillFoodShelf(LinkedHashMap<String, Food> foodShelf) {
      foodShelf.put("Berry", new Berry());
      foodShelf.put("PokéBlock", new PokeBlock());
      foodShelf.put("PokéPuff", new PokePuff());
      foodShelf.put("Rare Candy", new RareCandy());
   }

   public void displayPokemon() {
      int n = 0;
      for (Map.Entry<String, Pokemon> entry : pokemonShelf.entrySet()) {
         if (costumer.getMoney() < entry.getValue().getPrice()) {
            System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t[to expensive]");
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
            System.out.println("[" + n + "]" + entry.getKey() + " " + entry.getValue().getPrice() + "\t\t [too expensive]");
            n++;
         } else {
            System.out.println("[" + n + "]" + entry.getKey() + " " + entry.getValue().getPrice());
            n++;
         }
      }
   }

   public void buyPokemon(Pokemon pokemon) {
      if (enoughMoney(pokemon, costumer)) {
         System.out.println("Would you like to buy a " + pokemon.getBreed() + "for " + pokemon.getPrice());
         System.out.println("[y / n]");
         if (GameHelper.validateYesOrNo(GameHelper.input.nextLine())){
            costumer.createAndAdd(pokemon);
            costumer.handlePurchase(pokemon.getPrice());
         } else {
            displayPokemon();
         }
      } else {
         System.out.println("Not enough money");
      }
   }
   //!
   public void buyFood(Food food) {
      if (enoughMoney(food, costumer)) {
         // TODO ask how many kg to buy
         int kg = 0;
         //TODO Dont know how i will display the food yet 
         if(true){
            costumer.addFood(food, kg);
         }

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