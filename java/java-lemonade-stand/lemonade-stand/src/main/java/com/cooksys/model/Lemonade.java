package com.cooksys.model;

public class Lemonade {
    
    private double lemonJuice;
    private double water;
    private double sugar;
    private int iceCubes;
    private final int CUP = 1;
    private double price;

    public Lemonade(int iceCubes, double lemonJuice, double sugar, double water) {
        this.iceCubes = iceCubes;
        this.lemonJuice = lemonJuice;
        this.sugar = sugar;
        this.water = water;
        calculatePrice();
    }

    private void calculatePrice() {
        price = lemonJuice * .3 + sugar * .15 + CUP * .50;
    }

    public double getLemonJuice() {
        return lemonJuice;
    }

    public double getWater() {
        return water;
    }

    public double getSugar() {
        return sugar;
    }

    public int getIceCubes() {
        return iceCubes;
    }

    public int getCUP() {
        return CUP;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Lemonade: { lemonJuice: " + lemonJuice + ", water: " + water + ", sugar: " + sugar + ", iceCubes: " + iceCubes
                + ", CUP: " + CUP + ", price: " + price  + " }";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(lemonJuice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(water);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sugar);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + iceCubes;
        result = prime * result + CUP;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lemonade other = (Lemonade) obj;
        if (Double.doubleToLongBits(lemonJuice) != Double.doubleToLongBits(other.lemonJuice))
            return false;
        if (Double.doubleToLongBits(water) != Double.doubleToLongBits(other.water))
            return false;
        if (Double.doubleToLongBits(sugar) != Double.doubleToLongBits(other.sugar))
            return false;
        if (iceCubes != other.iceCubes)
            return false;
        if (CUP != other.CUP)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        return true;
    }
    
    
    
}
