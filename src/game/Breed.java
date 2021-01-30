package game;

import game.pokemonclasses.Pokemon;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Breed implements Serializable {

    private Game game;

    public Breed() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void printAvailablePokemon(Player player) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game.menu.playerDisplay(player);
        Helper.print("===== Breeding =====\n");
        if ((player.getPlayerPokemon().size() > 1)) {
            int i = 1;
            for (Pokemon pokemon : player.getPlayerPokemon()) {
                Helper.print("[" + i + "]" + pokemon.toString(false));
                i++;
            }
            Helper.print(Helper.exit + "\nSelect your first Pokemon");
            int choice = Helper.getInt(0, player.getPlayerPokemon().size());
            if (choice != 0) {
                printSuitableMate(getPokemon(choice, player), player);
            }
        } else {
            Helper.print("You don't have enough Pokemon");
            Helper.inputEnter();
        }
    }

    // containing list of suitable mates for selected pokemon
    public void printSuitableMate(Pokemon pokemon, Player player) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Helper.clearScreen();
        game.menu.playerDisplay(player);
        Helper.print("===== Select your partner =====");
        if (findSuitableMate(pokemon, player)) {
            ArrayList<Pokemon> tempMate = getMate(pokemon, player);
            int i = 1;
            for (Pokemon pokemonMate : tempMate) {
                Helper.print("[" + i + "]" + pokemonMate.toString(false));
                i++;
            }
            Helper.print(Helper.exit);
            int choice = Helper.getInt(0, tempMate.size());
            if (choice != 0) {
                if (Math.random() > 0.5) {
                    pokemon.loveMakeing(player, getMate(choice, tempMate));
                    player.accessShops(false);
                } else {
                    System.out.print("\n.");
                    Helper.waitMilliSeconds(700);
                    System.out.print(".");
                    Helper.waitMilliSeconds(700);
                    System.out.print(".");
                    Helper.waitMilliSeconds(700);
                    Audio.soundEffect("audio/nextTime.wav");
                    Helper.print("Unsuccessful breeding");
                    player.accessShops(false);
                    Helper.inputEnter();
                }
            }
        } else {
            Helper.print("No matching partner");
            Helper.inputEnter();
        }
    }

    public boolean findSuitableMate(Pokemon pokemon, Player player) {
        for (Pokemon pokemonMate : player.getPlayerPokemon()) {
            if (pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass() && pokemonMate.getGender() != pokemon.getGender()) {
                return true;
            }
        }
        return false;
    }

    // create a temp arraylist containing suitable mates
    private ArrayList<Pokemon> getMate(Pokemon pokemon, Player player) {
        ArrayList<Pokemon> tempList = new ArrayList<>();
        for (Pokemon pokemonMate : player.getPlayerPokemon()) {
            if (pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass() && pokemonMate.getGender() != pokemon.getGender()) {
                tempList.add(pokemonMate);
            }
        }
        return tempList;
    }

    private Pokemon getPokemon(int index, Player player) {
        return player.getPlayerPokemon().get(index - 1);
    }

    // get mate from templist
    private Pokemon getMate(int index, ArrayList<Pokemon> pokemonList) {
        return pokemonList.get(index - 1);
    }

}