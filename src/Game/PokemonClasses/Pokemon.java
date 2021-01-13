package Game.PokemonClasses;

import Game.*;
import Game.FoodClasses.*;


public abstract class Pokemon {

   enum Gender{
      FEMALE,
      MALE
  }
   
   protected Player owner;
   protected String name;
   protected Gender gender;
   protected int health = 100;
   protected boolean isSick = false;
   protected int maxOffspring;
   protected Food[] canEatFood;

   protected int age;
   protected int maxAge;
   protected int price;

   public int numberOfBreeds = 0;

   public Pokemon(String name, int age, String gender, Player owner){
      this.name = name;
      this.age = age;
      this.owner = owner;
      setGender(gender);
      owner.addPokemon(this);
   }

   public void eat(){
      
   }

   public void setGender(String gender) {
      if (gender.equalsIgnoreCase("female")){
         this.gender = Gender.FEMALE;
      } else {
         this.gender = Gender.MALE;
      }
   }
   
   public boolean isAlive(){
      return (age < maxAge && health > 0);
   }



   
   
}