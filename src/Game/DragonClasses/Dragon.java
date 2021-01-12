package Game.DragonClasses;

import Game.*;

public abstract class Dragon {

   enum Gender{
      FEMALE,
      MALE
  }
   
   protected Player owner;
   protected String name;
   protected Gender gender;
   protected int health = 100;
   protected boolean sick = false;
   protected int maxOffspring;

   protected int age;
   protected int maxAge;
   protected int price;

   public int numberOfBreeds = 0;

   public Dragon(String name, int age, String gender){
      this.name = name;
      this.age = age;
      //TODO method for setting gender
      this.gender = "gender";
   }

   //!
   public Gender setGender(){

   }

   public boolean isAlive(){

   }

   public void eat(){
      
   }

   
   
}