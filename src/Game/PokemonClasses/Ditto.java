package Game.PokemonClasses;

import Game.FoodClasses.*;

public class Ditto extends Pokemon {

   public Ditto() {
      price = 1000;
      maxAge = 10;
      // This type of pokemon should generate a random max offspring
      maxOffspring = (int) (Math.random() * (7 - 3)) + 3;
      canEatFood = new Food[] { new PokeBlock(), new PokePuff(), new RareCandy() };
   }

}