package Game.FoodClasses;


public abstract class Food {

   protected int price;
   protected int healthValue;

   public Food() {

   }

   public int getHealthValue() {
      return healthValue;
   }

   public int getPrice() {
      return price;
   }

   public String getType(){
      return this.getClass().getSimpleName();
   }

}