package Game;

import java.util.ArrayList;

import Game.FoodClasses.*;
import Game.PokemonClasses.*;

public class Game {

   private ArrayList<Player>players;
   private Menu menu;
   private Store store;
   private Breeding breedArea;
   int numOfRounds;
   int startingMoney;
   private int playedRounds;
   private Player currentPlayer = new Player("hej", 10000);

   public Game(){
      this.menu = new Menu();

      //TODO Hur gör jag åtkomst till stor när jag behöver en player för att skapa store/breeding
      this.store = new Store(currentPlayer);
      this.breedArea = new Breeding(currentPlayer);
      menu.setGame(this);

      
      
   }



   public void newGame(){

      //loop mellan 
      menu.mainMenu();
      currentPlayer = players.get(0);
      menu.playerDisplay();
      store.displayPokemon();;
      menu.gameMenu();
   }



   public void setNumOfRounds(int numOfRounds) {
      this.numOfRounds = numOfRounds;
   }
   public void setPlayedRounds(int playedRounds) {
      this.playedRounds = playedRounds;
   }
   public void setPlayers(ArrayList<Player> players) {
      this.players = players;
   }
   public void setStore(Store store) {
      this.store = store;
   }
   public void setStartingMoney(int startingMoney) {
      this.startingMoney = startingMoney;
   }

   public void changePlayer(){

   }
   //!
   public void playerLost(){
      if (currentPlayer.getPlayerPokemon() == null){

      }
   }



   


   
}