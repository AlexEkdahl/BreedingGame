package game.items.pokemons;

import game.items.foods.Berry;
import game.items.foods.Food;
import game.items.foods.PokeBlock;
import game.items.foods.RareCandy;

public class Squirtle extends Pokemon {

    public Squirtle() {
        price = 500;
        maxAge = 14;
        maxOffspring = (int) (Math.random() * 3) + 1;
        canEatFood = new Food[]{new Berry(), new PokeBlock(), new RareCandy()};
    }

}