package Game.PokemonClasses;

import Game.FoodClasses.*;

public class Pikachu extends Pokemon {

   public Pikachu() {
      price = 500;
      maxAge = 20;
      maxOffspring = (int) (Math.random() * 3) + 1;
      canEatFood = new Food[] { new Berry(), new PokeBlock(), new RareCandy() };
   }

}