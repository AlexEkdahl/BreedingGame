package Game;

import Game.PokemonClasses.*;

public class Breeding {

   Player player;

   public Breeding(Player player) {
      this.player = player;
   }

   public void printPokemon() {
      if ((player.getPlayerPokemon().isEmpty())) {
         System.out.println("You have no Pokemon");
      } else if (player.getPlayerPokemon().size() == 1) {
         System.out.println("You only have one Pokemon");
      } else {
         int i = 1;
         for (Pokemon pokemon : player.getPlayerPokemon()) {
            System.out.println("[" + i + "]" + pokemon.toString());
            i++;
         }
      }
   }

   // TODO add if there is no suitable spouse
   public void printPokemon(boolean findMate, Pokemon pokemon) {
      int i = 1;
      for (Pokemon pokemonMate : player.getPlayerPokemon()) {
         if (pokemonMate != pokemon) {
            if (pokemonMate.getClass() == pokemon.getClass()) {
               if (pokemonMate.getGender() != pokemon.getGender()) {
                  System.out.println("[" + i + "]" + pokemonMate.toString());
                  i++;
               }
            }
         }
      }
   }

   public void pokemonChoice() {

   }

}