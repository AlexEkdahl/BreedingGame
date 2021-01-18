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
   protected boolean isSick = false;

   protected int maxOffspring;
   protected Set<Food> canEat;
   protected Food[] canEatFood;
   protected int age = 0;
   protected int maxAge;
   protected int price;

   public void setName(String name) {
      this.name = name;
   }

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
      return (price * health) / 100;
   }

   public void reduceHealth() {
      this.health -= (int) (Math.random() * 15) + 10;
   }

   public int getAge() {
      return age;
   }

   public void aging() {
      this.age++;
   }

   public int getHealth() {
      return health;
   }

   public void eat() {

   }

   public boolean isAlive() {
      return (age < maxAge && health > 0);
   }

   public void setGender(int n) {
      if (n == 1) {
         this.gender = Gender.FEMALE;
      } else {
         this.gender = Gender.MALE;
      }
   }

   public String getBreed() {
      return this.getClass().getSimpleName();
   }

   public String toString(boolean forShop) {
      if (forShop) {
      return "===== info =====\n" + getBreed() + "\n\nMax offsprings: " + maxOffspring + "\nMax age: " + getMaxAge()
               + "\nEat: " + foodToString() + "\n===== info =====";
      }
      return getBreed() + " named " + name + ", age: " + age + " " + gender + " and health: " + health
            + ". Generate max " + maxOffspring + " offsprings";
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