package Game.FoodClasses;

public abstract class Food {

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