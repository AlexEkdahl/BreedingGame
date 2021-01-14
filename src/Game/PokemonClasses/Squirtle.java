package Game.PokemonClasses;

import Game.FoodClasses.*;

public class Squirtle extends Pokemon {

   public Squirtle(){
      price = 500;
      maxAge = 14;
      maxOffspring = 2;
      canEatFood = new Food[] { new Berry(), new PokeBlock(), new PokePuff(), new RareCandy() };
   }

   
}