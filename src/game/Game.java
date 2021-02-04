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
        Audio.themeSong("audio/themesong.wav");
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
                playerLost();
                getSick(currentPlayer);
            }
        }
        endOfGame();
    }

    private void getSick(Player currentPlayer) {
        if (players.size() != 0) {
            for (int i = currentPlayer.getPlayerPokemon().size() - 1; i >= 0; i--) {
                if (Math.random() < 0.2) {
                    Helper.print(currentPlayer.getName() + "! Your "
                                 + currentPlayer.getPokemon(i).toString(false)
                                 + " Got sick, pay the vet or let Pokemon die?");
                    Helper.print("Cost: 300" + "\n\n[y / n]");
                    handleSickPokemon(currentPlayer, i);
                }
            }
        }
    }

    private void handleSickPokemon(Player currentPlayer, int i) {
        if (!Helper.validateString()) {
            currentPlayer.getPlayerPokemon().remove(i);
            Helper.printAndWait("You let your Pokemon die...");
        } else {
            if (currentPlayer.getMoney() > 300) {
                currentPlayer.handlePurchase(300);
            } else {
                Helper.print("You couldn't pay the price, you don't have enough founds");
            }
        }
    }

    private void endOfGame() {
        if (players.size() != 0) {
            sellAllPokemon(currentPlayer);
            printAllScores();
            Helper.print("The winner is " + getWinner().getName());
            PrintColors.startUp();
        } else {
            Helper.print("No winners only losers!");
        }
    }

    private Player getWinner() {
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

    private void printAllScores() {
        for (Player players : this.players) {
            Helper.print(players.getName() + "\t\tgot:" + players.getMoney());
        }
    }

    public void changePlayer() {
        Helper.clearScreen();
        Helper.printAndWait("Next player");
        // If the player is last in turn, then start over with player 1
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            prepareNewPlayer(0);
            pokemonAgeing();
            round++;
        } else {
            prepareNewPlayer(players.indexOf(currentPlayer) + 1);
        }
    }

    private void prepareNewPlayer(int i) {
        currentPlayer = players.get(i);
        currentPlayer.accessShops(true);
        currentPlayer.roundDone = false;
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
    private void pokemonAgeing() {
        for (Player player : players) {
            for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
                player.getPokemon(i).aging();
                player.getPokemon(i).reduceHealth();
                if (player.getPokemon(i).getAge() > player.getPokemon(i).getMaxAge()) {
                    Helper.print(player.getName() + "! " + player.getPokemon(i).getName()
                                 + " died of old " + "age");
                    player.getPlayerPokemon().remove(i);
                    Helper.inputEnter();
                } else if (player.getPokemon(i).getHealth() <= 0) {
                    Helper.print(player.getName() + "! " + player.getPokemon(i).getName()
                                 + " died because " + "health dropped below 0");
                    player.getPlayerPokemon().remove(i);
                    Helper.inputEnter();
                }
            }
        }
    }

    private void sellAllPokemon(Player player) {
        for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
            player.sellPokemon(i);
            Helper.inputEnter();
        }
    }

}