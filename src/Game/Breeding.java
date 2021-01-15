package Game;

import java.util.ArrayList;

import Game.PokemonClasses.*;

public class Breeding {
   ArrayList<Pokemon> pokemonList;
   Player player;
   Pokemon pokemon1;
   Pokemon pokemon2;

   public Breeding(Player player) {
      this.player = player;
      this.pokemonList = player.getPlayerPokemon();
   }

   public void printAvailablePokemon() {
      if ((pokemonList.isEmpty())) {
         System.out.println("You have no Pokemon");
      } else if (pokemonList.size() == 1) {
         System.out.println("You only have one Pokemon");
      } else {
         int i = 1;
         for (Pokemon pokemon : pokemonList) {
            System.out.println("[" + i + "]" + pokemon.toString());
            i++;
         }
      }
   }

   //containing list of suitable mates for selected pokemon
   public void printSuitableMate(boolean findMate, Pokemon pokemon) {
      if (findSuitableMate(pokemon)) {
         int i = 1;
         for (Pokemon pokemonMate : pokemonList) {
            System.out.println("[" + i + "]" + pokemonMate.toString());
            i++;
         }
      } else {
         System.out.println("No matching partner");
         // TODO go back to menu
      }

   }

   public boolean findSuitableMate(Pokemon pokemon) {
      for (Pokemon pokemonMate : pokemonList) {
         if (pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass()
               && pokemonMate.getGender() != pokemon.getGender()) {
            return true;
         }
      }
      return false;
   }

   public void setPokemon1(Pokemon pokemon) {
      this.pokemon1 = pokemon;
   }

   public void setPokemon2(Pokemon pokemonMate) {
      this.pokemon2 = pokemonMate;
   }


}