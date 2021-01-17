package Game;

import java.util.ArrayList;

import Game.FoodClasses.*;
import Game.PokemonClasses.*;

public class Game {

   private ArrayList<Player>players;

   Menu menu = new Menu();
   Store store = new Store();
   Breed breed = new Breed();

   private int numOfRounds;
   private int startingMoney;
   private int round;
   private Player currentPlayer;

   public Game(){
      
   }

   public void newGame(){
      menu.setGame(this);
      store.setGame(this);
      breed.setGame(this);
      
      menu.mainMenu();
      currentPlayer = players.get(0);
      menu.gameMenu(currentPlayer);


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

   public void changePlayer(){

   }

   //!
   public void playerLost(){
      if (currentPlayer.getPlayerPokemon() == null){

      }
   }



   


   
}