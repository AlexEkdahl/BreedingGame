package game.pokemonclasses;

import game.foodclasses.Berry;
import game.foodclasses.Food;
import game.foodclasses.PokePuff;
import game.foodclasses.RareCandy;

public class Charmander extends Pokemon {

    public Charmander() {
        price = 500;
        maxAge = 13;
        maxOffspring = (int) (Math.random() * 3) + 1;
        canEatFood = new Food[]{new Berry(), new PokePuff(), new RareCandy()};
    }

}