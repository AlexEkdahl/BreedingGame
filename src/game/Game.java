package game;

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

    public Game() throws Exception {
        Audio.themeSong("audio/themesong.wav");
        menu.setGame(this);
        store.setGame(this);
        breed.setGame(this);
        feed.setGame(this);
        menu.mainMenu();
        for (var player : players) {
            player.setGame(this);
        }
        currentPlayer = players.get(0);
        newGame();
    }

    public void newGame() throws Exception {
        while (round <= numOfRounds && players.size() != 0) {
            menu.gameMenu(currentPlayer);
            changingPlayersAndHandlingAllEvents();
        }
        endOfGame();
    }

    private void changingPlayersAndHandlingAllEvents() throws Exception {
        if (currentPlayer.roundDone) {
            while (true) {
                changePlayer();
                getSick(currentPlayer);
                pokemonAgeing(currentPlayer);
                if (players.size() == 0) {
                    endOfGame();
                }
                if (!playerLost(currentPlayer)) {
                    break;
                }
                getSick(currentPlayer);
                pokemonAgeing(currentPlayer);
                if (!playerLost(currentPlayer)) {
                    break;
                }
            }
        }
    }

    private void getSick(Player currentPlayer) {
        if (players.size() != 0) {
            for (int i = currentPlayer.getPlayerPokemon().size() - 1; i >= 0; i--) {
                if (Math.random() < 0.2) {
                    Util.print(PrintColors.ANSI_YELLOW + "Cost: " + currentPlayer.getPokemon(i).getValue()
                               + PrintColors.ANSI_RESET);
                    Util.print(currentPlayer.getName() + "! Your "
                               + currentPlayer.getPokemon(i).toString(false)
                               + " Age: " + currentPlayer.getPokemon(i).getAge()
                               + " Got sick, pay the vet or let Pokemon die?" + "\n[y / n]");
                    handleSickPokemon(currentPlayer, i);
                }
            }
        }
    }

    private void handleSickPokemon(Player currentPlayer, int pokemonIndex) {
        if (!Util.validateString()) {
            currentPlayer.getPlayerPokemon().remove(pokemonIndex);
            Util.printAndWait("You let your Pokemon die...");
        } else {
            tryToHealPokemon(currentPlayer, pokemonIndex);
        }
    }

    private void tryToHealPokemon(Player currentPlayer, int pokemonIndex) {
        if (currentPlayer.getMoney() > currentPlayer.getPokemon(pokemonIndex).getValue()) {
            if (Math.random() > 0.5) {
                Util.printAndWait(currentPlayer.getPokemon(pokemonIndex).getName() + "Is well and breathing");
                currentPlayer.handlePurchase(currentPlayer.getPokemon(pokemonIndex).getValue());
            } else {
                Util.printAndWait("Unfortunately your pokemon didn't make it....");
                currentPlayer.getPlayerPokemon().remove(pokemonIndex);
            }
        } else {
            Util.printAndWait("You couldn't pay the price, you don't have enough founds");
            currentPlayer.getPlayerPokemon().remove(pokemonIndex);
        }
    }

    private void endOfGame() {
        if (players.size() != 0) {
            sellAllPokemon(currentPlayer);
            printAllScores();
            Util.print("The winner is " + getWinner().getName());
            PrintColors.startUp();
        } else {
            Util.print("No winners only losers!");
        }
        System.exit(0);
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
            Util.print(players.getName() + "\t\tgot:" + players.getMoney());
        }
    }

    public void changePlayer() {
        Util.clearScreen();
        Util.printAndWait("Next player");
        // If the player is last in turn, then start over with player 1
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            prepareNewPlayer(0);
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

    private boolean playerLost(Player currentPlayer) {
        if (currentPlayer.getPlayerPokemon().size() == 0 && currentPlayer.getMoney() < 500) {
            Util.print(currentPlayer.getName() + " ran out of Pokemon and money and is ELIMINATED");
            Util.inputEnter();
            Player temp = currentPlayer;
            changePlayer();
            players.remove(temp);
            if (players.size() == 0){
                endOfGame();
            }
            return true;
        }
        return false;
    }

    // Reverse loop, needed to remove pokemon from it
    private void pokemonAgeing(Player player) {
        for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
            player.getPokemon(i).aging();
            player.getPokemon(i).reduceHealth();
            if (player.getPokemon(i).getAge() > player.getPokemon(i).getMaxAge()) {
                Util.print(player.getName() + "! " + player.getPokemon(i).getName()
                           + " died of old " + "age");
                player.getPlayerPokemon().remove(i);
                Util.inputEnter();
            } else if (player.getPokemon(i).getHealth() <= 0) {
                Util.print(player.getName() + "! " + player.getPokemon(i).getName()
                           + " died because " + "health dropped below 0");
                player.getPlayerPokemon().remove(i);
                Util.inputEnter();
            }
        }
    }

    private void sellAllPokemon(Player player) {
        for (int i = player.getPlayerPokemon().size() - 1; i >= 0; i--) {
            player.sellPokemon(i);
            Util.inputEnter();
        }
    }

}
