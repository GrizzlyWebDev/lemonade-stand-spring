package main.lemonade;

public class Lemonade {

    private LemonJuice lemonJuice;
    private Sugar sugar;
    private Water water;

    public Lemonade(LemonJuice lemonJuice, Sugar sugar, Water water) {
        this.lemonJuice = lemonJuice;
        this.sugar = sugar;
        this.water = water;
    }

    public Lemonade(LemonJuice lemonJuice, Water water) {
        this.lemonJuice = lemonJuice;
        this.water = water;
    }

    public LemonJuice getLemonJuice() {
        return lemonJuice;
    }

    public void setLemonJuice(LemonJuice lemonJuice) {
        this.lemonJuice = lemonJuice;
    }

    public Sugar getSugar() {
        return sugar;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }


}
