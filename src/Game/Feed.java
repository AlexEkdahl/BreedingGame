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
      player.printPokemonList();
      System.out.println("[0] Exit to game menu");
      int pokeIndex = GameHelper.getInt(true, 0, player.getPlayerPokemon().size());
      if (pokeIndex == 0) {
         game.menu.gameMenu(player);
      }
      game.menu.playerDisplay(player);
      printRightFood(player.getPokemon(pokeIndex - 1), player);

      System.out.println();

   }

   // From my understandings it would have been better to use a Set of food,
   // reference Array is bad
   private boolean rightFood(Pokemon pokemon, Food targetFood) {
      return Arrays.asList(pokemon.getCanEatFood()).contains(targetFood);
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

}
