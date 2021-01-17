package Game;

import java.util.ArrayList;

import Game.PokemonClasses.*;

public class Breed {
   Game game;
   Pokemon pokemon1;
   Pokemon pokemon2;

   public Breed() {

   }

   public void setGame(Game game) {
      this.game = game;
   }

   public void printAvailablePokemon(Player player) {
      GameHelper.clearScreen();
      game.menu.playerDisplay(player);
      System.out.println("===== Breeding =====\n");
      if ((player.getPlayerPokemon().isEmpty())) {
         System.out.println("You have no Pokemon");
         game.menu.gameMenu(player);

      } else if (player.getPlayerPokemon().size() == 1) {
         System.out.println("You only have one Pokemon");
         game.menu.gameMenu(player);
      } else {
         int i = 1;
         for (Pokemon pokemon : player.getPlayerPokemon()) {
            System.out.println("[" + i + "]" + pokemon.toString(false));
            i++;
         }
         System.out.println("\n[0] Exit to game menu");
         printSuitableMate(getPokemon(GameHelper.getInt(true, 0, player.getPlayerPokemon().size()), player), player);
      }
   }

   // containing list of suitable mates for selected pokemon
   public void printSuitableMate(Pokemon pokemon, Player player) {
      GameHelper.clearScreen();
      game.menu.playerDisplay(player);
      if (findSuitableMate(pokemon, player)) {
         ArrayList<Pokemon> tempMate = getMate(pokemon, player);
         int i = 1;
         for (Pokemon pokemonMate : tempMate) {
            System.out.println("[" + i + "]" + pokemonMate.toString(false));
            i++;
         }
         System.out.println("\n[0] Exit to game menu");
         pokemon.loveMakeing(player, getMate(GameHelper.getInt(true, 1, tempMate.size()), tempMate));
         game.menu.gameMenu(player);
      } else {
         System.out.println("No matching partner");
         printAvailablePokemon(player);
      }
   }

   public boolean findSuitableMate(Pokemon pokemon, Player player) {
      for (Pokemon pokemonMate : player.getPlayerPokemon()) {
         if (pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass()
               && pokemonMate.getGender() != pokemon.getGender()) {
            return true;
         }
      }
      return false;
   }

   // create a temp arraylist containing suitable mates
   private ArrayList<Pokemon> getMate(Pokemon pokemon, Player player) {
      ArrayList<Pokemon> tempList = new ArrayList<>();
      for (Pokemon pokemonMate : player.getPlayerPokemon()) {
         if (pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass()
               && pokemonMate.getGender() != pokemon.getGender()) {
            tempList.add(pokemonMate);
         }
      }
      return tempList;
   }

   private Pokemon getPokemon(int index, Player player) {
      if (index == 0) {
         game.menu.gameMenu(player);
      }
      return player.getPlayerPokemon().get(index - 1);
   }

   // get mate from templist
   private Pokemon getMate(int index, ArrayList<Pokemon> pokemonList) {
      return pokemonList.get(index - 1);
   }

}