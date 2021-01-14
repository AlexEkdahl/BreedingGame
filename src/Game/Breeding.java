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

   public void printPokemon() {
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

   // TODO add if there is no suitable spouse
   public void printPokemon(boolean findMate, Pokemon pokemon) {
      int i = 1;
      for (Pokemon pokemonMate : pokemonList) {
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

   public void setPokemon1(Pokemon pokemon) {
      this.pokemon1 = pokemon;
   }

   public void setPokemon2(Pokemon pokemonMate) {
      this.pokemon2 = pokemonMate;
   }


   public void pokemonChoice() {

   }

}