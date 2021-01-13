package Game.PokemonClasses;

import java.util.Scanner;

import Game.FoodClasses.*;
import Game.*;


public abstract class Pokemon {

   enum Gender{
      FEMALE,
      MALE
  }
   
   protected String name;
   protected Gender gender;
   protected int health = 100;
   protected boolean isSick = false;
   
   protected int maxOffspring;
   protected Food[] canEatFood;
   protected int age = 0;
   protected int maxAge;
   protected int price;


   static Scanner input = new Scanner(System.in);


   public void setName(String name) {
      this.name = name;
   }

   public String getName(){
      return name;
   }

   public int getPrice() {
      return price;
   }

   public void eat(){
      
   }

   public boolean isAlive(){
      return (age < maxAge && health > 0);
   }

   public void setGender(int n) {
      if (n == 1){
         this.gender = Gender.FEMALE;
      } else {
         this.gender = Gender.MALE;
      }
   }

   public String getBreed(){
      return this.getClass().getSimpleName();
   }


   




   
   
}