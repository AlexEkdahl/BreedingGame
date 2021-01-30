package game;

import game.foodclasses.Food;
import game.pokemonclasses.Pokemon;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.Serializable;

public class Feed implements Serializable {

    Game game;

    public Feed() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void feedPokemon(Player player) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (player.getPlayerPokemon().size() != 0 && (player.getPlayerFood().size() != 0)) {
            while (true) {
                game.menu.playerDisplay(player);
                System.out.println("===== Feed your Pokemon =====\n");
                printPokemon(player);
                System.out.println(Helper.exit + "\nWhich Pokemon will you feed: ");
                int pokeIndex = Helper.getInt(0, player.getPlayerPokemon().size());
                if (pokeIndex != 0) {
                    game.menu.playerDisplay(player);
                    System.out.println("===== Feed your Pokemon =====\n");
                    printFood(player);
                    System.out.println(Helper.exit + "\n Choose food: ");

                    int pokFood = Helper.getInt(0, player.getPlayerFood().size());
                    if (pokFood != 0 && isRightFood(player.getPokemon(pokeIndex - 1), player.getFood(pokFood - 1))) {
                        System.out.println("Max amount: " + player.getFood(pokFood - 1).getAmount());
                        int amount = Helper.getInt(0, player.getFood(pokFood - 1).getAmount());
                        if (amount == 0) {
                            break;
                        }
                        player.getPokemon(pokeIndex - 1).eat(player.getFood(pokFood - 1), amount);
                        player.getFood(pokFood - 1).removeFood(amount);
                        player.accessShops(false);
                        player.canFeedPokemon = true;
                    } else {
                        Audio.soundEffect("src/audio/listen.wav");
                        System.out.println("That's not a suitable food option for " + player.getPokemon(pokeIndex - 1).getBreed(false));
                        Helper.waitMilliSeconds(1500);
                    }

                } else {
                    break;
                }
            }
        } else {
            game.menu.playerDisplay(player);
            System.out.println("===== Feed your Pokemon =====\n");
            if (player.getPlayerPokemon().size() == 0) {
                System.out.println("You haven't got any Pokemon");
            } else {
                System.out.println("You have no food");
            }
            Helper.inputEnter();
        }
    }

    private void printPokemon(Player player) {
        int i = 1;
        for (Pokemon pokemon : player.getPlayerPokemon()) {
            System.out.println("[" + i + "]" + pokemon.getBreed(false) + ", " + pokemon.getName() + ". Health: " + pokemon.getHealth() + " Age: " + pokemon.getAge());
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

    public boolean isRightFood(Pokemon pokemon, Food food) {
        for (Food pokefood : pokemon.getCanEatFood()) {
            if (pokefood.getType().equals(food.getType())) {
                return true;
            }
        }
        return false;
    }

}
