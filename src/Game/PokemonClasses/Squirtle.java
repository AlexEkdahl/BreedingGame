package Game.PokemonClasses;

import Game.FoodClasses.*;

public class Squirtle extends Pokemon {

   public Squirtle() {
      price = 500;
      maxAge = 14;
      maxOffspring = (int) (Math.random() * 3) + 1;
      canEatFood = new Food[] { new Berry(), new PokeBlock(), new RareCandy() };
   }

}