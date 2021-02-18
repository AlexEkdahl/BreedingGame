package game;

import game.items.foods.Food;
import game.items.pokemons.Pokemon;


import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Menu implements Serializable {

    public boolean readHowTo = false;
    Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    protected void mainMenu() {
        int choice = -1;
        while (choice != 1) {
            Util.clearScreen();
            Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
            if (readHowTo) {
                Util.print("\n[" + PrintColors.ANSI_GREEN + 1 + PrintColors.ANSI_RESET + "]"
                           + PrintColors.ANSI_GREEN + " New game" + PrintColors.ANSI_RESET);
            } else {
                Util.print("\n[1] New game");
            }
            if (readHowTo) {
                Util.print("[2] How to play");
            } else {
                Util.print("[" + PrintColors.ANSI_YELLOW + 2 + PrintColors.ANSI_RESET + "]"
                           + PrintColors.ANSI_YELLOW + " How to play" + PrintColors.ANSI_RESET);
            }
            Util.print("\n[3] Load game");
            Util.print("\n[0] Exit game");
            choice = Util.getInt(0, 3);
            mainMenuChoice(choice);
        }
    }

    protected void gameMenu(Player player) throws Exception {
        playerDisplay(player);
        Util.print("===== GAME MENU =====");
        Util.print("[1] Buy Pokemon");
        Util.print("[2] Buy food");
        Util.print("[3] Feed Pokemon");
        Util.print("[4] Breed Pokemon");
        Util.print("[5] Sell Pokemon");
        Util.print("\n[8] Turn off/on music");
        Util.print("[9] Save and EXIT");
        Util.print("[0] - Finish round");
        gameMenuChoice(Util.getInt(0, 9), player);
    }

    protected void playerDisplay(Player player) {
        Util.clearScreen();
        Util.print(PrintColors.ANSI_RED + player.getName() + PrintColors.ANSI_RESET);
        Util.print("Money: " + player.getMoney());
        Util.print("Round: " + game.getRound() + "/" + game.getNumOfRounds());
        Util.print("");
        if (player.getPlayerFood().size() != 0) {
            foodDisplay(player);
        }
        if (player.getPlayerPokemon().size() != 0) {
            pokeDisplay(player);
        }
        Util.print("");
    }

    private void howToPlay() {
        Util.clearScreen();
        readHowTo = true;
        Util.print("""
                ===== HOW TO PLAY =====

                * This is a turn based game were players take tuns on setting up
                * their Pokemon for success. Players choose from buying or selling
                * Pokemon, buying food or try to breed. Each Pokemon have a different
                * value. Don't forget that different Pokemon eat different food.
                * When the last turn is finished the players automatically sell
                * their Pokemon and the winner is the one with most money left.

                * A player can only make a action in one of the categories per round.
                * But is able eg. to buy multiple Pokemon per round.

                * A player that is unable to buy any Pokemon due to shortage of money and
                * don't have any pokemon in their party is eliminated.
                * Each round your Pokemon get 1 year older and is loosing a percentage
                * of health. If you are unlucky one of your Pokemon get sick....""");
        Util.inputEnter();
    }

    private void exitGame() {
        Util.print("Bye bye");
        System.exit(0);
    }

    private void newGameMenu() {
        ArrayList<Player> players = new ArrayList<>();
        int numOfPlayers;
        int numOfRounds;
        int startingMoney;
        Util.clearScreen();
        Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
        Util.print("\nHow many players (1-4):");
        numOfPlayers = Util.getInt(1, 4);
        Util.clearScreen();
        Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
        Util.print("\nHow many rounds (5-35): ");
        numOfRounds = Util.getInt(5, 35);
        Util.clearScreen();
        Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
        Util.print("\nStarting money");
        Util.print("\n  [EASY]   [MEDIUM]    [HARD]");
        Util.print("[5 0 0 0]  [2 5 0 0]  [1 1 0 0]");
        startingMoney = Util.getInt(1100, 5000);
        for (int i = 1; i <= numOfPlayers; i++) {
            Util.clearScreen();
            Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
            System.out.printf("\nPlayer %d name:\n", i);
            players.add(new Player(Util.input.nextLine(), startingMoney));
        }
        game.setNumOfRounds(numOfRounds);
        game.setPlayers(players);
    }

    private void mainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> newGameMenu();
            case 2 -> howToPlay();
            case 3 -> loadSavedFile();
            case 0 -> exitGame();
        }
    }

    private void gameMenuChoice(int choice, Player player) throws Exception {
        switch (choice) {
            case 1 -> {
                if (player.canBuyPokemon) {
                    game.store.buyPokemon(player);
                } else {
                    playerDisplay(player);
                    choiceMade(player);
                }
            }
            case 2 -> {
                if (player.canBuyFood) {
                    game.store.buyFood(player);
                } else {
                    playerDisplay(player);
                    choiceMade(player);
                }
            }
            case 3 -> {
                if (player.canFeedPokemon) {
                    game.feed.feedPokemon(player);
                } else {
                    playerDisplay(player);
                    choiceMade(player);
                }
            }
            case 4 -> {
                if (player.canBreed) {
                    game.breed.breedPokemon(player);
                } else {
                    playerDisplay(player);
                    choiceMade(player);
                }
            }
            case 5 -> {
                if (player.canSellPokemon) {
                    game.store.sellPokemon(player);
                } else {
                    playerDisplay(player);
                    choiceMade(player);
                }
            }
            case 8 -> Audio.dummyMethod();
            case 9 -> newSaveFile();
            case 0 -> player.roundDone = true;
        }
    }

    private void newSaveFile() {
        File f = new File("SaveFiles/");
        Util.print("Enter name on savefile:");
        String fileName = Util.input.nextLine() + ".ser";
        if (!Files.exists(Paths.get("SaveFile/" + fileName))) {
            if (!f.exists()) {
                f.mkdir();
            }
            Serializer.serialize("SaveFiles/" + fileName, game);
        } else {
            Util.print("Filename already exist.");
            Util.print("[1] Overwrite existing savefile\n" + "[2] Create a new one");
            if (Util.getInt(1, 2) == 1) {
                Serializer.serialize("SaveFile/" + fileName, game);
            }
        }
        exitGame();
    }

    public void loadSavedFile() {
        String[] saveFiles;
        File f = new File("SaveFiles/");
        FilenameFilter filter = (f1, name) -> name.endsWith(".ser");
        saveFiles = f.list(filter);
        if (saveFiles == null || saveFiles.length == 0) {
            Util.print("No save files");
        } else {
            Util.clearScreen();
            Util.print("====== Welcome to THE POKEMON BREEDERS RACE ======");
            Util.print("\nSaved files\n");
            int n = 1;
            for (String save : saveFiles) {
                Util.print("[" + n + "] " + save.replaceAll(".ser", ""));
                n++;
            }
            int choice = Util.getInt(1, saveFiles.length);
            String saveString = "SaveFiles/" + saveFiles[choice - 1];
            try {
                this.game = (Game) Serializer.deserialize(saveString);
                game.newGame();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
        Util.inputEnter();
    }

    protected void choiceMade(Player player) throws Exception {
        if (player.canBuyFood) {
            Util.print("You made your choice, you can only buy food this round");
        } else if (player.canBuyPokemon) {
            Util.print("You made your choice, you can only buy Pokemon this round");
        } else if (player.canSellPokemon) {
            Util.print("You made your choice, you can only sell Pokemon this round");
        } else if (player.canFeedPokemon) {
            Util.print("You made your choice, you can only feed your Pokemon this round");
        } else {
            Util.print("You made your choice, you can only try or succeed breeding your Pokemon once per " + "round");
        }
        Audio.soundEffect("audio/ahem.wav");
        Util.inputEnter();
    }

    private void pokeDisplay(Player player) {
        Util.print("===== POKEMON =====");
        for (Pokemon pokemon : player.getPlayerPokemon()) {
            // Cant get the padding right
            System.out.printf("%-11.11s %-10s %s%s/%-3s %s%s%s\n", pokemon.getBreed(true),
                    pokemon.getName(), "Age: ", pokemon.getAge(),
                    pokemon.getMaxAge(), "Health: ", pokemon.getHealth(), "%");
        }
    }

    private void foodDisplay(Player player) {
        // remove from display if amount is 0
        for (int i = player.getPlayerFood().size() - 1; i > 0; i--) {
            if (player.getPlayerFood().get(i).getAmount() == 0) {
                player.getPlayerFood().remove(i);
            }
        }
        Util.print("===== FOOD =====");
        for (Food food : player.getPlayerFood()) {
            Util.print(food.getType() + ": " + food.getAmount() + "kg");
        }
    }

}
