package game.items;

public abstract class Item {

    protected int price;

    public int getPrice() {
        return price;
    }

    public String getClassName(){
        return this.getClass().getSimpleName();
    }

}
