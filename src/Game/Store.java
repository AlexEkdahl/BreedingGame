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

   public void setGame(Game game) {
      this.game = game;
   }

   // Pokemon have random max offsprings, so that player can buy different object
   // each time
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

   // TODO not neccessary
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
      while (true) {
         game.menu.playerDisplay(customer);
         fillPokeShelf();
         int n = 1;
         System.out.println("===== Pokemon Shop =====\n");
         for (Map.Entry<String, Pokemon> entry : pokemonShelf.entrySet()) {
            if (customer.getMoney() < entry.getValue().getPrice()) {
               System.out.println(
                     "[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice() + "\t\t[to expensive]");
               n++;
            } else {
               System.out.println("[" + n + "]" + entry.getKey() + "\t" + entry.getValue().getPrice());
               n++;
            }
         }
         System.out.println("\n[0] Exit shop");
         int answer = GameHelper.getInt(0, 5);

         if (answer == 0) {
            break;
         }
         pokemonToBuy(answer, customer);
      }
   }

   public void buyPokemon(Pokemon pokemon, Player customer) {
      if (enoughMoney(pokemon, customer)) {
         game.menu.playerDisplay(customer);
         System.out.println("\nWould you like to buy a " + pokemon.getBreed() + " for " + pokemon.getPrice() + "?\n");
         System.out.println(pokemon.toString(true) + "\n\n[y / n]");
         String answer = GameHelper.validateString();
         if (answer.equals("y") && customer.canBuyPokemon()){
            customer.createPokemon(pokemon, false);
            customer.handlePurchase(pokemon.getPrice());
            customer.accessShops(false);
            customer.setCanBuyPokemon(true);
         } else {
            game.menu.choiceMade(customer);
         }
      } else {
         System.out.println("Not enough money");
         GameHelper.inputEnter();
      }
   }

   public void pokemonToBuy(int number, Player customer) {
      switch (number) {
         case 1 -> buyPokemon(new Bulbasur(), customer);
         case 2 -> buyPokemon(new Charmander(), customer);
         case 3 -> buyPokemon(new Squirtle(), customer);
         case 4 -> buyPokemon(new Pikachu(), customer);
         case 5 -> buyPokemon(new Ditto(), customer);
      }
   }

   public void displayFood(Player customer) {
      while (true) {
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
         int answer = GameHelper.getInt(0, 5);
         if (answer == 0) {
            break;
         }
         foodToBuy(answer, customer);
      }
   }

   public void buyFood(Food food, Player customer) {
      System.out.println("Max item you can buy: " + maxFood(food, customer));
      System.out.println("How much: ");
      int number = GameHelper.getInt(0, maxFood(food, customer));
      if (number != 0 && customer.canBuyFood()) {
         customer.addFood(food, number);
         customer.handlePurchase((food.getPrice() * number));
         customer.accessShops(false);
         customer.setCanBuyFood(true);
      } else { 
         game.menu.choiceMade(customer);
      }
   }

   public void foodToBuy(int number, Player customer) {
      switch (number) {
         case 1 -> buyFood(new Berry(), customer);
         case 2 -> buyFood(new PokeBlock(), customer);
         case 3 -> buyFood(new PokePuff(), customer);
         case 4 -> buyFood(new RareCandy(), customer);
      }
   }

   public boolean enoughMoney(Pokemon pokemon, Player costumer) {
      return (costumer.getMoney() >= pokemon.getPrice() ? true : false);
   }

   public boolean enoughMoney(Food food, int quantity, Player costumer) {
      return (costumer.getMoney() >= (food.getPrice() * quantity)) ? true : false;
   }

   public int maxFood(Food food, Player customer) {
      return customer.getMoney() / food.getPrice();
   }

   public void sellPokemon(Player customer) {
      while (true) {
         game.menu.playerDisplay(customer);
         System.out.println("====== Sell your POKEMON ======");
         if (customer.getPlayerPokemon().size() != 0) {
            customer.printPokemonList();
            System.out.println("\n[0] Exit to game menu");
            int index = GameHelper.getInt(0, customer.getPlayerPokemon().size());
            if (index == 0) {
               break;
            }
            game.menu.playerDisplay(customer);
            if (index != 0) {
               System.out
                     .println("Do you want to sell " + customer.getPokemon(index - 1).getName() + " ?" + "\n[y / n]");
               String answer = GameHelper.validateString();
               if (answer.equals("y") && customer.canSellPokemon()) {
                  customer.sellPokemon(index - 1);
                  customer.accessShops(false);
                  customer.setCanSellPokemon(true);
               } else {
                  game.menu.choiceMade(customer);
                  break;
               }
            }
         } else {
            System.out.println("You have no Pokemon");
            GameHelper.inputEnter();
            break;
         }
      }
   }

}