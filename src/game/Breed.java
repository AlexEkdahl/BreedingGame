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

    public void breedPokemon(Player player)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game.menu.playerDisplay(player);
        Helper.print("===== Breeding =====\n");
        if ((player.getPlayerPokemon().size() > 1)) {
            printPokemonList(player.getPlayerPokemon());
            Helper.print(Helper.askExitToMenu + "\nSelect your first Pokemon");
            int choiceOfFirstMate = getPokemonChoice(player.getPlayerPokemon());
            if (choiceOfFirstMate != 0) {
                chooseSuitableMate(getPokemon(choiceOfFirstMate, player.getPlayerPokemon()), player);
            }
        } else {
            Helper.printAndWait("You don't have enough Pokemon");
        }
    }

    // containing list of suitable mates for selected pokemon
    public void chooseSuitableMate(Pokemon pokemon, Player player)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        game.menu.playerDisplay(player);
        Helper.print("===== Select your partner =====");
        if (isThereSuitablePartners(pokemon, player)) {
            ArrayList<Pokemon> tempMate = getSuitableMateList(pokemon, player);
            printPokemonList(tempMate);
            Helper.print(Helper.askExitToMenu);
            int partnerChoice = getPokemonChoice(tempMate);
            if (partnerChoice != 0) {
                if (Math.random() > 0.5) {
                    pokemon.loveMakeing(player, getPokemon(partnerChoice, tempMate));
                } else {
                    unsuccessfulBreeding();
                }
                player.accessShops(false);
            }
        } else {
            Helper.printAndWait("No matching partner");
        }
    }

    private void printPokemonList(ArrayList<Pokemon> pokemonList) {
        int i = 1;
        for (Pokemon pokemon : pokemonList) {
            Helper.print("[" + i + "]" + pokemon.toString(false));
            i++;
        }
    }

    public boolean isThereSuitablePartners(Pokemon pokemon, Player player) {
        for (Pokemon pokemonMate : player.getPlayerPokemon()) {
            if (canMate(pokemon, pokemonMate)) {
                return true;
            }
        }
        return false;
    }

    // create a temp arraylist containing suitable mates
    private ArrayList<Pokemon> getSuitableMateList(Pokemon pokemon, Player player) {
        ArrayList<Pokemon> tempList = new ArrayList<>();
        for (Pokemon pokemonMate : player.getPlayerPokemon()) {
            if (canMate(pokemon, pokemonMate)) {
                tempList.add(pokemonMate);
            }
        }
        return tempList;
    }

    private boolean canMate(Pokemon pokemon, Pokemon pokemonMate) {
        return pokemonMate != pokemon && pokemonMate.getClass() == pokemon.getClass()
               && pokemonMate.getGender() != pokemon.getGender();
    }

    private int getPokemonChoice(ArrayList<Pokemon> pokemonList) {
        return Helper.getInt(0, pokemonList.size());
    }

    private void unsuccessfulBreeding()
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.print("\n.");
        Helper.waitMilliSeconds(700);
        System.out.print(".");
        Helper.waitMilliSeconds(700);
        System.out.print(".");
        Helper.waitMilliSeconds(700);
        Audio.soundEffect("audio/nextTime.wav");
        Helper.printAndWait("Unsuccessful breeding");
    }

    private Pokemon getPokemon(int index, ArrayList<Pokemon> pokemonList) {
        return pokemonList.get(index - 1);
    }

}