package Game;

import java.util.*;

import Game.PokemonClasses.*;

public class Player {

   private String name;
   private int money;
   private ArrayList<Pokemon> playerPokemon; 
   private Map<String, Integer> playerFood;

   public Player(String name, int money){
      this.name = name;
      this.money = money;
   }

   public void addPokemon(Pokemon newPokemon){
      playerPokemon.add(newPokemon);
   }

   
}