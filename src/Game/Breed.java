package Game;

import java.io.Serializable;
import java.util.ArrayList;
import Game.PokemonClasses.*;

public class Breed implements Serializable{
   private Game game;

   public Breed() {

   }

   public void setGame(Game game) {
      this.game = game;
   }

   public void printAvailablePokemon(Player player) {
      while (true) {
         game.menu.playerDisplay(player);
         System.out.println("===== Breeding =====\n");
         if ((player.getPlayerPokemon().size() > 1)) {
            int i = 1;
            for (Pokemon pokemon : player.getPlayerPokemon()) {
               System.out.println("[" + i + "]" + pokemon.toString(false));
               i++;
            }
            System.out.println("\n[0] Exit to game menu" + "\nSelect your first Pokemon");
            int choice = GameHelper.getInt(0, player.getPlayerPokemon().size());
            if (choice == 0) {
               break;
            }
            printSuitableMate(getPokemon(choice, player), player);
            break;
         } else {
            System.out.println("You dont have enough Pokemon");
            GameHelper.inputEnter();
            break;
         }
      }
   }

   // containing list of suitable mates for selected pokemon
   public void printSuitableMate(Pokemon pokemon, Player player) {
      GameHelper.clearScreen();
      game.menu.playerDisplay(player);
      System.out.println("===== Select your partner =====");
      if (findSuitableMate(pokemon, player)) {
         ArrayList<Pokemon> tempMate = getMate(pokemon, player);
         int i = 1;
         for (Pokemon pokemonMate : tempMate) {
            System.out.println("[" + i + "]" + pokemonMate.toString(false));
            i++;
         }
         System.out.println("\n[0] Exit to game menu");
         int choice = GameHelper.getInt(0, tempMate.size());
         if (choice != 0) {
            if (Math.random() > 0.5) {
               pokemon.loveMakeing(player, getMate(choice, tempMate));
               player.accessShops(false);
            } else {
               System.out.print("\n.");
               GameHelper.waitMilliSeconds(700);
               System.out.print(".");
               GameHelper.waitMilliSeconds(700);
               System.out.print(".");
               GameHelper.waitMilliSeconds(700);
               System.out.println("Unsuccessful breeding");
               player.accessShops(false);
               GameHelper.inputEnter();
            }
         }
      } else {
         System.out.println("No matching partner");
         GameHelper.inputEnter();
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
      return player.getPlayerPokemon().get(index - 1);
   }

   // get mate from templist
   private Pokemon getMate(int index, ArrayList<Pokemon> pokemonList) {
      return pokemonList.get(index - 1);
   }

}