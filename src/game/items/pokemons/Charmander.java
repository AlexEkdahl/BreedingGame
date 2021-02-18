package game.items.pokemons;

import game.items.foods.Berry;
import game.items.foods.Food;
import game.items.foods.PokePuff;
import game.items.foods.RareCandy;

public class Charmander extends Pokemon {

    public Charmander() {
        price = 500;
        maxAge = 13;
        maxOffspring = (int) (Math.random() * 3) + 1;
        canEatFood = new Food[]{new Berry(), new PokePuff(), new RareCandy()};
    }

}