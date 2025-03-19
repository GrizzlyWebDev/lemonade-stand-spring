package main.lemonade;

public class Water {

    private int amount;
    private String unit;

    public Water(int amount, String unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public void drink(int amount) throws IllegalArgumentException {
        if (this.amount < amount) {
            throw new IllegalArgumentException("You cannot drink more water than exists. Amount of water available: " + this.amount);
        } else {
            this.amount -= amount;
        }
    }

}
