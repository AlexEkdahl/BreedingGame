package game.pokemonclasses;

import game.foodclasses.Food;
import game.foodclasses.PokeBlock;
import game.foodclasses.PokePuff;
import game.foodclasses.RareCandy;

public class Ditto extends Pokemon {

   public Ditto() {
      price = 1000;
      maxAge = 10;
      // This type of pokemon should generate a random max offspring
      maxOffspring = (int) (Math.random() * 5) + 1;
      canEatFood = new Food[]{new PokeBlock(), new PokePuff(), new RareCandy()};
   }

}