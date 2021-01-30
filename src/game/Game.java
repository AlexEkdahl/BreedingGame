package game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    Menu menu = new Menu();
    Store store = new Store();
    Breed breed = new Breed();
    Feed feed = new Feed();
    private ArrayList<Player> players;
    private int numOfRounds;
    private int round = 1;
    private Player currentPlayer;

    public Game() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Audio.themeSong("src/audio/themesong.wav");
        menu.setGame(this);
        store.setGame(this);
        breed.setGame(this);
        feed.setGame(this);
        menu.mainMenu();
        for (Player player : players) {
            player.game = this;
        }
        currentPlayer = players.get(0);
        newGame();
    }

    public void newGame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        while (round <= numOfRounds && players.size() != 0) {
            menu.gameMenu(currentPlayer);
            if (currentPlayer.roundDone) {
                changePlayer();
            }
        }
        if (players.size() != 0) {
            pokemonAgeOrSell(true);
            printAllScores();
            Helper.print("The winner is " + getWinner().getName());
            PrintColors.startUp();
        } else {
            Helper.print("No winners only losers!");
        }
    }

    public void changePlayer() {
        Helper.clearScreen();
        Helper.print("Next player");
        Helper.inputEnter();
        // If the player is last in turn, then start over with player 1
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            currentPlayer = players.get(0);
            currentPlayer.accessShops(true);
            currentPlayer.roundDone = false;
            pokemonAgeOrSell(false);
            round++;
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
            currentPlayer.accessShops(true);
            currentPlayer.roundDone = false;
        }
        playerLost();
        getSick(currentPlayer);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getRound() {
        return round;
    }

    public int getNumOfRounds() {

        return numOfRounds;
    }

    public void setNumOfRounds(int numOfRounds) {

        this.numOfRounds = numOfRounds;
    }

    private void playerLost() {
        for (int i = players.size() - 1; i >= 0; i--) {
            if (players.get(i).getPlayerPokemon().size() == 0 && players.get(i).getMoney() < 500) {
                Helper.print(players.get(i).getName() + " ran out of Pokemon and money and is ELIMINATED");
                players.remove(i);
                Helper.inputEnter();
            }
        }
    }

    // Reverse loop, needed to remove pokemon from it
    private void pokemonAgeOrSell(boolean sellAll) {
        for (Player player : players) {
            for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
                if (sellAll) {
                    player.sellPokemon(i);
                    Helper.inputEnter();
                } else {
                    player.getPokemon(i).aging();
                    player.getPokemon(i).reduceHealth();
                    if (player.getPokemon(i).getAge() > player.getPokemon(i).getMaxAge()) {
                        Helper.print(player.getName() + "! " + player.getPokemon(i).getName() + " died of old " + "age");
                        player.getPlayerPokemon().remove(i);
                        Helper.inputEnter();
                    } else if (player.getPokemon(i).getHealth() <= 0) {
                        Helper.print(player.getName() + "! " + player.getPokemon(i).getName() + " died because " + "health dropped below 0");
                        player.getPlayerPokemon().remove(i);
                        Helper.inputEnter();
                    }
                }
            }
        }
    }

    public void getSick(Player currentPlayer) {
        if (players.size() != 0) {
            for (int i = currentPlayer.getPlayerPokemon().size() - 1; i >= 0; i--) {
                if (Math.random() < 0.2) {
                    currentPlayer.getPokemon(i).isSick = false;
                    Helper.print(currentPlayer.getName() + "! Your " + currentPlayer.getPokemon(i).toString(false) + " Got sick, pay the vet or let Pokemon die?");
                    Helper.print("Cost: 300" + "\n\n[y / n]");
                    if (Helper.validateString()) {
                        currentPlayer.getPlayerPokemon().remove(i);
                        Helper.print("You let your Pokemon die...");
                        Helper.inputEnter();
                    } else {
                        if (currentPlayer.getMoney() > 300) {
                            currentPlayer.handlePurchase(300);
                        } else {
                            Helper.print("You couldn't pay the price, you don't have enough founds");
                        }
                    }
                }
            }
        }
    }

    public void printAllScores() {
        for (Player player : this.players) {
            Helper.print(player.getName() + "\t\tgot:" + player.getMoney());
        }
    }

    public Player getWinner() {
        int bestScore = 0;
        int bestIndex = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getMoney() > bestScore) {
                bestScore = players.get(i).getMoney();
                bestIndex = i;
            }
        }
        return players.get(bestIndex);
    }

}