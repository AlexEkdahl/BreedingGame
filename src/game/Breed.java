package game;

import game.items.pokemons.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class Breed implements Serializable {

    private Game game;

    public Breed() {
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void breedPokemon(Player player) throws Exception {
        game.menu.playerDisplay(player);
        Util.print("===== Breeding =====\n");
        if ((player.getPlayerPokemon().size() > 1)) {
            printPokemonList(player.getPlayerPokemon());
            Util.print(Util.askExitToMenu + "\nSelect your first Pokemon");
            int choiceOfFirstMate = getPokemonIndex(player.getPlayerPokemon());
            if (choiceOfFirstMate != 0) {
                chooseSuitableMate(getPokemon(choiceOfFirstMate, player.getPlayerPokemon()), player);
            }
        } else {
            Util.printAndWait("You don't have enough Pokemon");
        }
    }

    // containing list of suitable mates for selected pokemon
    public void chooseSuitableMate(Pokemon pokemon, Player player) throws Exception {
        game.menu.playerDisplay(player);
        Util.print("===== Select your partner =====");
        if (isThereSuitablePartners(pokemon, player)) {
            ArrayList<Pokemon> suitableMatesList = getSuitableMateList(pokemon, player);
            printPokemonList(suitableMatesList);
            Util.print(Util.askExitToMenu);
            int partnerChoice = getPokemonIndex(suitableMatesList);
            if (partnerChoice != 0) {
                if (Math.random() > 0.5) {
                    pokemon.loveMakeing(player, getPokemon(partnerChoice, suitableMatesList));
                } else {
                    unsuccessfulBreeding();
                }
                player.accessShops(false);
            }
        } else {
            Util.printAndWait("No matching partner");
        }
    }

    private void printPokemonList(ArrayList<Pokemon> pokemonList) {
        int i = 1;
        for (Pokemon pokemon : pokemonList) {
            Util.print("[" + i + "]" + pokemon.toString(false));
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

    private int getPokemonIndex(ArrayList<Pokemon> pokemonList) {
        return Util.getInt(0, pokemonList.size());
    }

    private void unsuccessfulBreeding() throws Exception {
        System.out.print("\n.");
        Util.waitMilliSeconds(700);
        System.out.print(".");
        Util.waitMilliSeconds(700);
        System.out.print(".");
        Util.waitMilliSeconds(700);
        Audio.soundEffect("audio/nextTime.wav");
        Util.printAndWait("Unsuccessful breeding");
    }

    private Pokemon getPokemon(int index, ArrayList<Pokemon> pokemonList) {
        return pokemonList.get(index - 1);
    }

}