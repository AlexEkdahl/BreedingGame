package Game.PokemonClasses;

import Game.FoodClasses.*;

public class Bulbasur extends Pokemon {

   public Bulbasur(){
      price = 500;
      maxAge = 12;
      maxOffspring = 3;
      canEatFood = new Food[] { new PokeBlock(), new PokePuff(), new RareCandy() };
   }

   
}