package Game.PokemonClasses;



import Game.FoodClasses.*;
import java.util.*;



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




   public void setName(String name) {
      this.name = name;
   }

   public String getName(){
      return name;
   }

   public int getPrice() {
      return price;
   }

   public Gender getGender() {
      return gender;
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

   public String toString(){
      return getBreed() + "" + name + ", age: " + age + " " +gender + " and health: " + health + ". Generate max " + maxOffspring + " offsprings";
   }

   public String toString(boolean forShop){
      return getBreed() + ", can generate max " + maxOffspring + " offsprings and eat: " + foodToString();
   }

   public String foodToString(){
      String s = "";
      for (Food food: canEatFood){
         s = s + food.getClass().getSimpleName() + ", ";
      }
      return s.trim();
   }


   
   
}