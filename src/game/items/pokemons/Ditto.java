package game.items.pokemons;

import game.items.foods.Food;
import game.items.foods.PokeBlock;
import game.items.foods.PokePuff;
import game.items.foods.RareCandy;

public class Ditto extends Pokemon {

    public Ditto() {
        price = 1000;
        maxAge = 10;
        maxOffspring = (int) (Math.random() * 5) + 1;
        canEatFood = new Food[]{new PokeBlock(), new PokePuff(), new RareCandy()};
    }

}