package game.items.foods;

import game.items.Item;

import java.io.Serializable;

public abstract class Food extends Item implements Serializable {


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

    public void removeFood(int quantity) {
        this.amount -= quantity;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

}