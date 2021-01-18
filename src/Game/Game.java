package Game;

import java.util.ArrayList;
import Game.PokemonClasses.*;

public class Game {

   private ArrayList<Player> players;

   Menu menu = new Menu();
   Store store = new Store();
   Breed breed = new Breed();
   Feed feed = new Feed();

   private int numOfRounds;
   private int round = 1;
   private Player currentPlayer;

   public Game() {

   }

   public void newGame() {
      menu.setGame(this);
      store.setGame(this);
      breed.setGame(this);
      feed.setGame(this);

      menu.mainMenu();
      for (Player player : players) {
         player.game = this;
      }
      currentPlayer = players.get(0);

      while (round < numOfRounds) {
         menu.gameMenu(currentPlayer);
         if (currentPlayer.getRoundDone()) {
            changePlayer();
         }
      }
   }

   public void setNumOfRounds(int numOfRounds) {
      this.numOfRounds = numOfRounds;
   }

   public void setPlayers(ArrayList<Player> players) {
      this.players = players;
   }

   public int getRound() {
      return round;
   }

   private boolean playerLost() {
      return (currentPlayer.getPlayerPokemon() == null && currentPlayer.getMoney() < 500) ? true : false;

   }

   public void changePlayer() {
      GameHelper.clearScreen();
      System.out.println("Next player");
      GameHelper.inputEnter();
      // If the player is last in turn, then start over with player 1
      if (players.indexOf(currentPlayer) == players.size() - 1) {
         currentPlayer = players.get(0);
         currentPlayer.accessShops(true);
         currentPlayer.setRoundDone(false);
         reducePokemonHealth();
         round++;
      } else {
         currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
         currentPlayer.accessShops(true);
         currentPlayer.setRoundDone(false);
      }
   }

   private void pokemonAgeing(){
      for (Player player: players){
         for(Pokemon pokemon: player.getPlayerPokemon()){
            pokemon.aging();
         }
      }
   }

   private void reducePokemonHealth(){
      for (Player player: players){
         for(Pokemon pokemon: player.getPlayerPokemon()){
            pokemon.reduceHealth();
         }
      }
   }



}