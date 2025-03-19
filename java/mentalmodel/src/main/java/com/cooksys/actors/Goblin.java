package com.cooksys.actors;

public class Goblin extends Monster {
    public Goblin(int health, int damage) {
        super(health, damage);
    }

   /*  @Override
    public void attack(Actor target) {
        target.setHealth(target.getHealth() - 9000);
    } */

    @Override
    public String toString() {
        return "Goblin";
    }
}
