package Game.PokemonClasses;

import java.util.*;

import Game.GameHelper;
import Game.Player;
import Game.FoodClasses.*;

public abstract class Pokemon {

   enum Gender {
      FEMALE, MALE
   }

   protected String name;
   protected Gender gender;
   protected int health = 100;
   public boolean isSick = false;

   protected int maxOffspring;
   protected Set<Food> canEat;
   protected Food[] canEatFood;
   protected int age = 0;
   protected int maxAge;
   protected int price;

   public int getMaxAge() {
      return maxAge;
   }

   public String getName() {
      return name;
   }

   public int getPrice() {
      return price;
   }

   public Gender getGender() {
      return gender;
   }

   public String getGenderString() {
      return this.gender.toString().toLowerCase();
   }

   public Food[] getCanEatFood() {
      return canEatFood;
   }

   public int getValue() {
      return (int) Math.round(price * health/100.0 * (age == 0 ? 1.0 : (1.0 - (double)age/maxAge)));
   }

   public int getHealth() {
      return health;
   }

   public int getAge() {
      return age;
   }

   public String getBreed() {
      return this.getClass().getSimpleName();
   }

   public void setName(String name) {
      this.name = name;
   }

   public void reduceHealth() {
      this.health -= (int) (Math.random() * 15) + 10;
   }

   public void aging() {
      this.age++;
   }

   public String genderSymbol() {
      return (gender == Gender.FEMALE) ? "♀" : "♂";
   }

   public void eat(Food food, int quantity) {
      for (int i = 0; i < quantity; i++) {
         health = health + (int) (health * 0.10);
      }
      // Cant get over 100
      health = Math.min(health, 100);
   }

   public void setGender(int n) {
      if (n == 1) {
         this.gender = Gender.FEMALE;
      } else {
         this.gender = Gender.MALE;
      }
   }

   public String toString(boolean forShop) {
      if (forShop) {
         return "===== info =====\n" + getBreed() + "\n\nMax offsprings: " + maxOffspring + "\nMax age: " + getMaxAge()
               + "\nEat: " + foodToString() + "\n================";
      }
      return getBreed() + getGenderString() + " " + name + " " + " Max offspring: " + maxOffspring;
   }

   public String foodToString() {
      String s = "";
      for (Food food : canEatFood) {
         s = s + food.getClass().getSimpleName() + ", ";
      }
      return s.trim().replaceFirst(".$", "");
   }

   public void loveMakeing(Player owner, Pokemon mate) {
      int offsprings = (int) (Math.random() * this.maxOffspring) + 1;
      for (int i = 0; i < offsprings; i++) {
         System.out.print(i + 1 + ".");
         GameHelper.waitMilliSeconds(700);
         System.out.print(".");
         GameHelper.waitMilliSeconds(700);
         System.out.print(".");
         GameHelper.waitMilliSeconds(700);
      }
      System.out.print(" " + offsprings + " new " + mate.getBreed() + "!");
      System.out.println(" Congratulations, " + this.name + " and " + mate.getName() + " successfully mated");
      for (int j = 0; j < offsprings; j++) {
         switch (this.getBreed()) {
            case "Pikachu" -> owner.createPokemon(new Pikachu(), true);
            case "Bulbasur" -> owner.createPokemon(new Bulbasur(), true);
            case "Charmander" -> owner.createPokemon(new Charmander(), true);
            case "Squirtle" -> owner.createPokemon(new Squirtle(), true);
            case "Ditto" -> owner.createPokemon(new Ditto(), true);
         }
      }
   }

}