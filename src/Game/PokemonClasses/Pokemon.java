package Game.PokemonClasses;

import Game.FoodClasses.*;


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

   public int numberOfBreeds = 0;

   public Pokemon(int age, String name, String gender) {
      this.name = name;
      setGender(gender);
   }


   public int getPrice() {
      return price;
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