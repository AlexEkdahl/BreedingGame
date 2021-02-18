package game;

import game.items.foods.Food;
import game.items.pokemons.Pokemon;

import java.io.Serializable;

public class Feed implements Serializable {

    Game game;

    public Feed() {
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void feedPokemon(Player player) throws Exception {
        if (player.getPlayerPokemon().size() != 0 && (player.getPlayerFood().size() != 0)) {
            while (true) {
                whichPokemonToFeed(player);
                int choiceOfPokemonToFeed = Util.getInt(0, player.getPlayerPokemon().size());
                if (choiceOfPokemonToFeed != 0) {
                    whichFoodToEat(player);
                    if (chooseAmountAndFed(player, choiceOfPokemonToFeed)) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        notAbleToFeedPokemon(player);
    }

    private boolean chooseAmountAndFed(Player player, int choiceOfPokemonToFeed) throws Exception {
        int pokeFoodChoice = Util.getInt(0, player.getPlayerFood().size());
        boolean pokemonCanEatThis = pokeFoodChoice != 0 && canPokemonEatThisFood(
                player.getPokemon(choiceOfPokemonToFeed - 1),
                player.getFood(pokeFoodChoice - 1));
        if (pokemonCanEatThis) {
            int amountOfFood = getAmountOfFood(player, pokeFoodChoice);
            if (amountOfFood == 0) {
                return true;
            }
            successfullyFedPokemon(player, choiceOfPokemonToFeed, pokeFoodChoice, amountOfFood);
        } else {
            Audio.soundEffect("audio/listen.wav");
            System.out.println("That's not a suitable food option for "
                               + player.getPokemon(choiceOfPokemonToFeed - 1).getBreed(false));
            Util.waitMilliSeconds(1500);
        }
        return false;
    }

    private int getAmountOfFood(Player player, int pokeFoodChoice) {
        System.out.println("Max amount: " + player.getFood(pokeFoodChoice - 1).getAmount());
        return Util.getInt(0, player.getFood(pokeFoodChoice - 1).getAmount());
    }

    private void notAbleToFeedPokemon(Player player) {
        game.menu.playerDisplay(player);
        System.out.println("===== Feed your Pokemon =====\n");
        if (player.getPlayerPokemon().size() == 0) {
            System.out.println("You haven't got any Pokemon");
        } else {
            System.out.println("You have no food");
        }
        Util.inputEnter();
    }

    private void successfullyFedPokemon(Player player, int choiceOfPokemonToFeed, int pokeFoodChoice, int amount) {
        player.getPokemon(choiceOfPokemonToFeed - 1).eat(amount);
        player.getFood(pokeFoodChoice - 1).removeFood(amount);
        player.accessShops(false);
        player.canFeedPokemon = true;
    }

    private void whichPokemonToFeed(Player player) {
        game.menu.playerDisplay(player);
        System.out.println("===== Feed your Pokemon =====\n");
        printPokemon(player);
        System.out.println(Util.askExitToMenu + "\nWhich Pokemon will you feed: ");
    }

    private void whichFoodToEat(Player player) {
        game.menu.playerDisplay(player);
        System.out.println("===== Feed your Pokemon =====\n");
        printFood(player);
        System.out.println(Util.askExitToMenu + "\n Choose food: ");
    }

    private void printPokemon(Player player) {
        int i = 1;
        for (Pokemon pokemon : player.getPlayerPokemon()) {
            System.out.println("[" + i + "]" + pokemon.getBreed(false) + ", "
                               + pokemon.getName() + ". Health: " + pokemon.getHealth()
                               + " Age: " + pokemon.getAge());
            System.out.println("\t- Eats: " + pokemon.foodToString() + "\n");
            i++;
        }
    }

    private void printFood(Player player) {
        int i = 1;
        for (Food food : player.getPlayerFood()) {
            System.out.println("[" + i + "]" + food.getType() + ", " + food.getAmount() + "st");
            i++;
        }
    }

    public boolean canPokemonEatThisFood(Pokemon pokemon, Food food) {
        for (Food pokefood : pokemon.getCanEatFood()) {
            if (pokefood.getType().equals(food.getType())) {
                return true;
            }
        }
        return false;
    }

}
