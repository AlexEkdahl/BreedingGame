package Game;

import java.util.LinkedHashMap;
import java.util.Map;

import Game.PokemonClasses.*;
import Game.FoodClasses.*;

public class Store {

   private Game game;

   private LinkedHashMap<String, Pokemon> pokemonShelf = new LinkedHashMap<>();
   private LinkedHashMap<String, Food> foodShelf = new LinkedHashMap<>();

   public Store() {
      
   }
   
   public void buyYourPokemon(Player costumer) {
      displayPokemon(costumer);
   }
   
   public void setGame(Game game) {
      this.game = game;
   }

   private void fillPokeShelf() {
      if (pokemonShelf != null) {
         pokemonShelf.clear();
      }
      pokemonShelf.put("Bulbasur", new Bulbasur());
      pokemonShelf.put("Charmander", new Charmander());
      pokemonShelf.put("Squirtle", new Squirtle());
      pokemonShelf.put("Pikachu", new Pikachu());
      pokemonShelf.put("Ditto", new Ditto());
   }

   private void fillFoodShelf() {
      if (foodShelf != null) {
         foodShelf.clear();
      }
      foodShelf.put("Berry", new Berry());
      foodShelf.put("PokéBlock", new PokeBlock());
      foodShelf.put("PokéPuff", new PokePuff());
      foodShelf.put("Rare Candy", new RareCandy());
   }

   public void displayPokemon(Player customer) {
      GameHelper.clearScreen();
      game.menu.playerDisplay(customer);
      fillPokeShelf();
      int n = 1;
      System.out.println("===== Pokemon Shop =====\n");
      for (Map.Entry<String, Pokemon> entry : pokemonShelf.entrySet()) {
         if (customer.getMoney() < entry.getValue().getPrice()) {
            System.out
                  .println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t[to expensive]");
            n++;
         } else {
            System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice());
            n++;
         }
      }
      System.out.println("\n[0] Exit shop");
      pokemonToBuy(GameHelper.getInt(true, 0, 5), customer);
   }

   public void displayFood(Player customer) {
      GameHelper.clearScreen();
      game.menu.playerDisplay(customer);
      fillFoodShelf();
      int n = 1;
      System.out.println("===== PokeFood Shop =====\n");
      for (Map.Entry<String, Food> entry : foodShelf.entrySet()) {
         if (customer.getMoney() < entry.getValue().getPrice()) {
            System.out.println(
                  "[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t [too expensive]");
            n++;
         } else {
            System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice());
            n++;
         }
      }
      System.out.println("\n[0] Exit shop");
      foodToBuy(GameHelper.getInt(true, 0, 5), customer);
   }

   public void buyPokemon(Pokemon pokemon, Player customer) {
      if (enoughMoney(pokemon, customer)) {
         System.out.println("Would you like to buy a " + pokemon.getBreed() + " for " + pokemon.getPrice());
         System.out.println("===== info =====");
         System.out.println(pokemon.toString(true));
         System.out.println("[y / n]");
         if (GameHelper.validateString(GameHelper.input.nextLine())) {
            customer.createPokemon(pokemon, false);
            customer.handlePurchase(pokemon.getPrice());
            displayPokemon(customer);
         } else {
            displayPokemon(customer);
         }
      } else {
         System.out.println("Not enough money");
         displayPokemon(customer);
      }
   }

   public void buyFood(Food food, Player customer) {
      System.out.println("Max item you can buy: " + maxFood(food, customer));
      System.out.println("How much: ");
      int number = GameHelper.getInt(true, 1, maxFood(food, customer));
      if (number == 0) {
         displayFood(customer);
      } else {
         customer.addFood(food, number);
         customer.handlePurchase((food.getPrice() * number));
         displayFood(customer);
      }
   }

   public boolean enoughMoney(Pokemon pokemon, Player costumer) {
      return (costumer.getMoney() >= pokemon.getPrice() ? true : false);
   }

   public boolean enoughMoney(Food food, int quantity, Player costumer) {
      return (costumer.getMoney() >= (food.getPrice() * quantity)) ? true : false;
   }

   public void pokemonToBuy(int number, Player customer) {
      switch (number) {
         case 0 -> game.menu.gameMenu(customer);
         case 1 -> buyPokemon(new Bulbasur(), customer);
         case 2 -> buyPokemon(new Charmander(), customer);
         case 3 -> buyPokemon(new Squirtle(), customer);
         case 4 -> buyPokemon(new Pikachu(), customer);
         case 5 -> buyPokemon(new Ditto(), customer);
      }
   }

   public void foodToBuy(int number, Player customer) {
      switch (number) {
         case 0 -> game.menu.gameMenu(customer);
         case 1 -> buyFood(new Berry(), customer);
         case 2 -> buyFood(new PokeBlock(), customer);
         case 3 -> buyFood(new PokePuff(), customer);
         case 4 -> buyFood(new RareCandy(), customer);
      }
   }

   public int maxFood(Food food, Player customer) {
      return customer.getMoney() / food.getPrice();
   }

   public void sellPokemon(Player customer){
      game.menu.playerDisplay(customer);
      System.out.println("====== Sell your POKEMON ======");
      customer.printPokemonList();
      System.out.println("[0] Exit to game menu");
      int index = GameHelper.getInt(true, 0, customer.getPlayerPokemon().size());
      if (index == 0) {
         game.menu.gameMenu(customer);
      }
      System.out.println("Do you want to sell " + customer.getPokemon(index-1).getName() + " ?");
      System.out.println("[y / n]");
         if (GameHelper.validateString(GameHelper.input.nextLine())) {
            customer.sellPokemon(index -1);
         } else {
            sellPokemon(customer);
         }

   }

}