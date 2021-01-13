package Game.FoodClasses;

public abstract class Food {

   protected int price;

   public Food() {

   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getType(){
      return this.getClass().getSimpleName();
   }

}