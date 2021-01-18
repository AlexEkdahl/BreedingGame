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

   // TODO Maybe want the user to be able to try to feed the pokemon wrong food?
   public void feedPokemon(Player player) {
      game.menu.playerDisplay(player);
      System.out.println("===== Feed your Pokemon =====");

      player.printPokemonList();

      System.out.println("\n[0] Exit to game menu" +"\nWhich Pokemon will you feed: ");
      int pokeIndex = GameHelper.getInt(0, player.getPlayerPokemon().size());
      if (pokeIndex != 0) {
         game.menu.playerDisplay(player);
         if (gotRightFood(player.getPokemon(pokeIndex - 1), player)) {
            printRightFood(player.getPokemon(pokeIndex - 1), player);
            System.out.println("Which Pokemon will you feed: ");
            int pokeIndex1 = GameHelper.getInt(0, player.getPlayerPokemon().size());
         }
         game.menu.playerDisplay(player);
         if (gotRightFood(player.getPokemon(pokeIndex - 1), player)) {
            printRightFood(player.getPokemon(pokeIndex - 1), player);
            System.out.println("Which Pokemon will you feed: ");
            int pokeIndex1 = GameHelper.getInt(0, player.getPlayerPokemon().size());

         } else {
            System.out.println("You have no suitable food for " + player.getPokemon(pokeIndex - 1).getName());
            GameHelper.inputEnter();
         }
      }

   }
   //TODO läspå
   // From my understandings it would have been better to use a Set of food,
   // reference Array is bad
   private boolean rightFood(Pokemon pokemon, Food targetFood) {
      return Arrays.asList(pokemon.getCanEatFood()).getClass().contains(targetFood);
   }

   // Only print food this pokemon can eat that the player got
   private void printRightFood(Pokemon pokemon, Player player) {
      int n = 1;
      for (Map.Entry<Food, Integer> entry : player.getPlayerFood().entrySet()) {
         if (rightFood(pokemon, entry.getKey())) {
            System.out.println("[" + n + "]" + entry.getKey().getType() + "\t" + entry.getValue());
            n++;
         }
      }
   }

   private boolean gotRightFood(Pokemon pokemon, Player player) {
      int n = 0;
      for (Map.Entry<Food, Integer> entry : player.getPlayerFood().entrySet()) {
         if (rightFood(pokemon, entry.getKey())) {
            n++;
         }
      }
      return (n == 0) ? false : true;
   }

}
