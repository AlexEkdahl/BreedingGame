package game.items.pokemons;

import game.items.foods.Food;
import game.items.foods.PokeBlock;
import game.items.foods.PokePuff;
import game.items.foods.RareCandy;

public class Bulbasur extends Pokemon {

    public Bulbasur() {
        price = 500;
        maxAge = 12;
        maxOffspring = (int) (Math.random() * 3) + 1;
        canEatFood = new Food[]{new PokeBlock(), new PokePuff(), new RareCandy()};
    }

}