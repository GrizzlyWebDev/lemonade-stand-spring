package main;
import main.lemonade.LemonJuice;
import main.lemonade.Lemonade;
import main.lemonade.Sugar;
import main.lemonade.Water;

public class Main {

    public static void main(String[] args) {
        
        LemonJuice lemonJuice = new LemonJuice(5, "ounces");
        Sugar sugar = new Sugar(12, "tsp");
        Water water = new Water(3, "cups");

        Lemonade lemonade = new Lemonade(lemonJuice, sugar, water);

        System.out.println(lemonade.getLemonJuice());
    }
    
}
