package Game.FoodClasses;

import java.io.Serializable;

public abstract class Food implements Serializable {

   protected int price;
   protected int healthValue;
   protected int amount;

   public Food() {

   }

   public int getAmount() {
      return amount;
   }

   public void addFood(int quantity) {
      this.amount += quantity;
   }

   public void removeFood(int quantity){
      this.amount -=  quantity;
      if(amount == 0){
         
      }
   }

   public int getHealthValue() {
      return healthValue;
   }

   public int getPrice() {
      return price;
   }

   public String getType() {
      return this.getClass().getSimpleName();
   }

}